package codesgesture.app.induratnasales;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Models.SalesPersonModel;
import codesgesture.app.induratnasales.Models.ShopModel;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.Services.UserUtil;
import codesgesture.app.induratnasales.Utils.SessionManage;

public class AddPayment extends AppCompatActivity {
    Button bt_save;
    Spinner sptypepay,spnrshop;
    EditText amt;
    ArrayAdapter<ShopModel> shopModelArrayAdapter;
    ArrayList<ShopModel> shopModels=new ArrayList<>();
    String s,firmname,buyerid,total_order_amount;
    SalesPersonModel salesPersonModel;

    TextView txchequeno,txbanknm,txacholdernm,txtransid,txupinm;
    EditText chequeno,banknm,acholdernm,trans_id,upiname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_payment);
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
        title.setText("Add Payment");

        spnrshop=findViewById(R.id.spnrshop);
        sptypepay=findViewById(R.id.sptypepay);
        amt=findViewById(R.id.amt);
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

        shopModels = new ArrayList<>();
        shopModelArrayAdapter = new ArrayAdapter<ShopModel>(AddPayment.this, android.R.layout.simple_spinner_item, shopModels);
        shopModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrshop.setAdapter(shopModelArrayAdapter);
        AreaJsonCall();

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

        spnrshop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = spnrshop.getSelectedItemPosition();
                buyerid=String.valueOf(shopModels.get(pos).getBuyer_id());
                firmname=String.valueOf(shopModels.get(pos).getFirm_name());
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
                    SubmitPayment();
                }
            }
        });

    }

    private void SubmitPayment() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(AddPayment.this);
        param.add(new NetParam("buyer_id",buyerid));
        param.add(new NetParam("firm_name",firmname));
        param.add(new NetParam("credit_amount",amt.getText().toString()));
        param.add(new NetParam("payment_mode",sptypepay.getSelectedItem().toString()));

        param.add(new NetParam("chequeno",chequeno.getText().toString()));
        param.add(new NetParam("acholdernm",acholdernm.getText().toString()));
        param.add(new NetParam("trans_id",trans_id.getText().toString()));
        param.add(new NetParam("banknm",banknm.getText().toString()));
        param.add(new NetParam("upiname",upiname.getText().toString()));

        jc.SendRequest(s,"addpayment", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Payment Added.",AddPayment.this);
                finish();
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "", "Please wait while getting..");
    }

    private void AreaJsonCall() {
        shopModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(AddPayment.this);
        param.add(new NetParam("sales_id",salesPersonModel.getSales_id()));
        jc.SendRequest(s,"get_shop", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    ShopModel mod = new ShopModel();
                    mod.setFirm_name(obj.getString("firm_name"));
                    mod.setBuyer_id(obj.getString("buyer_id"));
                    shopModels.add(mod);
                    shopModelArrayAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "", "Please wait while getting..");
    }



}
