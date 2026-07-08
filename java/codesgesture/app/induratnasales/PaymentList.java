package codesgesture.app.induratnasales;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;

import codesgesture.app.induratnasales.Adapter.PaymentAdapter;
import codesgesture.app.induratnasales.Adapter.ProductAdapter;
import codesgesture.app.induratnasales.Models.PaymentModel;
import codesgesture.app.induratnasales.Models.ProductModel;
import codesgesture.app.induratnasales.Models.SalesPersonModel;
import codesgesture.app.induratnasales.Models.ShopModel;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.Utils.SessionManage;
import codesgesture.app.induratnasales.interfaces.Notify;

public class PaymentList extends AppCompatActivity {
    ArrayList<PaymentModel> productModels=new ArrayList<>();
    PaymentAdapter productAdapter;
    RecyclerView recyclerView;
    EditText search;
    String s;
    SalesPersonModel salesPersonModel;
    EditText todt,fromdt;
    Button btnfind;
    int year;
    int month;
    int day;
    LinearLayout norecord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_list);
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
        title.setText("PaymentList");

        recyclerView=findViewById(R.id.recyclerView);
        search=findViewById(R.id.search);
        fromdt=findViewById(R.id.fromdt);
        todt=findViewById(R.id.todt);
        btnfind=findViewById(R.id.btnfind);
        norecord=findViewById(R.id.norecord);

        GridLayoutManager mLayoutManager = new GridLayoutManager(PaymentList.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        productAdapter = new PaymentAdapter(PaymentList.this, productModels, R.layout.item_payment);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setItemViewCacheSize(productModels.size());

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

        btnfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todt.getText().length()==0){
                   todt.setError("Please select to date.");
                }else if (fromdt.getText().length()==0){
                    fromdt.setError("Please select from date.");
                }else {
                    GetData();
                }
            }
        });

        fromdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                day   = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                month = mcurrentDate.get(Calendar.MONTH);
                year  = mcurrentDate.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(PaymentList.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;

                        if(month < 10 && dayOfMonth < 10){
                            fromdt.setText(year + "-0" + month + "-0" + dayOfMonth);
                        }
                        else if (month<10 && dayOfMonth > 10) {
                            fromdt.setText(year + "-0" + month + "-" + dayOfMonth);
                        }
                        else if (dayOfMonth<10 && month > 10) {
                            fromdt.setText(year + "-" + month + "-0" + dayOfMonth);
                        }
                        else {
                            fromdt.setText(year + "-" + month + "-" + dayOfMonth);
                        }

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        todt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                day   = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                month = mcurrentDate.get(Calendar.MONTH);
                year  = mcurrentDate.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(PaymentList.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        if(month < 10 && dayOfMonth < 10){
                            todt.setText(year + "-0" + month + "-0" + dayOfMonth);
                        }
                        else if (month<10 && dayOfMonth > 10) {
                            todt.setText(year + "-0" + month + "-" + dayOfMonth);
                        }
                        else if (dayOfMonth<10 && month > 10) {
                            todt.setText(year + "-" + month + "-0" + dayOfMonth);
                        }
                        else {
                            todt.setText(year + "-" + month + "-" + dayOfMonth);
                        }

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

    }

    void filter(String text) {
        ArrayList<PaymentModel> temp = new ArrayList();
        for (PaymentModel d : productModels) {
            if(d!=null){
                if (d.getFirm_name().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                }}
        }
        productAdapter.updateList(temp);
    }

    private void GetData() {
        productModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PaymentList.this);
        param.add(new NetParam("sales_id",salesPersonModel.getSales_id()));
        param.add(new NetParam("fromdt",fromdt.getText().toString()));
        param.add(new NetParam("todt",todt.getText().toString()));
        jc.SendRequest(s,"paymentlist", param, new JsonCallbacks() {
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
                    productModels.add(product);
                }
                productAdapter.notifyDataSetChanged();
                BindDataView();
            }

            @Override
            public void onPostError(String msg) {
                BindDataView();
            }
        }, "", "Loading..");
    }

    private void BindDataView() {
        if(productModels.size()>0){
            norecord.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        else{
            norecord.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

}
