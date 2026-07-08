package codesgesture.app.induratnasales.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Models.ShopModel;
import codesgesture.app.induratnasales.ProductList;
import codesgesture.app.induratnasales.R;
import codesgesture.app.induratnasales.Services.UserUtil;
import codesgesture.app.induratnasales.Utils.SessionManage;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private ArrayList<ShopModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;

    public ShopAdapter(Context context, ArrayList<ShopModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getSales_id();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        final ShopModel data = arrayList.get(i);

        holder.address.setText("  "+data.getAddress_line_1()+", "+data.getAddress_line_2());
        holder.mobile.setText("  Mobile no : "+data.getCustomer_mobileno());
        holder.shopcode.setText("Shop Code : "+data.getShop_code());
        holder.creditlimit.setText("Credit Limit : ₹"+data.getCredit_limit());
        holder.amount.setText("Left Credit Limit : ₹"+data.getTotal_amt());
        holder.shopname.setText(data.getFirm_name());

        float a = Float.parseFloat(data.getCredit_limit());
        float b = Float.parseFloat(data.getTotal_amt());

        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a >= b){
                    Intent intent=new Intent(context, ProductList.class);
                    intent.putExtra("data",data);
                    context.startActivity(intent);
                }else {
                    UserUtil.ShowMsg("Your credit limit is over.",context);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView shopcode,shopname,mobile,address,creditlimit,amount;
        LinearLayout click;

        ViewHolder(View view) {
            super(view);
            shopcode= view.findViewById(R.id.shopcode);
            shopname= view.findViewById(R.id.shopname);
            mobile= view.findViewById(R.id.mobile);
            address= view.findViewById(R.id.address);
            click= view.findViewById(R.id.click);
            amount= view.findViewById(R.id.amount);
            creditlimit= view.findViewById(R.id.creditlimit);
        }
    }

    public void updateList(ArrayList<ShopModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}
