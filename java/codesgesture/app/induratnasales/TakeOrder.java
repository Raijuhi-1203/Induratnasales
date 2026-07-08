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

import codesgesture.app.induratnasales.Adapter.ShopAdapter;
import codesgesture.app.induratnasales.Models.SalesPersonModel;
import codesgesture.app.induratnasales.Models.ShopModel;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.Utils.SessionManage;

public class TakeOrder extends AppCompatActivity {
    ArrayList<ShopModel> shopModels=new ArrayList<>();
    ShopAdapter shopAdapter;
    RecyclerView recyclerView;
    String s;
    EditText search;
    SalesPersonModel salesPersonModel;
    LinearLayout norecord;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_order);
        salesPersonModel=(SalesPersonModel) SessionManage.getCurrentUser(getApplicationContext());
        s=getString(R.string.con);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("Take-Order");

        recyclerView=findViewById(R.id.recyclerView);
        search=findViewById(R.id.search);
        norecord=findViewById(R.id.norecord);
        GridLayoutManager mLayoutManager = new GridLayoutManager(TakeOrder.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        shopAdapter = new ShopAdapter(TakeOrder.this, shopModels, R.layout.item_shop);
        recyclerView.setAdapter(shopAdapter);
        recyclerView.setItemViewCacheSize(shopModels.size());
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
        ArrayList<ShopModel> temp = new ArrayList();
        for (ShopModel d : shopModels) {
            if(d!=null){
                if (d.getFirm_name().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                }}
        }
        shopAdapter.updateList(temp);
    }

    private void GetData() {
        shopModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(TakeOrder.this);
        param.add(new NetParam("sales_id",salesPersonModel.getSales_id()));
        jc.SendRequest(s,"get_shop", param, new JsonCallbacks() {
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
                BindDataView();
            }

            @Override
            public void onPostError(String msg) {
                BindDataView();
            }
        }, "", "Loading..");
    }

    private void BindDataView() {
        if(shopModels.size()>0){
            norecord.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        else{
            norecord.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

}
