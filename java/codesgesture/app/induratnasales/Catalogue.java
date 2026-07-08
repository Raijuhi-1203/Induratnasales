package codesgesture.app.induratnasales;

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

import codesgesture.app.induratnasales.Adapter.CategoryAdapter;
import codesgesture.app.induratnasales.Models.CategoryModel;
import codesgesture.app.induratnasales.Models.ShopModel;
import codesgesture.app.induratnasales.Services.CallJson;
import codesgesture.app.induratnasales.Services.CallJsonWithoutProgress;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;

public class Catalogue extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayout norecord;
    EditText search;
    CategoryAdapter categoryAdapter;
    ArrayList<CategoryModel> categoryModels=new ArrayList<>();
    String s;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogue);
        s=getString(R.string.con);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("Categories");


        search=findViewById(R.id.search);
        norecord=findViewById(R.id.norecord);
        recyclerView=findViewById(R.id.recyclerView);

        GridLayoutManager mLayoutManager = new GridLayoutManager(Catalogue.this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        categoryAdapter = new CategoryAdapter(Catalogue.this, categoryModels,R.layout.item_category);
        recyclerView.setAdapter(categoryAdapter);
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
        ArrayList<CategoryModel> temp = new ArrayList();
        for (CategoryModel d : categoryModels) {
            if(d!=null){
                if (d.getCategory_name().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                }}
        }
        categoryAdapter.updateList(temp);
    }

    private void GetData() {
        categoryModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(Catalogue.this);
        jc.SendRequest(s,"get_category", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    CategoryModel product = new CategoryModel();
                    product.setCategory_name(obj.getString("category_name"));
                    product.setCategory_id(obj.getString("category_id"));
                    product.setCategory_photo(obj.getString("category_photo"));
                    categoryModels.add(product);
                }
                categoryAdapter.notifyDataSetChanged();
                BindDataView();
            }

            @Override
            public void onPostError(String msg) {
                BindDataView();
            }
        }, "", "Loading..");
    }

    private void BindDataView() {
        if(categoryModels.size()>0){
            norecord.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        else{
            norecord.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

}
