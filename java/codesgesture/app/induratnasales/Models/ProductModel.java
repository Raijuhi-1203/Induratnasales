package codesgesture.app.induratnasales.Models;

import java.io.Serializable;

public class ProductModel implements Serializable  {
    private int cart_qty;
    private String id;
    private String product_temp_id;
    private String product_id;
    private String product_short_name = null;
    private String product_full_name;
    private String product_description;
    private String product_barcode;
    private String product_sku;
    private String product_hsnORsac;
    private String product_parent_category_id;
    private String product_parent_category_name;
    private String product_sub_category_id;
    private String product_sub_category_name;
    private String verticle_id;
    private String verticle_name;
    private String product_brand_name;
    private String product_photo = null;
    private String product_full_description;
    private String publish_status;
    private String product_postion_no;
    private String country_of_origin;
    private String meta_title = null;
    private String meta_description = null;
    private String meta_keyword = null;
    private String variants_id = null;
    private String isApproved;
    private String auto_guid;
    private String create_date;
    private String create_time;
    private String last_update_date;
    private String last_update_time;
    private String id1;
    private String product_id1;
    private String product_unit;
    private String product_unit_value;
    private String product_GST_type;
    private String product_tax_type;
    private String product_GST_percentage;
    private String product_GST_rate;
    private String product_CGST_percentage;
    private String product_CGST_rate;
    private String product_SGST_percentage;
    private String product_SGST_rate;
    private String product_IGST_percentage = null;
    private String product_IGST_rate = null;
    private String product_market_price;
    private String product_sell_price;
    private String product_discount_percentage;
    private String product_discount_price;
    private String product_with_gst_Price;
    private String product_final_sell_price;
    private String product_shipping_charge;
    private String product_stock;
    private String auto_guid1 = null;
    private String create_date1 = null;
    private String create_time1 = null;
    private String last_update_date1 = null;
    private String last_update_time1 = null;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    private int qty;

    public String getDebit_amount() {
        return debit_amount;
    }

    public void setDebit_amount(String debit_amount) {
        this.debit_amount = debit_amount;
    }

    private String debit_amount = null;

    private String total_debit ;

    public String getTotal_debit() {
        return total_debit;
    }

    public void setTotal_debit(String total_debit) {
        this.total_debit = total_debit;
    }

    public String getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(String total_amt) {
        this.total_amt = total_amt;
    }

    private String total_amt ;

    // Getter Methods

    public int getCart_qty() {
        return cart_qty;
    }

    public String getId() {
        return id;
    }

