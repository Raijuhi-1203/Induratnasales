package codesgesture.app.induratnasales;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import codesgesture.app.induratnasales.Adapter.LedgerAdapter;
import codesgesture.app.induratnasales.Adapter.OrderAdapter;
import codesgesture.app.induratnasales.Models.OrderModel;
import codesgesture.app.induratnasales.Models.PaymentModel;
import codesgesture.app.induratnasales.Models.SalesPersonModel;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.Utils.SessionManage;

public class DateWiseLedger extends AppCompatActivity {

    EditText datepicker;
    Button btnsubmit;
    String s;
    SalesPersonModel salesPersonModel;
    LinearLayout norecord;
    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    ArrayList<OrderModel> orderModels=new ArrayList<>();
    int year;
    int month;
    int day;
    ArrayList<PaymentModel> productModels=new ArrayList<>();
    LedgerAdapter productAdapter;
    TextView debit,credit,blnc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datewise_ledger);
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
        title.setText("Datewise Ledger");

        norecord=findViewById(R.id.norecord);
        recyclerView=findViewById(R.id.recyclerView);
        datepicker=findViewById(R.id.datepicker);
        btnsubmit=findViewById(R.id.btnsubmit);
        debit=findViewById(R.id.debit);
        credit=findViewById(R.id.credit);
        blnc=findViewById(R.id.blnc);

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                day   = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                month = mcurrentDate.get(Calendar.MONTH);
                year  = mcurrentDate.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(DateWiseLedger.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        if(month < 10 && dayOfMonth < 10){
                            datepicker.setText(year + "-0" + month + "-0" + dayOfMonth);
                        }
                        else if (month<10 && dayOfMonth > 10) {
                            datepicker.setText(year + "-0" + month + "-" + dayOfMonth);
                        }
                        else if (dayOfMonth<10 && month > 10) {
                            datepicker.setText(year + "-" + month + "-0" + dayOfMonth);
                        }
                        else {
                            datepicker.setText(year + "-" + month + "-" + dayOfMonth);
                        }

                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datepicker.getText().length()==0){
                    datepicker.setError("Please select date");
                }else {
                    Bindata();
                }
            }
        });

        GridLayoutManager mLayoutManager = new GridLayoutManager(DateWiseLedger.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        productAdapter = new LedgerAdapter(DateWiseLedger.this, productModels, R.layout.item_ledger);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setItemViewCacheSize(productModels.size());
       // Bindata();

    }

    private void Bindata() {
        productModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(DateWiseLedger.this);
        param.add(new NetParam("transaction_date",datepicker.getText().toString()));
        param.add(new NetParam("sales_id",salesPersonModel.getSales_id()));
        jc.SendRequest(s,"date_ledger", param, new JsonCallbacks() {
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
                BindDataView();
            }

            @Override
            public void onPostError(String msg) {
                BindDataView();
            }
        }, "", "Loading..");
    }

    private void BindDataView() {
        if(orderModels.size()>0){
            norecord.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else{
            norecord.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
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
