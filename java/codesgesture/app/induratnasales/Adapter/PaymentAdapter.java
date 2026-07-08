package codesgesture.app.induratnasales.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Models.PaymentModel;
import codesgesture.app.induratnasales.Models.PaymentModel;
import codesgesture.app.induratnasales.ProductList;
import codesgesture.app.induratnasales.R;
import codesgesture.app.induratnasales.Services.UserUtil;
import codesgesture.app.induratnasales.Utils.SessionManage;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {
    private ArrayList<PaymentModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;

    public PaymentAdapter(Context context, ArrayList<PaymentModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getSales_id();
    }

    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new PaymentAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PaymentAdapter.ViewHolder holder, final int i) {
        final PaymentModel data = arrayList.get(i);

        holder.mode.setText("Payment Mode : "+data.getPayment_mode());
        holder.date.setText("Date : "+data.getTransaction_date());

        if (data.getTransaction_section().equals("Order")){
            holder.amtype.setText("₹ "+data.getDebit_amount()+"  ("+data.getTransaction_type()+")");
            holder.amtype.setTextColor(holder.amtype.getContext().getColor(R.color.red));
        }else if (data.getTransaction_section().equals("Payment")){
            holder.amtype.setText("₹ "+data.getCredit_amount()+"  ("+data.getTransaction_type()+")");
            holder.amtype.setTextColor(holder.amtype.getContext().getColor(R.color.green));
        }

        holder.section.setText("Section : "+data.getTransaction_section());
        holder.shopname.setText(data.getFirm_name());

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView amtype,shopname,date,mode,section;
        LinearLayout click;

        ViewHolder(View view) {
            super(view);
            amtype= view.findViewById(R.id.amtype);
            shopname= view.findViewById(R.id.shopname);
            date= view.findViewById(R.id.date);
            mode= view.findViewById(R.id.mode);
            click= view.findViewById(R.id.click);
            section= view.findViewById(R.id.section);
        }
    }

    public void updateList(ArrayList<PaymentModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}
