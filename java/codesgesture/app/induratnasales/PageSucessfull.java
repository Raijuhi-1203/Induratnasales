package codesgesture.app.induratnasales;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Adapter.OrderAdapter;
import codesgesture.app.induratnasales.Adapter.ShopAdapter;
import codesgesture.app.induratnasales.Models.OrderModel;
import codesgesture.app.induratnasales.Models.ProductModel;
import codesgesture.app.induratnasales.Models.ShopModel;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;

public class PageSucessfull extends AppCompatActivity {
    Button btdone;
    TextView date,shopnm,mob,total;
    RecyclerView rvorderitem;
    OrderAdapter orderAdapter;
    ArrayList<OrderModel> orderModels=new ArrayList<>();
    String s;
    ShopModel shopModel;
   // ProductModel productModels;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.successfull);
        shopModel=(ShopModel)getIntent().getSerializableExtra("shopModel");
      //  productModels=(ProductModel)getIntent().getSerializableExtra("productModels");
        s=getString(R.string.con);

        btdone=findViewById(R.id.btdone);
        btdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PageSucessfull.this,Dashboard.class));
            }
        });

        rvorderitem=findViewById(R.id.rvorderitem);
        date=findViewById(R.id.date);
        shopnm=findViewById(R.id.shopnm);
        mob=findViewById(R.id.mob);
        total=findViewById(R.id.total);

        mob.setText(shopModel.getCustomer_mobileno());
        shopnm.setText(shopModel.getFirm_name()+" ( Shop Code : "+shopModel.getShop_code()+")");

        GridLayoutManager mLayoutManager = new GridLayoutManager(PageSucessfull.this, 1);
        rvorderitem.setLayoutManager(mLayoutManager);
        orderAdapter = new OrderAdapter(PageSucessfull.this, orderModels, R.layout.item_order_product);
        rvorderitem.setAdapter(orderAdapter);
        rvorderitem.setItemViewCacheSize(orderModels.size());
        GetData();

    }

    private void GetData() {
        orderModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageSucessfull.this);
        param.add(new NetParam("shop_code",shopModel.getShop_code()));
        jc.SendRequest(s,"get_order", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    OrderModel product = new OrderModel();
                    product.setId(obj.getString("id"));
                    product.setShop_code(obj.getString("shop_code"));
                    product.setCustomer_name(obj.getString("customer_name"));
                    product.setCustomer_mobileno(obj.getString("customer_mobileno"));
                    product.setProduct_name(obj.getString("product_name"));
                    product.setProduct_final_sell_price(obj.getString("product_final_sell_price"));
                    product.setProduct_qty(obj.getString("product_qty"));
                    product.setProduct_unit(obj.getString("product_unit"));
                    product.setProduct_unit_value(obj.getString("product_unit_value"));
                    total.setText("₹ "+obj.getString("total_order_amount"));
                    date.setText("Date : "+obj.getString("order_date"));
                    orderModels.add(product);
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }

    @Override
    public void onBackPressed() {
    }

}
