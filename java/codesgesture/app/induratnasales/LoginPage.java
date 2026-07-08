package codesgesture.app.induratnasales;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Models.SalesPersonModel;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.Services.UserUtil;
import codesgesture.app.induratnasales.Services.Utility;
import codesgesture.app.induratnasales.Utils.SessionManage;

public class LoginPage extends AppCompatActivity implements JsonCallbacks {
    String s;
    SalesPersonModel salesPersonModel;
    Button btnsubmit;
    EditText mob,pwd;
    ArrayList<NetParam> param;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        s=getString(R.string.con);

        btnsubmit=findViewById(R.id.btnsubmit);
        mob=findViewById(R.id.mob);
        pwd=findViewById(R.id.pwd);

        CheckLogin();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mob.getText().length()==0){
                    mob.setError("Please enter mobile no");
                } else if (pwd.getText().length()==0) {
                    pwd.setError("please enter password");
                }else {
                    param = new ArrayList<NetParam>();
                    CallJson jc = new CallJson(LoginPage.this);
                    param.add(new NetParam("mobileno", mob.getText().toString()));
                    param.add(new NetParam("PASSWORD", pwd.getText().toString()));
                    jc.SendRequest(s,"loginprocess", param, LoginPage.this, "loginprocess", "Please wait while verifying..");
                }
            }
        });
    }

    @Override
    public void onPostSuceess(String json, String method) throws JSONException {
        SalesPersonModel sd = new SalesPersonModel();
        try {
            JSONObject obj = UserUtil.ConvertStringToJsonObject(json);
            if (obj.length() != 1) {
                sd.setSales_id(obj.getString("sales_id"));
                sd.setId(obj.getString("id"));
                sd.setSales_name(obj.getString("sales_name"));
                sd.setSales_address_line_1(obj.getString("sales_address_line_1"));
                sd.setSales_address_line_2(obj.getString("sales_address_line_2"));
                sd.setSales_state_name(obj.getString("sales_state_name"));
                sd.setSales_city_name(obj.getString("sales_city_name"));
                sd.setSales_email(obj.getString("sales_email"));
                sd.setSales_mobileno(obj.getString("sales_mobileno"));
                sd.setSales_gender(obj.getString("sales_gender"));
                SessionManage.SetCustomerSessions(getApplicationContext(), sd);
                Intent act = new Intent(LoginPage.this, Dashboard.class);
                startActivity(act);
                UserUtil.ShowMsg("Login Successfully !", LoginPage.this);
                finish();
            } else {
                Utility.ShowMEssage(LoginPage.this, "Invalid Login user and Password!");
            }
        } catch (JSONException e) {
            Utility.ShowMEssage(LoginPage.this, "Invalid Login user and Password!");
            e.printStackTrace();
        }
    }

    @Override
    public void onPostError(String msg) {

    }

    private void CheckLogin() {
        salesPersonModel = SessionManage.getCurrentUser(this);
        if (salesPersonModel != null) {
            if (salesPersonModel.getSales_id() != null) {
                Intent act = new Intent(LoginPage.this, Dashboard.class);
                startActivity(act);
                finish();
            }
        }
    }
}
