package codesgesture.app.induratnasales;

import android.content.Intent;
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

import codesgesture.app.induratnasales.Adapter.ProductAdapter;
import codesgesture.app.induratnasales.Adapter.ShopAdapter;
import codesgesture.app.induratnasales.Models.ProductModel;
import codesgesture.app.induratnasales.Models.ShopModel;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.interfaces.Notify;

public class ProductList extends AppCompatActivity {

    TextView btorder,total;
    ArrayList<ProductModel> productModels=new ArrayList<>();
    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    EditText search;
    String s;
    ShopModel shopModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);
        shopModel=(ShopModel)getIntent().getSerializableExtra("data");
        s=getString(R.string.con);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("Product");

        total=findViewById(R.id.total);
        btorder=findViewById(R.id.btorder);
        btorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductList.this,PageCart.class);
                intent.putExtra("data",shopModel);
                startActivity(intent);
            }
        });

        recyclerView=findViewById(R.id.recyclerView);
        search=findViewById(R.id.search);

        GridLayoutManager mLayoutManager = new GridLayoutManager(ProductList.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        productAdapter = new ProductAdapter(ProductList.this, productModels, R.layout.item_product, new Notify() {
            @Override
            public void onAdd(ProductModel data) {
                GetData();
            }

            @Override
            public void onRemove(ProductModel data) {
                GetData();
            }
        },shopModel.getShop_code());
        recyclerView.setAdapter(productAdapter);
        recyclerView.setItemViewCacheSize(productModels.size());
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
        ArrayList<ProductModel> temp = new ArrayList();
        for (ProductModel d : productModels) {
            if(d!=null){
                if (d.getProduct_full_name().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                }}
        }
        productAdapter.updateList(temp);
    }

    private void GetData() {
        productModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(ProductList.this);
        param.add(new NetParam("auto_guid",shopModel.getShop_code()));
        jc.SendRequest(s,"get_product", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    ProductModel product = new ProductModel();
                    product.setId(obj.getString("id"));
                    product.setAuto_guid(obj.getString("auto_guid"));
                    product.setCart_qty(Integer.parseInt(obj.getString("cart_qty")));
                    product.setQty(Integer.parseInt(obj.getString("cart_qty")));
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
                    product.setTotal_amt(obj.getString("total_amt"));
                    product.setId1(obj.getString("id1"));
                    productModels.add(product);
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }
    
}
