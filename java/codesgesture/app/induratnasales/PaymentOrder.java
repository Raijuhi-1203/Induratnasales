package codesgesture.app.induratnasales;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Models.ProductModel;
import codesgesture.app.induratnasales.Models.SalesPersonModel;
import codesgesture.app.induratnasales.Models.ShopModel;
import codesgesture.app.induratnasales.Services.CallJsonWithoutProgress;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.Utils.SessionManage;

public class PaymentOrder extends AppCompatActivity {
    ArrayList<ProductModel> productModel;
    ShopModel shopModel;

    Button bt_save;
    Spinner sptypepay;
    EditText amt;
    String s,total_order_amount;
    SalesPersonModel salesPersonModel;

    TextView txchequeno,txbanknm,txacholdernm,txtransid,txupinm;
    EditText chequeno,banknm,acholdernm,trans_id,upiname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_order);
        salesPersonModel=(SalesPersonModel) SessionManage.getCurrentUser(getApplicationContext());
        productModel=(ArrayList<ProductModel>)getIntent().getSerializableExtra("productModels");
        shopModel=(ShopModel) getIntent().getSerializableExtra("shopModel");
        total_order_amount=getIntent().getStringExtra("total_order_amount");
        s=getString(R.string.con);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("Order Payment");

        sptypepay=findViewById(R.id.sptypepay);
        amt=findViewById(R.id.amt);
        amt.setText(total_order_amount);
        bt_save=findViewById(R.id.bt_save);

        txchequeno=findViewById(R.id.txchequeno);txbanknm=findViewById(R.id.txbanknm);
        txacholdernm=findViewById(R.id.txacholdernm);txtransid=findViewById(R.id.txtransid);
        txupinm=findViewById(R.id.txupinm);

        chequeno=findViewById(R.id.chequeno);banknm=findViewById(R.id.banknm);
        acholdernm=findViewById(R.id.acholdernm);trans_id=findViewById(R.id.trans_id);
        upiname=findViewById(R.id.upiname);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pay_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptypepay.setAdapter(adapter);

        sptypepay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mode = sptypepay.getSelectedItem().toString();
                if (mode.equals("UPI")){
                    txupinm.setVisibility(View.VISIBLE);upiname.setVisibility(View.VISIBLE);
                    txbanknm.setVisibility(View.GONE); banknm.setVisibility(View.GONE);
                    txacholdernm.setVisibility(View.GONE);acholdernm.setVisibility(View.GONE);
                    txtransid.setVisibility(View.GONE);trans_id.setVisibility(View.GONE);
                    txchequeno.setVisibility(View.GONE);chequeno.setVisibility(View.GONE);
                } else if (mode.equals("Cash")) {
                    txupinm.setVisibility(View.GONE);upiname.setVisibility(View.GONE);
                    txbanknm.setVisibility(View.GONE); banknm.setVisibility(View.GONE);
                    txacholdernm.setVisibility(View.GONE);acholdernm.setVisibility(View.GONE);
                    txtransid.setVisibility(View.GONE);trans_id.setVisibility(View.GONE);
                    txchequeno.setVisibility(View.GONE);chequeno.setVisibility(View.GONE);
                }else if (mode.equals("Online Transfer")) {
                    txupinm.setVisibility(View.GONE);upiname.setVisibility(View.GONE);
                    txbanknm.setVisibility(View.GONE); banknm.setVisibility(View.GONE);
                    txacholdernm.setVisibility(View.GONE);acholdernm.setVisibility(View.GONE);
                    txtransid.setVisibility(View.VISIBLE);trans_id.setVisibility(View.VISIBLE);
                    txchequeno.setVisibility(View.GONE);chequeno.setVisibility(View.GONE);
                }else if (mode.equals("Cheque")) {
                    txupinm.setVisibility(View.GONE);upiname.setVisibility(View.GONE);
                    txbanknm.setVisibility(View.VISIBLE); banknm.setVisibility(View.VISIBLE);
                    txacholdernm.setVisibility(View.VISIBLE);acholdernm.setVisibility(View.VISIBLE);
                    txtransid.setVisibility(View.GONE);trans_id.setVisibility(View.GONE);
                    txchequeno.setVisibility(View.VISIBLE);chequeno.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amt.getText().length()==0){
                    amt.setError("Please enter amount");
                }else {
                    SubmitOrder();
                }
            }
        });

    }

    private void SubmitOrder() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(PaymentOrder.this);
        String item = new Gson().toJson(productModel);
        String shop = new Gson().toJson(shopModel);
        shop="["+shop+"]";
        param.add(new NetParam("address",shop));
        param.add(new NetParam("items",item));
        param.add(new NetParam("total_order_amount",total_order_amount));
        param.add(new NetParam("sales_id",salesPersonModel.getSales_id()));
        param.add(new NetParam("sales_auto_guid",salesPersonModel.getAuto_guid()));
        param.add(new NetParam("payment_mode", sptypepay.getSelectedItem().toString()));
        param.add(new NetParam("chequeno",chequeno.getText().toString()));
        param.add(new NetParam("acholdernm",acholdernm.getText().toString()));
        param.add(new NetParam("trans_id",trans_id.getText().toString()));
        param.add(new NetParam("banknm",banknm.getText().toString()));
        param.add(new NetParam("upiname",upiname.getText().toString()));
        jc.SendRequest(this.s,"addorder", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                Intent intent=new Intent(PaymentOrder.this,PageSucessfull.class);
                intent.putExtra("shopModel",shopModel);
                intent.putExtra("productModels",productModel);
                startActivity(intent);
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }

}
