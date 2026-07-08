package codesgesture.app.induratnasales.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Models.PaymentModel;
import codesgesture.app.induratnasales.R;
import codesgesture.app.induratnasales.Utils.SessionManage;

public class LedgerAdapter extends RecyclerView.Adapter<LedgerAdapter.ViewHolder> {
    private ArrayList<PaymentModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;

    public LedgerAdapter(Context context, ArrayList<PaymentModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getSales_id();
    }

    @Override
    public LedgerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new LedgerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final LedgerAdapter.ViewHolder holder, final int i) {
        final PaymentModel data = arrayList.get(i);

        holder.date.setText(data.getTransaction_date());
        holder.mode.setText(data.getPayment_mode());
        holder.balance.setText(data.getBalance());

//        if (data.getTransaction_section().equals("Order")){
//            holder.mode.setText(data.getPayment_mode());
//            holder.mode.setTextColor(holder.mode.getContext().getColor(R.color.red));
//        }else if (data.getTransaction_section().equals("Payment")){
//            holder.mode.setText(data.getPayment_mode());
//            holder.mode.setTextColor(holder.mode.getContext().getColor(R.color.green));
//        }

        if (data.getTransaction_section().equals("Order")){
            holder.debit.setText(data.getDebit_amount());
            holder.debit.setTextColor(holder.debit.getContext().getColor(R.color.red));
            holder.credit.setText("0.00");
        }else if (data.getTransaction_section().equals("Payment")){
            holder.credit.setText(data.getCredit_amount());
            holder.credit.setTextColor(holder.credit.getContext().getColor(R.color.green));
            holder.debit.setText("0.00");
        }

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView credit,date,mode,debit,balance;

        ViewHolder(View view) {
            super(view);
            credit= view.findViewById(R.id.credit);
            debit= view.findViewById(R.id.debit);
            mode= view.findViewById(R.id.mode);
            date= view.findViewById(R.id.date);
            balance= view.findViewById(R.id.balance);
        }
    }

    public void updateList(ArrayList<PaymentModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}
