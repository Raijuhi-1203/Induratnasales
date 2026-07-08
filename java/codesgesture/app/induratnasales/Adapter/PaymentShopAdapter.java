package codesgesture.app.induratnasales.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Models.PaymentModel;
import codesgesture.app.induratnasales.R;
import codesgesture.app.induratnasales.Utils.SessionManage;

public class PaymentShopAdapter extends RecyclerView.Adapter<PaymentShopAdapter.ViewHolder> {
    private ArrayList<PaymentModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;

    public PaymentShopAdapter(Context context, ArrayList<PaymentModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getSales_id();
    }

    @Override
    public PaymentShopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new PaymentShopAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PaymentShopAdapter.ViewHolder holder, final int i) {
        final PaymentModel data = arrayList.get(i);
        holder.date.setText(data.getTransaction_date());
        holder.credit.setText(data.getCredit_amount());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView credit,date;

        ViewHolder(View view) {
            super(view);
            credit= view.findViewById(R.id.credit);
            date= view.findViewById(R.id.date);
        }
    }

    public void updateList(ArrayList<PaymentModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}
