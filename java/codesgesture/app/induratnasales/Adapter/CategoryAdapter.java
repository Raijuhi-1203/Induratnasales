package codesgesture.app.induratnasales.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Models.CategoryModel;
import codesgesture.app.induratnasales.PageProduct;
import codesgesture.app.induratnasales.R;
import codesgesture.app.induratnasales.Utils.Constants;
import codesgesture.app.induratnasales.Utils.SessionManage;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategoryModel> arrayList;
    private Context context;
    String Userid="",mcon;
    private int layout;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        mcon=context.getString(R.string.maincon);
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getSales_id();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        final CategoryModel data = arrayList.get(i);

        holder.catnm.setText(data.getCategory_name());
        Glide.with(context).load(mcon+data.getCategory_photo()).into(holder.catimg);

        holder.click_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PageProduct.class);
                intent.putExtra("catid",data.getCategory_id());
                intent.putExtra("catnm",data.getCategory_name());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView catnm;
        ImageView catimg;
        LinearLayout click_layout;

        ViewHolder(View view) {
            super(view);
            catnm = (TextView) view.findViewById(R.id.catnm);
            catimg=(ImageView)view.findViewById(R.id.catimg);
            click_layout= view.findViewById(R.id.click_layout);
        }
    }

    public void updateList(ArrayList<CategoryModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}