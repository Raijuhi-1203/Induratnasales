package codesgesture.app.induratnasales;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Adapter.UnitAdapter;
import codesgesture.app.induratnasales.Models.CatProductModel;
import codesgesture.app.induratnasales.Models.ProductModel;
import codesgesture.app.induratnasales.Models.UnitModel;
import codesgesture.app.induratnasales.Services.CallJsonWithoutProgress;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.interfaces.ExtraCallBack;

public class ProductDetails extends AppCompatActivity {
    CatProductModel productModel;
    ImageView img;
    TextView txnm,offmrp,mrp,dis,fulldesp;
    UnitAdapter unitAdapter;
    RecyclerView rvunit;
    ArrayList<UnitModel> unitModels=new ArrayList<>();
    String cmrp,cdis,coffmrp,pid;
    String str,mcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        productModel=(CatProductModel) getIntent().getSerializableExtra("data");
        str=getString(R.string.con);
        mcon=getString(R.string.maincon);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText(productModel.getProduct_full_name());

        BindIds();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rvunit.setLayoutManager(layoutManager);
        unitAdapter = new UnitAdapter(ProductDetails.this, unitModels,R.layout.item_unit,productModel.getProduct_unit(),productModel.getProduct_unit_value());
        rvunit.smoothScrollToPosition(0);
        unitAdapter.setOnRecyclerViewItemClickListener(new UnitAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClicked(String text) {
                String[] arrOfStr = text.split(",", 2);
                String a=arrOfStr[0];
                String b=arrOfStr[1];
                getPrice(a,b);
            }
        });

        rvunit.setAdapter(unitAdapter);
        rvunit.setItemViewCacheSize(unitModels.size());
        GetunitData();
    }

    private void BindIds() {
        img=findViewById(R.id.img);txnm=findViewById(R.id.txnm);
        offmrp=findViewById(R.id.offmrp);mrp=findViewById(R.id.mrp);
        dis=findViewById(R.id.dis);fulldesp=findViewById(R.id.fulldesp);
        rvunit=findViewById(R.id.rvunit);

        txnm.setText(productModel.getProduct_full_name());
        offmrp.setText("₹ "+productModel.getProduct_final_sell_price());
        mrp.setText("₹ "+productModel.getProduct_market_price());
        mrp.setPaintFlags(mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        dis.setText(productModel.getProduct_discount_percentage()+"% OFF");
        fulldesp.setText(Html.fromHtml(productModel.getProduct_description()));

        cmrp=productModel.getProduct_market_price();
        cdis=productModel.getProduct_discount_percentage();
        coffmrp=productModel.getProduct_final_sell_price();
        pid=productModel.getId();

        Glide.with(ProductDetails.this).load(mcon+productModel.getPhoto_path()).into(img);
        
    }

    private void getPrice(String text, String b) {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(ProductDetails.this);
        param.add(new NetParam("product_unit",b));
        param.add(new NetParam("product_id",productModel.getProduct_id()));
        param.add(new NetParam("product_unit_value",text));
        jc.SendRequest(str,"get_price", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    offmrp.setText("₹ "+obj.getString("product_final_sell_price"));
                    mrp.setText("₹ "+obj.getString("product_market_price"));
                    mrp.setPaintFlags(mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    dis.setText(obj.getString("product_discount_percentage")+"% OFF");

                    cmrp=obj.getString("product_market_price");
                    cdis=obj.getString("product_discount_percentage");
                    coffmrp=obj.getString("product_final_sell_price");
                    pid=obj.getString("id");

                    //  mrp.setText(obj.getString("status"));
                }
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "LOGIN", "Please wait while getting..");
    }

    private void GetunitData() {
        unitModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(ProductDetails.this);
        param.add(new NetParam("product_id",productModel.getProduct_id()));
        jc.SendRequest(str,"get_product_unit", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    UnitModel product = new UnitModel();
                    product.setProduct_unit(obj.getString("product_unit"));
                    product.setProduct_unit_value(obj.getString("product_unit_value"));
                    unitModels.add(product);
                }
                unitAdapter.notifyDataSetChanged();
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }

}
