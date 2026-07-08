package codesgesture.app.induratnasales;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Adapter.ShopAdapter;
import codesgesture.app.induratnasales.Models.SalesPersonModel;
import codesgesture.app.induratnasales.Models.ShopModel;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.CallJsonWithoutProgress;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.Utils.SessionManage;

public class Dashboard extends AppCompatActivity {
    SalesPersonModel salesPersonModel;
    CardView lg,catalogue,pay,shopwise,datewise,takeorder,addpayment,paymentlist;
    RecyclerView rvshop;
    ArrayList<ShopModel> shopModels=new ArrayList<>();
    ShopAdapter shopAdapter;
    String s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        salesPersonModel=(SalesPersonModel) SessionManage.getCurrentUser(getApplicationContext());
        s=getString(R.string.con);

        TextView name=findViewById(R.id.name);
        name.setText("Hello, "+salesPersonModel.getSales_name());

        takeorder=findViewById(R.id.takeorder);
        datewise=findViewById(R.id.datewise);
        shopwise=findViewById(R.id.shopwise);
        pay=findViewById(R.id.pay);
        catalogue=findViewById(R.id.catalogue);
        addpayment=findViewById(R.id.addpayment);
        paymentlist=findViewById(R.id.paymentlist);
        lg=findViewById(R.id.lg);
        rvshop=findViewById(R.id.rvshop);

        GridLayoutManager mLayoutManager = new GridLayoutManager(Dashboard.this, 1);
        rvshop.setLayoutManager(mLayoutManager);
        shopAdapter = new ShopAdapter(Dashboard.this, shopModels, R.layout.item_shop);
        rvshop.setAdapter(shopAdapter);
        rvshop.setItemViewCacheSize(shopModels.size());
        GetData();

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManage.ClearSession(getApplicationContext());
                startActivity(new Intent(Dashboard.this, MainActivity.class));
                finish();
            }
        });
        takeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,TakeOrder.class));
            }
        });
        addpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,AddPayment.class));
            }
        });
        paymentlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,PaymentList.class));
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,ShopPayment.class));
            }
        });
        shopwise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,ShopLedger.class));
            }
        });
        datewise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,DateWiseLedger.class));
            }
        });
        catalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Catalogue.class));
            }
        });

    }

    private void GetData() {
        shopModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(Dashboard.this);
        param.add(new NetParam("sales_id",salesPersonModel.getSales_id()));
        jc.SendRequest(s,"get_recent_shop", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    ShopModel product = new ShopModel();
                    product.setId(obj.getString("id"));
                    product.setBuyer_id(obj.getString("buyer_id"));
                    product.setShop_code(obj.getString("shop_code"));
                    product.setAuto_guid(obj.getString("auto_guid"));
                    product.setCustomer_name(obj.getString("customer_name"));
                    product.setFirm_name(obj.getString("firm_name"));
                    product.setCustomer_mobileno(obj.getString("customer_mobileno"));
                    product.setAddress_line_1(obj.getString("address_line_1"));
                    product.setAddress_line_2(obj.getString("address_line_2"));
                    product.setAddress_city_name(obj.getString("address_city_name"));
                    product.setAddress_state_name(obj.getString("address_state_name"));
                    product.setAddress_pincode(obj.getString("address_pincode"));
                    product.setBalance_amount(obj.getString("balance_amount"));
                    product.setCredit_amount(obj.getString("credit_amount"));
                    product.setDebit_amount(obj.getString("debit_amount"));
                    product.setTotal_amt(obj.getString("total_amt"));
                    product.setTotal_debit(obj.getString("total_debit"));
                    product.setCredit_limit(obj.getString("credit_limit"));
                    shopModels.add(product);
                }
                shopAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }

}
