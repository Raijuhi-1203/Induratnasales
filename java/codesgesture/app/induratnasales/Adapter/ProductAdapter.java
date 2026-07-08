package codesgesture.app.induratnasales.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Models.ProductModel;
import codesgesture.app.induratnasales.Models.ProductModel;
import codesgesture.app.induratnasales.ProductList;
import codesgesture.app.induratnasales.R;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.CallJsonWithoutProgress;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.Services.UserUtil;
import codesgesture.app.induratnasales.Utils.SessionManage;
import codesgesture.app.induratnasales.interfaces.ExtraCallBack;
import codesgesture.app.induratnasales.interfaces.Notify;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<ProductModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    Notify notify;
    ExtraCallBack ecb;
    String s,shop_code;

    public ProductAdapter(Context context, ArrayList<ProductModel> arrayList, int layout, Notify notify1,String shop_code) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        this.notify=notify1;
        s=context.getString(R.string.con);
        this.shop_code=shop_code;
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getSales_id();
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ProductAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.ViewHolder holder, final int i) {
        final ProductModel data = arrayList.get(i);

        holder.prdnm.setText(data.getProduct_full_name());
        holder.price.setText("  MRP : ₹ "+data.getProduct_final_sell_price());
        holder.weight.setText(" Weight : "+data.getProduct_unit_value()+" "+data.getProduct_unit());

        holder.buy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                double pmrp = Double.parseDouble(data.getProduct_final_sell_price());
                double tamt = Double.parseDouble(data.getTotal_amt());
                if (pmrp <= tamt){
                    if (isChecked){
                        holder.qty.setText("1");
                        AddCart(data.getProduct_id(),data.getId1(),holder.qty.getText().toString());
                    }else {
                        holder.qty.setText("");
                        RemoveCart(data.getProduct_id(),data.getId1());
                    }
                }else {
                    holder.buy.setChecked(false);
                    UserUtil.ShowMsg("Product amount greater than credit limit.",context);
                }

            }
        });

//        holder.qty.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                QTYChange(data.getId1(),data.getProduct_id(),s.toString());
//            }
//        });

    }

//    private void QTYChange(String id1, String product_id, String toString) {
//        ArrayList<NetParam> param;
//        param = new ArrayList<NetParam>();
//        CallJsonWithoutProgress jc = new CallJsonWithoutProgress((Activity) context);
//        param.add(new NetParam("customer_id",shop_code));
//        param.add(new NetParam("product_id",product_id));
//        param.add(new NetParam("product_price_id",id1));
//        param.add(new NetParam("cart_qty",toString));
//        jc.SendRequest(s,"qty_change", param, new JsonCallbacks() {
//            @Override
//            public void onPostSuceess(String json, String method) throws JSONException {
//
//            }
//
//            @Override
//            public void onPostError(String msg) {
//
//            }
//        }, "", "Loading..");
//    }

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

    private void AddCart(String product_id, String prd_price_id, String qty) {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress((Activity) context);
        param.add(new NetParam("shop_code",shop_code));
        param.add(new NetParam("product_id",product_id));
        param.add(new NetParam("product_price_id",prd_price_id));
        param.add(new NetParam("cart_qty",qty));
        jc.SendRequest(s,"addtocart", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Added from list",context);
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
        TextView prdnm,price,weight;
        CheckBox buy;
        EditText qty;
        ViewHolder(View view) {
            super(view);
            prdnm= view.findViewById(R.id.prdnm);
            price= view.findViewById(R.id.price);
            weight= view.findViewById(R.id.weight);
            buy= view.findViewById(R.id.buy);
            qty= view.findViewById(R.id.qty);
        }
    }

    public void updateList(ArrayList<ProductModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }

}
