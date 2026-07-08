package codesgesture.app.induratnasales.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Models.ProductModel;
import codesgesture.app.induratnasales.R;
import codesgesture.app.induratnasales.Services.CallJsonWithoutProgress;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.Services.UserUtil;
import codesgesture.app.induratnasales.Utils.Constants;
import codesgesture.app.induratnasales.Utils.SessionManage;
import codesgesture.app.induratnasales.interfaces.ExtraCallBack;
import codesgesture.app.induratnasales.interfaces.Notify;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<ProductModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    Notify notify;
    ExtraCallBack ecb;
    int a=0;
    String s,shop_code,sm;

    double total;
    public CartAdapter(Context context, ArrayList<ProductModel> arrayList, int layout, Notify notify1,String shop_code,double total) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        this.notify=notify1;
        s=context.getString(R.string.con);
        sm=context.getString(R.string.maincon);
        this.shop_code=shop_code;
        this.total=total;
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getSales_id();
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new CartAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, final int i) {
        final ProductModel data = arrayList.get(i);

        holder.prdnm.setText(data.getProduct_full_name());
        holder.mrp.setText("₹ "+data.getProduct_final_sell_price());

        if(data.getCart_qty()!=0){
            holder.cartno.setText(String.valueOf(data.getCart_qty()));
        }

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cq =data.getCart_qty();
                cq++;
                data.setQty(cq);
                data.setCart_qty(cq);
                holder.cartno.setText(String.valueOf(data.getCart_qty()));
                String price = data.getProduct_final_sell_price() ;
                double totalamt = cq * Double.parseDouble(price) ;

                notify.onRemove(null);

                double t =Double.parseDouble(data.getTotal_amt());

                if (totalamt <= t){
                    QtyChangeCart(totalamt,data.getCart_qty(),Userid,data.getProduct_id(),data.getId1());
                }else {
                    UserUtil.ShowMsg("Your credit limit is over.",context);
                }

            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cq =data.getQty();
                if (cq>0) {
                    cq--;
                    if(cq==0){
                        a=100;
                        RemoveCart(data.getProduct_id(),data.getId());
                    }else {
                        a=0;
                        data.setQty(cq);
                        data.setCart_qty(cq);
                        holder.cartno.setText(String.valueOf(data.getQty()));
                    }
                }

                if (a == 100) {

                }else if (a==0){
                    String price = data.getProduct_final_sell_price() ;
                    double totalamt = cq * Double.parseDouble(price) ;
                    notify.onRemove(null);

                    QtyChangeCart(totalamt,data.getQty(),Userid,data.getProduct_id(),data.getId());
                }
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemoveCart(data.getProduct_id(),data.getId());
            }
        });

    }

    private void QtyChangeCart(double totalamt, int cart_qty, String userid, String product_id, String product_final_sell_price) {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress((Activity) context);
        param.add(new NetParam("product_id",product_id));
        param.add(new NetParam("cart_qty",String.valueOf(cart_qty)));
        param.add(new NetParam("product_price_id",product_final_sell_price));
        param.add(new NetParam("shop_code",shop_code));
        jc.SendRequest(s,"qty_change", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                if (ecb != null){
                    ecb.OnCompleted("removed");
                }
                notify.onAdd(null);
            }
            @Override
            public void onPostError(String msg) {
            }
        }, " ", "Loading..");
    }

    private void RemoveCart(String product_id, String id1) {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress((Activity) context);
        param.add(new NetParam("customer_id",shop_code));
        param.add(new NetParam("product_id",product_id));
        param.add(new NetParam("product_price_id",id1));
        jc.SendRequest(s,"removetocart", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Remove from list",context);
                if (ecb != null){
                    ecb.OnCompleted("removed");
                }
                notify.onAdd(null);
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prdnm,mrp,cartno;
        ImageView add,minus,remove;

        ViewHolder(View view) {
            super(view);
            prdnm = (TextView) view.findViewById(R.id.prdnm);
            mrp = (TextView) view.findViewById(R.id.mrp);
            add=view.findViewById(R.id.add);
            cartno=view.findViewById(R.id.cartno);
            minus=view.findViewById(R.id.minus);
            remove=view.findViewById(R.id.remove);
        }
    }

    public void updateList(ArrayList<ProductModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }

}