    public String getProduct_temp_id() {
        return product_temp_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_short_name() {
        return product_short_name;
    }

    public String getProduct_full_name() {
        return product_full_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public String getProduct_barcode() {
        return product_barcode;
    }

    public String getProduct_sku() {
        return product_sku;
    }

    public String getProduct_hsnORsac() {
        return product_hsnORsac;
    }

    public String getProduct_parent_category_id() {
        return product_parent_category_id;
    }

    public String getProduct_parent_category_name() {
        return product_parent_category_name;
    }

    public String getProduct_sub_category_id() {
        return product_sub_category_id;
    }

    public String getProduct_sub_category_name() {
        return product_sub_category_name;
    }

    public String getVerticle_id() {
        return verticle_id;
    }

    public String getVerticle_name() {
        return verticle_name;
    }

    public String getProduct_brand_name() {
        return product_brand_name;
    }

    public String getProduct_photo() {
        return product_photo;
    }

    public String getProduct_full_description() {
        return product_full_description;
    }

    public String getPublish_status() {
        return publish_status;
    }

    public String getProduct_postion_no() {
        return product_postion_no;
    }

    public String getCountry_of_origin() {
        return country_of_origin;
    }

    public String getMeta_title() {
        return meta_title;
    }

    public String getMeta_description() {
        return meta_description;
    }

    public String getMeta_keyword() {
        return meta_keyword;
    }

    public String getVariants_id() {
        return variants_id;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public String getAuto_guid() {
        return auto_guid;
    }

    public String getCreate_date() {
        return create_date;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public String getLast_update_time() {
        return last_update_time;
    }

    public String getId1() {
        return id1;
    }

    public String getProduct_id1() {
        return product_id1;
    }

    public String getProduct_unit() {
        return product_unit;
    }

    public String getProduct_unit_value() {
        return product_unit_value;
    }

    public String getProduct_GST_type() {
        return product_GST_type;
    }

    public String getProduct_tax_type() {
        return product_tax_type;
    }

    public String getProduct_GST_percentage() {
        return product_GST_percentage;
    }

    public String getProduct_GST_rate() {
        return product_GST_rate;
    }

    public String getProduct_CGST_percentage() {
        return product_CGST_percentage;
    }

    public String getProduct_CGST_rate() {
        return product_CGST_rate;
    }

    public String getProduct_SGST_percentage() {
        return product_SGST_percentage;
    }

    public String getProduct_SGST_rate() {
        return product_SGST_rate;
    }

    public String getProduct_IGST_percentage() {
        return product_IGST_percentage;
    }

    public String getProduct_IGST_rate() {
        return product_IGST_rate;
    }

    public String getProduct_market_price() {
        return product_market_price;
    }

    public String getProduct_sell_price() {
        return product_sell_price;
    }

    public String getProduct_discount_percentage() {
        return product_discount_percentage;
    }

    public String getProduct_discount_price() {
        return product_discount_price;
    }

    public String getProduct_with_gst_Price() {
        return product_with_gst_Price;
    }

    public String getProduct_final_sell_price() {
        return product_final_sell_price;
    }

    public String getProduct_shipping_charge() {
        return product_shipping_charge;
    }

    public String getProduct_stock() {
        return product_stock;
    }

    public String getAuto_guid1() {
        return auto_guid1;
    }

    public String getCreate_date1() {
        return create_date1;
    }

    public String getCreate_time1() {
        return create_time1;
    }

    public String getLast_update_date1() {
        return last_update_date1;
    }

    public String getLast_update_time1() {
        return last_update_time1;
    }

    // Setter Methods

    public void setCart_qty(int cart_qty) {
        this.cart_qty = cart_qty;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProduct_temp_id(String product_temp_id) {
        this.product_temp_id = product_temp_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_short_name(String product_short_name) {
        this.product_short_name = product_short_name;
    }

    public void setProduct_full_name(String product_full_name) {
        this.product_full_name = product_full_name;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setProduct_barcode(String product_barcode) {
        this.product_barcode = product_barcode;
    }

    public void setProduct_sku(String product_sku) {
        this.product_sku = product_sku;
    }

    public void setProduct_hsnORsac(String product_hsnORsac) {
        this.product_hsnORsac = product_hsnORsac;
    }

    public void setProduct_parent_category_id(String product_parent_category_id) {
        this.product_parent_category_id = product_parent_category_id;
    }

    public void setProduct_parent_category_name(String product_parent_category_name) {
        this.product_parent_category_name = product_parent_category_name;
    }

    public void setProduct_sub_category_id(String product_sub_category_id) {
        this.product_sub_category_id = product_sub_category_id;
    }

    public void setProduct_sub_category_name(String product_sub_category_name) {
        this.product_sub_category_name = product_sub_category_name;
    }

    public void setVerticle_id(String verticle_id) {
        this.verticle_id = verticle_id;
    }

    public void setVerticle_name(String verticle_name) {
        this.verticle_name = verticle_name;
    }

    public void setProduct_brand_name(String product_brand_name) {
        this.product_brand_name = product_brand_name;
    }

    public void setProduct_photo(String product_photo) {
        this.product_photo = product_photo;
    }

    public void setProduct_full_description(String product_full_description) {
        this.product_full_description = product_full_description;
    }

    public void setPublish_status(String publish_status) {
        this.publish_status = publish_status;
    }

    public void setProduct_postion_no(String product_postion_no) {
        this.product_postion_no = product_postion_no;
    }

    public void setCountry_of_origin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }

    public void setMeta_title(String meta_title) {
        this.meta_title = meta_title;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description;
    }

    public void setMeta_keyword(String meta_keyword) {
        this.meta_keyword = meta_keyword;
    }

    public void setVariants_id(String variants_id) {
        this.variants_id = variants_id;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public void setAuto_guid(String auto_guid) {
        this.auto_guid = auto_guid;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setLast_update_date(String last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setLast_update_time(String last_update_time) {
        this.last_update_time = last_update_time;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public void setProduct_id1(String product_id1) {
        this.product_id1 = product_id1;
    }

    public void setProduct_unit(String product_unit) {
        this.product_unit = product_unit;
    }

    public void setProduct_unit_value(String product_unit_value) {
        this.product_unit_value = product_unit_value;
    }

    public void setProduct_GST_type(String product_GST_type) {
        this.product_GST_type = product_GST_type;
    }

    public void setProduct_tax_type(String product_tax_type) {
        this.product_tax_type = product_tax_type;
    }

    public void setProduct_GST_percentage(String product_GST_percentage) {
        this.product_GST_percentage = product_GST_percentage;
    }

    public void setProduct_GST_rate(String product_GST_rate) {
        this.product_GST_rate = product_GST_rate;
    }

    public void setProduct_CGST_percentage(String product_CGST_percentage) {
        this.product_CGST_percentage = product_CGST_percentage;
    }

    public void setProduct_CGST_rate(String product_CGST_rate) {
        this.product_CGST_rate = product_CGST_rate;
    }

    public void setProduct_SGST_percentage(String product_SGST_percentage) {
        this.product_SGST_percentage = product_SGST_percentage;
    }

    public void setProduct_SGST_rate(String product_SGST_rate) {
        this.product_SGST_rate = product_SGST_rate;
    }

    public void setProduct_IGST_percentage(String product_IGST_percentage) {
        this.product_IGST_percentage = product_IGST_percentage;
    }

    public void setProduct_IGST_rate(String product_IGST_rate) {
        this.product_IGST_rate = product_IGST_rate;
    }

    public void setProduct_market_price(String product_market_price) {
        this.product_market_price = product_market_price;
    }

    public void setProduct_sell_price(String product_sell_price) {
        this.product_sell_price = product_sell_price;
    }

    public void setProduct_discount_percentage(String product_discount_percentage) {
        this.product_discount_percentage = product_discount_percentage;
    }

    public void setProduct_discount_price(String product_discount_price) {
        this.product_discount_price = product_discount_price;
    }

    public void setProduct_with_gst_Price(String product_with_gst_Price) {
        this.product_with_gst_Price = product_with_gst_Price;
    }

    public void setProduct_final_sell_price(String product_final_sell_price) {
        this.product_final_sell_price = product_final_sell_price;
    }

    public void setProduct_shipping_charge(String product_shipping_charge) {
        this.product_shipping_charge = product_shipping_charge;
    }

    public void setProduct_stock(String product_stock) {
        this.product_stock = product_stock;
    }

    public void setAuto_guid1(String auto_guid1) {
        this.auto_guid1 = auto_guid1;
    }

    public void setCreate_date1(String create_date1) {
        this.create_date1 = create_date1;
    }

    public void setCreate_time1(String create_time1) {
        this.create_time1 = create_time1;
    }

    public void setLast_update_date1(String last_update_date1) {
        this.last_update_date1 = last_update_date1;
    }

    public void setLast_update_time1(String last_update_time1) {
        this.last_update_time1 = last_update_time1;
    }
}
