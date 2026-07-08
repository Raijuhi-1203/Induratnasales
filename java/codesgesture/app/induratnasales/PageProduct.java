package codesgesture.app.induratnasales;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Adapter.CatProductAdapter;
import codesgesture.app.induratnasales.Adapter.CategoryAdapter;
import codesgesture.app.induratnasales.Models.CatProductModel;
import codesgesture.app.induratnasales.Models.CategoryModel;
import codesgesture.app.induratnasales.Models.ProductModel;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.CallJsonWithoutProgress;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;

public class PageProduct extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayout norecord;
    EditText search;
    CatProductAdapter categoryAdapter;
    ArrayList<CatProductModel> categoryModels=new ArrayList<>();
    String s,catid,catnm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products);
        catid=getIntent().getStringExtra("catid");
        catnm=getIntent().getStringExtra("catnm");
        s=getString(R.string.con);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText(catnm);


        search=findViewById(R.id.search);
        norecord=findViewById(R.id.norecord);
        recyclerView=findViewById(R.id.recyclerView);

        GridLayoutManager mLayoutManager = new GridLayoutManager(PageProduct.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        categoryAdapter = new CatProductAdapter(PageProduct.this, categoryModels,R.layout.item_product_cat);
        recyclerView.setAdapter(categoryAdapter);
        GetData();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    void filter(String text) {
        ArrayList<CatProductModel> temp = new ArrayList();
        for (CatProductModel d : categoryModels) {
            if(d!=null){
                if (d.getProduct_full_name().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                }}
        }
        categoryAdapter.updateList(temp);
    }

    private void GetData() {
        categoryModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageProduct.this);
        param.add(new NetParam("catid",catid));
        jc.SendRequest(s,"get_categorywise_product", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    CatProductModel product = new CatProductModel();
                    product.setId(obj.getString("id"));
                    product.setAuto_guid(obj.getString("auto_guid"));
                    product.setProduct_id(obj.getString("product_id"));
                    product.setProduct_short_name(obj.getString("product_short_name"));
                    product.setProduct_full_name(obj.getString("product_full_name"));
                    product.setProduct_description(obj.getString("product_description"));
                    product.setProduct_barcode(obj.getString("product_barcode"));
                    product.setProduct_sku(obj.getString("product_sku"));
                    product.setProduct_hsnORsac(obj.getString("product_hsnORsac"));
                    product.setProduct_parent_category_id(obj.getString("product_parent_category_id"));
                    product.setProduct_parent_category_name(obj.getString("product_parent_category_name"));
                    product.setProduct_sub_category_id(obj.getString("product_sub_category_id"));
                    product.setProduct_sub_category_name(obj.getString("product_sub_category_name"));
                    product.setProduct_unit(obj.getString("product_unit"));
                    product.setProduct_unit_value(obj.getString("product_unit_value"));
                    product.setProduct_GST_type(obj.getString("product_GST_type"));
                    product.setProduct_GST_rate(obj.getString("product_GST_rate"));
                    product.setProduct_GST_percentage(obj.getString("product_GST_percentage"));
                    product.setProduct_tax_type(obj.getString("product_tax_type"));
                    product.setProduct_CGST_percentage(obj.getString("product_CGST_percentage"));
                    product.setProduct_CGST_rate(obj.getString("product_CGST_rate"));
                    product.setProduct_SGST_percentage(obj.getString("product_SGST_percentage"));
                    product.setProduct_SGST_rate(obj.getString("product_SGST_rate"));
                    product.setProduct_IGST_percentage(obj.getString("product_IGST_percentage"));
                    product.setProduct_IGST_rate(obj.getString("product_IGST_rate"));
                    product.setProduct_market_price(obj.getString("product_market_price"));
                    product.setProduct_discount_percentage(obj.getString("product_discount_percentage"));
                    product.setProduct_discount_price(obj.getString("product_discount_price"));
                    product.setProduct_with_gst_Price(obj.getString("product_with_gst_Price"));
                    product.setProduct_final_sell_price(obj.getString("product_final_sell_price"));
                    product.setProduct_shipping_charge(obj.getString("product_shipping_charge"));
                    product.setProduct_stock(obj.getString("product_stock"));
                    product.setProduct_photo(obj.getString("product_photo"));
                    product.setId1(obj.getString("id1"));
                    product.setPhoto_path(obj.getString("photo_path"));
                    categoryModels.add(product);
                }
                categoryAdapter.notifyDataSetChanged();
                BindDataView();
            }

            @Override
            public void onPostError(String msg) {
                BindDataView();
            }
        }, "", "Loading..");
    }

    private void BindDataView() {
        if(categoryModels.size()>0){
            norecord.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        else{
            norecord.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

}