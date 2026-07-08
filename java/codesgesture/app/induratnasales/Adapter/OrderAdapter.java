package codesgesture.app.induratnasales.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Models.OrderModel;
import codesgesture.app.induratnasales.R;
import codesgesture.app.induratnasales.Utils.SessionManage;
import codesgesture.app.induratnasales.interfaces.ExtraCallBack;
import codesgesture.app.induratnasales.interfaces.Notify;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private ArrayList<OrderModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    Notify notify;
    ExtraCallBack ecb;
    int a=0;
    String s,shop_code,sm;

    public OrderAdapter(Context context, ArrayList<OrderModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        s=context.getString(R.string.con);
        sm=context.getString(R.string.maincon);
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getSales_id();
    }

    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new OrderAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final OrderAdapter.ViewHolder holder, final int i) {
        final OrderModel data = arrayList.get(i);
        holder.prdnm.setText(data.getProduct_name());
        holder.mrp.setText(data.getProduct_final_sell_price());
        holder.qty.setText(String.valueOf(data.getProduct_qty()));
        holder.unit.setText(data.getProduct_unit_value()+data.getProduct_unit());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prdnm,mrp,qty,unit;

        ViewHolder(View view) {
            super(view);
            prdnm = (TextView) view.findViewById(R.id.prdnm);
            mrp = (TextView) view.findViewById(R.id.mrp);
            qty=view.findViewById(R.id.qty);
            unit=view.findViewById(R.id.unit);
        }
    }

    public void updateList(ArrayList<OrderModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }

}