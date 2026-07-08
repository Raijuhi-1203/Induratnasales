package codesgesture.app.induratnasales;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import codesgesture.app.induratnasales.Adapter.LedgerAdapter;
import codesgesture.app.induratnasales.Adapter.PaymentAdapter;
import codesgesture.app.induratnasales.Models.PaymentModel;
import codesgesture.app.induratnasales.Models.ProductModel;
import codesgesture.app.induratnasales.Models.SalesPersonModel;
import codesgesture.app.induratnasales.Models.ShopModel;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.Utils.SessionManage;

public class ShopLedger extends AppCompatActivity {
    SalesPersonModel salesPersonModel;
    String s,firmname,buyerid,scode,mobile,scname;
    RecyclerView recyclerView;
    Spinner spnrshop;
    ArrayAdapter<ShopModel> shopModelArrayAdapter;
    ArrayList<ShopModel> shopModels=new ArrayList<>();
    ArrayList<PaymentModel> productModels=new ArrayList<>();
    LedgerAdapter productAdapter;
    TextView debit,credit,blnc,acnm,fnm,mob;
    EditText datepickerto,datepickerfrom;
    int year,month,day;
    Button btnsubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_ledger);
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
        title.setText("Shopwise Ledger");

        recyclerView=findViewById(R.id.recyclerView);
        debit=findViewById(R.id.debit);
        credit=findViewById(R.id.credit);
        blnc=findViewById(R.id.blnc);
        acnm=findViewById(R.id.acnm);
        fnm=findViewById(R.id.fnm);
        mob=findViewById(R.id.mob);
        datepickerto=findViewById(R.id.datepickerto);
        datepickerfrom=findViewById(R.id.datepickerfrom);
        btnsubmit=findViewById(R.id.btnsubmit);

        spnrshop=findViewById(R.id.spnrshop);
        shopModels = new ArrayList<>();
        shopModelArrayAdapter = new ArrayAdapter<ShopModel>(ShopLedger.this, android.R.layout.simple_spinner_item, shopModels);
        shopModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrshop.setAdapter(shopModelArrayAdapter);
        AreaJsonCall();

        spnrshop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = spnrshop.getSelectedItemPosition();
                buyerid=String.valueOf(shopModels.get(pos).getBuyer_id());
                firmname=String.valueOf(shopModels.get(pos).getFirm_name());
                scode=String.valueOf(shopModels.get(pos).getShop_code());
                mobile=String.valueOf(shopModels.get(pos).getCustomer_mobileno());
                scname=String.valueOf(shopModels.get(pos).getBank_ac_holder_name());
                fnm.setText(firmname+" ("+scode+")");
                acnm.setText(scname);
                mob.setText(mobile);
                Bindata(buyerid);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        GridLayoutManager mLayoutManager = new GridLayoutManager(ShopLedger.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        productAdapter = new LedgerAdapter(ShopLedger.this, productModels, R.layout.item_ledger);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setItemViewCacheSize(productModels.size());

        datepickerto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                day   = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                month = mcurrentDate.get(Calendar.MONTH);
                year  = mcurrentDate.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ShopLedger.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        if(month < 10 && dayOfMonth < 10){
                            datepickerto.setText(year + "-0" + month + "-0" + dayOfMonth);
                        }
                        else if (month<10 && dayOfMonth > 10) {
                            datepickerto.setText(year + "-0" + month + "-" + dayOfMonth);
                        }
                        else if (dayOfMonth<10 && month > 10) {
                            datepickerto.setText(year + "-" + month + "-0" + dayOfMonth);
                        }
                        else {
                            datepickerto.setText(year + "-" + month + "-" + dayOfMonth);
                        }

                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        datepickerfrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                day   = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                month = mcurrentDate.get(Calendar.MONTH);
                year  = mcurrentDate.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ShopLedger.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        if(month < 10 && dayOfMonth < 10){
                            datepickerfrom.setText(year + "-0" + month + "-0" + dayOfMonth);
                        }
                        else if (month<10 && dayOfMonth > 10) {
                            datepickerfrom.setText(year + "-0" + month + "-" + dayOfMonth);
                        }
                        else if (dayOfMonth<10 && month > 10) {
                            datepickerfrom.setText(year + "-" + month + "-0" + dayOfMonth);
                        }
                        else {
                            datepickerfrom.setText(year + "-" + month + "-" + dayOfMonth);
                        }

                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datepickerto.getText().length()==0){
                    datepickerto.setError("Please select date");
                }else if (datepickerfrom.getText().length()==0){
                    datepickerfrom.setError("Please select date");
                }
                else {
                    Bindata(buyerid);
                }
            }
        });

    }

    private void Bindata(String buyerid) {
        productModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(ShopLedger.this);
        param.add(new NetParam("buyer_id",buyerid));
        param.add(new NetParam("sales_id",salesPersonModel.getSales_id()));
        param.add(new NetParam("transaction_date",datepickerfrom.getText().toString()));
        param.add(new NetParam("transaction_date1",datepickerto.getText().toString()));
        jc.SendRequest(s,"shop_ledger", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    PaymentModel product = new PaymentModel();
                    product.setId(obj.getString("id"));
                    product.setFirm_name(obj.getString("firm_name"));
                    product.setDebit_amount(obj.getString("debit_amount"));
                    product.setCredit_amount(obj.getString("credit_amount"));
                    product.setTransaction_type(obj.getString("transaction_type"));
                    product.setTransaction_section(obj.getString("transaction_section"));
                    product.setPayment_mode(obj.getString("payment_mode"));
                    product.setTransaction_date(obj.getString("transaction_date"));
                    product.setBalance(obj.getString("balance"));
                    productModels.add(product);
                }
                TotalAmount();
                productAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }

    private void AreaJsonCall() {
        shopModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(ShopLedger.this);
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
                    product.setBank_ac_holder_name(obj.getString("bank_ac_holder_name"));
                    shopModels.add(product);
                    shopModelArrayAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "", "Please wait while getting..");
    }


    private void TotalAmount() {
        Double totalamount = 0.0;
        Double mtotalamount = 0.0;
        Double btotalamount = 0.0;
        for (PaymentModel cart : productModels) {
            totalamount += (Double.parseDouble(cart.getCredit_amount()));
            mtotalamount += (Double.parseDouble(cart.getDebit_amount()));
            btotalamount += (Double.parseDouble(cart.getBalance()));
        }
        String price =String.valueOf(String.format("%.02f",totalamount));
        String mprice =String.valueOf(String.format("%.02f",mtotalamount));
        String bprice =String.valueOf(String.format("%.02f",btotalamount));
        credit.setText(price);
        debit.setText(mprice);
        blnc.setText(bprice);

    }

}
