package codesgesture.app.induratnasales.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Models.UnitModel;
import codesgesture.app.induratnasales.R;
import codesgesture.app.induratnasales.Utils.SessionManage;
import codesgesture.app.induratnasales.interfaces.ExtraCallBack;


public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.ViewHolder> {
    private ArrayList<UnitModel> arrayList;
    private Context context;
    private int layout;
    String u,uv;
    int row_index;

    public UnitAdapter(Context context, ArrayList<UnitModel> arrayList, int layout,String unit,String unitvalue) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        this.u=unit;
        this.uv=unitvalue;
    }

    private static OnRecyclerViewItemClickListener mListener;

    public interface OnRecyclerViewItemClickListener {
        void onItemClicked(String text);
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        final UnitModel data = arrayList.get(i);

        if(u.equals(data.getProduct_unit()) && uv.equals(data.getProduct_unit_value())){
            holder.linear_layout.setBackgroundResource(R.drawable.grey_box);
        }else {
            holder.linear_layout.setBackgroundResource(R.drawable.cat_box);
        }

        holder.unitnm.setText(data.getProduct_unit_value()+" "+data.getProduct_unit());
        holder.unitnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=i;
                notifyDataSetChanged();
                //  holder.linear_layout.setBackgroundResource(R.drawable.grey_box);
                mListener.onItemClicked(data.getProduct_unit_value()+","+data.getProduct_unit());
            }
        });

        if(row_index==i){
            holder.linear_layout.setBackgroundResource(R.drawable.grey_box);
        }else {
            holder.linear_layout.setBackgroundResource(R.drawable.cat_box);
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView unitnm;
        LinearLayout linear_layout;

        ViewHolder(View view) {
            super(view);
            unitnm = (TextView) view.findViewById(R.id.unitnm);
            linear_layout=view.findViewById(R.id.linear_layout);
        }
    }

    public void updateList(ArrayList<UnitModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }

}