package codesgesture.app.induratnasales;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.induratnasales.Adapter.CartAdapter;
import codesgesture.app.induratnasales.Models.ProductModel;
import codesgesture.app.induratnasales.Models.SalesPersonModel;
import codesgesture.app.induratnasales.Models.ShopModel;
import codesgesture.app.induratnasales.Services.CallJsonWithoutProgress;
import codesgesture.app.induratnasales.Services.JsonCallbacks;
import codesgesture.app.induratnasales.Services.NetParam;
import codesgesture.app.induratnasales.Services.UserUtil;
import codesgesture.app.induratnasales.Utils.SessionManage;
import codesgesture.app.induratnasales.interfaces.Notify;

public class PageCart extends AppCompatActivity {

    ArrayList<ProductModel> productModels=new ArrayList<>();
    CartAdapter productAdapter;
    RecyclerView recyclerView;
    EditText search;
    String s;
    ShopModel shopModel;
    TextView cart_total,btorder;
    double total;
    String price_total;
    SalesPersonModel salesPersonModel;

    LinearLayout norecord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_cart);
        salesPersonModel=(SalesPersonModel) SessionManage.getCurrentUser(getApplicationContext());
        shopModel=(ShopModel)getIntent().getSerializableExtra("data");
        s=getString(R.string.con);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("Cart");

        cart_total=findViewById(R.id.total);
        btorder=findViewById(R.id.btorder);
        norecord=findViewById(R.id.norecord);
        btorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total==0){
                    UserUtil.ShowMsg("Sorry ! Your cart is Empty.", PageCart.this);
                }else {
                    AddOrder();
                }
            }
        });

        recyclerView=findViewById(R.id.recyclerView);
        search=findViewById(R.id.search);

        GridLayoutManager mLayoutManager = new GridLayoutManager(PageCart.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        productAdapter = new CartAdapter(PageCart.this, productModels, R.layout.item_cart, new Notify() {
            @Override
            public void onAdd(ProductModel data) {
                GetData();
            }

            @Override
            public void onRemove(ProductModel data) {
                GetData();
            }
        },shopModel.getShop_code(),total);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setItemViewCacheSize(productModels.size());
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

    private void AddOrder() {
        Intent intent=new Intent(PageCart.this,PaymentOrder.class);
        intent.putExtra("productModels",productModels);
        intent.putExtra("shopModel",shopModel);
        intent.putExtra("total_order_amount",String.valueOf(total));
        startActivity(intent);

//        final Dialog dialog = new Dialog(PageCart.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.dialog_payment);
//
//        Spinner sptypepay=dialog.findViewById(R.id.sptypepay);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pay_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sptypepay.setAdapter(adapter);
//
//        ImageView btclose=dialog.findViewById(R.id.btclose);
//        btclose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        Button dialogButton = (Button) dialog.findViewById(R.id.bt_save);
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (sptypepay.getSelectedItem().toString().length()==0){
//
//                }else {
//                    SubmitOrder(sptypepay.getSelectedItem().toString());
//                    dialog.show();
//                }
//                dialog.dismiss();
//            }
//        });
//        dialog.show();

    }

    private void SubmitOrder(String s) {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(PageCart.this);
        String item = new Gson().toJson(productModels);
        String shop = new Gson().toJson(shopModel);
        shop="["+shop+"]";
        param.add(new NetParam("address",shop));
        param.add(new NetParam("items",item));
        param.add(new NetParam("total_order_amount",String.valueOf(total)));
        param.add(new NetParam("sales_id",salesPersonModel.getSales_id()));
        param.add(new NetParam("sales_auto_guid",salesPersonModel.getAuto_guid()));
        param.add(new NetParam("payment_mode",s));
        jc.SendRequest(this.s,"addorder", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                Intent intent=new Intent(PageCart.this,PageSucessfull.class);
                intent.putExtra("shopModel",shopModel);
                intent.putExtra("productModels",productModels);
                startActivity(intent);
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }

    void filter(String text) {
        ArrayList<ProductModel> temp = new ArrayList();
        for (ProductModel d : productModels) {
            if(d!=null){
                if (d.getProduct_full_name().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                }}
        }
        productAdapter.updateList(temp);
    }

    private void GetData() {
        productModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(PageCart.this);
        param.add(new NetParam("auto_guid",shopModel.getShop_code()));
        jc.SendRequest(s,"get_cart", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    ProductModel product = new ProductModel();
                    product.setId(obj.getString("id"));
                    product.setAuto_guid(obj.getString("auto_guid"));
                    product.setCart_qty(Integer.parseInt(obj.getString("cart_qty")));
                    product.setQty(Integer.parseInt(obj.getString("cart_qty")));
                    product.setProduct_id(obj.getString("product_id"));
                    product.setProduct_short_name(obj.getString("product_short_name"));
                    product.setProduct_full_name(obj.getString("product_full_name"));
                    product.setProduct_description(obj.getString("product_description"));
                    product.setProduct_barcode(obj.getString("product_barcode"));
                    product.setProduct_sku(obj.getString("product_sku"));
                    product.setProduct_hsnORsac(obj.getString("product_hsnORsac"));
                    product.setProduct_parent_category_id(obj.getString("product_parent_category_id"));
                    product.setProduct_parent_category_name(obj.getString("product_parent_category_name"));
                    product.setProduct_sub_category_id(obj.getString("product_sub_category_id"));
                    product.setProduct_sub_category_name(obj.getString("product_sub_category_name"));
                    product.setProduct_unit(obj.getString("product_unit"));
                    product.setProduct_unit_value(obj.getString("product_unit_value"));
                    product.setProduct_GST_type(obj.getString("product_GST_type"));
                    product.setProduct_GST_rate(obj.getString("product_GST_rate"));
                    product.setProduct_GST_percentage(obj.getString("product_GST_percentage"));
                    product.setProduct_tax_type(obj.getString("product_tax_type"));
                    product.setProduct_CGST_percentage(obj.getString("product_CGST_percentage"));
                    product.setProduct_CGST_rate(obj.getString("product_CGST_rate"));
                    product.setProduct_SGST_percentage(obj.getString("product_SGST_percentage"));
                    product.setProduct_SGST_rate(obj.getString("product_SGST_rate"));
                    product.setProduct_IGST_percentage(obj.getString("product_IGST_percentage"));
                    product.setProduct_IGST_rate(obj.getString("product_IGST_rate"));
                    product.setProduct_market_price(obj.getString("product_market_price"));
                    product.setProduct_discount_percentage(obj.getString("product_discount_percentage"));
                    product.setProduct_discount_price(obj.getString("product_discount_price"));
                    product.setProduct_with_gst_Price(obj.getString("product_with_gst_Price"));
                    product.setProduct_final_sell_price(obj.getString("product_final_sell_price"));
                    product.setProduct_shipping_charge(obj.getString("product_shipping_charge"));
                    product.setProduct_stock(obj.getString("product_stock"));
                    product.setDebit_amount(obj.getString("debit_amount"));
                    product.setTotal_amt(obj.getString("total_amt"));
                    product.setId1(obj.getString("id1"));
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
        if(productModels.size()>0){
            norecord.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        else{
            norecord.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    private void TotalAmount() {
        Double totalamount = 0.0;
        Double mtotalamount = 0.0;
        for (ProductModel cart : productModels) {
            totalamount += (Double.parseDouble(cart.getProduct_final_sell_price()) * cart.getCart_qty());
            mtotalamount += (Double.parseDouble(cart.getProduct_market_price()) * cart.getCart_qty());
        }
        String price =String.valueOf(String.format("%.02f",totalamount));
        String mprice =String.valueOf(String.format("%.02f",mtotalamount));
        cart_total.setText("₹ "+price);
        total=totalamount;
        price_total=mprice;
    }

}
