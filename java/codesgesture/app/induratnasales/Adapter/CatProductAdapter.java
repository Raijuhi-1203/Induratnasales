package codesgesture.app.induratnasales.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import codesgesture.app.induratnasales.Models.CatProductModel;
import codesgesture.app.induratnasales.Models.CatProductModel;
import codesgesture.app.induratnasales.ProductDetails;
import codesgesture.app.induratnasales.R;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.CallJsonWithoutProgress;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.Services.UserUtil;
import codesgesture.app.induratnasales.Utils.SessionManage;
import codesgesture.app.induratnasales.interfaces.ExtraCallBack;
import codesgesture.app.induratnasales.interfaces.Notify;

public class CatProductAdapter extends RecyclerView.Adapter<CatProductAdapter.ViewHolder> {
    private ArrayList<CatProductModel> arrayList;
    private Context context;
    private int layout;
    String str;
    public CatProductAdapter(Context context, ArrayList<CatProductModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        str= context.getString(R.string.maincon);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        final CatProductModel data = arrayList.get(i);

        holder.prdnm.setText(data.getProduct_full_name());
        holder.unit.setText(" "+data.getProduct_unit_value() + data.getProduct_unit());
        holder.offmrp.setText("₹ "+data.getProduct_final_sell_price());
        holder.mrp.setText("₹ "+data.getProduct_market_price());
        holder.mrp.setPaintFlags(holder.mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        
        String url = str+data.getPhoto_path();
        Glide.with(context).load(url).into(holder.prdimg);

        holder.click_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ProductDetails.class);
                intent.putExtra("data",data);
                context.startActivity(intent);
            }
        });

    }
    
    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prdnm,offmrp,mrp,unit;
        ImageView prdimg;
        LinearLayout click_layout;
        ViewHolder(View view) {
            super(view);
            unit = (TextView) view.findViewById(R.id.unit);
            prdnm = (TextView) view.findViewById(R.id.prdnm);
            offmrp = (TextView) view.findViewById(R.id.offmrp);
            mrp = (TextView) view.findViewById(R.id.mrp);
            prdimg=(ImageView)view.findViewById(R.id.prdimg);
            click_layout= view.findViewById(R.id.click_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateList(ArrayList<CatProductModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }

}
