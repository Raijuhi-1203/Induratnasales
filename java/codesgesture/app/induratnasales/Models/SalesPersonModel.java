package codesgesture.app.induratnasales.Models;

import java.io.Serializable;

public class SalesPersonModel implements Serializable {
    private String id;
    private String sales_id;
    private String sales_name;
    private String sales_email;
    private String sales_mobileno;
    private String sales_gender;
    private String sales_address_line_1;
    private String sales_address_line_2;
    private String sales_state_id;
    private String sales_state_name;
    private String sales_city_id;
    private String sales_city_name;
    private String sales_gst = null;
    private String sales_gst_state_code = null;
    private String sales_gst_state = null;
    private String sales_pincode = null;
    private String sales_password;
    private String sales_photo = null;
    private String identity_option = null;
    private String mobileno_verified = null;
    private String otp = null;
    private String sales_date;
    private String sales_time;
    private String sales_paid_amount = null;
    private String sales_due_amount = null;
    private String sales_status = null;

    public String getAuto_guid() {
        return auto_guid;
    }

    public void setAuto_guid(String auto_guid) {
        this.auto_guid = auto_guid;
    }

    private String auto_guid = null;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getSales_id() {
        return sales_id;
    }

    public String getSales_name() {
        return sales_name;
    }

    public String getSales_email() {
        return sales_email;
    }

    public String getSales_mobileno() {
        return sales_mobileno;
    }

    public String getSales_gender() {
        return sales_gender;
    }

    public String getSales_address_line_1() {
        return sales_address_line_1;
    }

    public String getSales_address_line_2() {
        return sales_address_line_2;
    }

    public String getSales_state_id() {
        return sales_state_id;
    }

    public String getSales_state_name() {
        return sales_state_name;
    }

    public String getSales_city_id() {
        return sales_city_id;
    }

    public String getSales_city_name() {
        return sales_city_name;
    }

    public String getSales_gst() {
        return sales_gst;
    }

    public String getSales_gst_state_code() {
        return sales_gst_state_code;
    }

    public String getSales_gst_state() {
        return sales_gst_state;
    }

    public String getSales_pincode() {
        return sales_pincode;
    }

    public String getSales_password() {
        return sales_password;
    }

    public String getSales_photo() {
        return sales_photo;
    }

    public String getIdentity_option() {
        return identity_option;
    }

    public String getMobileno_verified() {
        return mobileno_verified;
    }

    public String getOtp() {
        return otp;
    }

    public String getSales_date() {
        return sales_date;
    }

    public String getSales_time() {
        return sales_time;
    }

    public String getSales_paid_amount() {
        return sales_paid_amount;
    }

    public String getSales_due_amount() {
        return sales_due_amount;
    }

    public String getSales_status() {
        return sales_status;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setSales_id(String sales_id) {
        this.sales_id = sales_id;
    }

    public void setSales_name(String sales_name) {
        this.sales_name = sales_name;
    }

    public void setSales_email(String sales_email) {
        this.sales_email = sales_email;
    }

    public void setSales_mobileno(String sales_mobileno) {
        this.sales_mobileno = sales_mobileno;
    }

    public void setSales_gender(String sales_gender) {
        this.sales_gender = sales_gender;
    }

    public void setSales_address_line_1(String sales_address_line_1) {
        this.sales_address_line_1 = sales_address_line_1;
    }

    public void setSales_address_line_2(String sales_address_line_2) {
        this.sales_address_line_2 = sales_address_line_2;
    }

    public void setSales_state_id(String sales_state_id) {
        this.sales_state_id = sales_state_id;
    }

    public void setSales_state_name(String sales_state_name) {
        this.sales_state_name = sales_state_name;
    }

    public void setSales_city_id(String sales_city_id) {
        this.sales_city_id = sales_city_id;
    }

    public void setSales_city_name(String sales_city_name) {
        this.sales_city_name = sales_city_name;
    }

    public void setSales_gst(String sales_gst) {
        this.sales_gst = sales_gst;
    }

    public void setSales_gst_state_code(String sales_gst_state_code) {
        this.sales_gst_state_code = sales_gst_state_code;
    }

    public void setSales_gst_state(String sales_gst_state) {
        this.sales_gst_state = sales_gst_state;
    }

    public void setSales_pincode(String sales_pincode) {
        this.sales_pincode = sales_pincode;
    }

    public void setSales_password(String sales_password) {
        this.sales_password = sales_password;
    }

    public void setSales_photo(String sales_photo) {
        this.sales_photo = sales_photo;
    }

    public void setIdentity_option(String identity_option) {
        this.identity_option = identity_option;
    }

    public void setMobileno_verified(String mobileno_verified) {
        this.mobileno_verified = mobileno_verified;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setSales_date(String sales_date) {
        this.sales_date = sales_date;
    }

    public void setSales_time(String sales_time) {
        this.sales_time = sales_time;
    }

    public void setSales_paid_amount(String sales_paid_amount) {
        this.sales_paid_amount = sales_paid_amount;
    }

    public void setSales_due_amount(String sales_due_amount) {
        this.sales_due_amount = sales_due_amount;
    }

    public void setSales_status(String sales_status) {
        this.sales_status = sales_status;
    }
}