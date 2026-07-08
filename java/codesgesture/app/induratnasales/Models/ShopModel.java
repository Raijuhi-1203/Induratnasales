package codesgesture.app.induratnasales.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ShopModel implements Serializable {
    private String id;
    private String buyer_id;
    private String shop_code;
    private String auto_guid;
    private String customer_name;
    private String firm_name;
    private String customer_email;
    private String customer_mobileno;
    private String customer_phone_no;
    private String customer_gender;
    private String customer_area;
    private String gst_state_code;
    private String gst_state;
    private String gstin;
    private String customer_password = null;
    private String customer_profilephoto = null;
    private String credit_limit;
    private String customer_date;
    private String customer_time;
    private String last_update_date;
    private String last_update_time;
    private String address_line_1;
    private String address_line_2;
    private String address_city_id;
    private String address_city_name;
    private String address_state_id;
    private String address_state_name;
    private String address_pincode;
    private String address_landmark = null;
    private String bank_name;
    private String bank_ac_holder_name;
    private String bank_ac_no;
    private String bank_ifsc;
    private String bank_address;
    private String customer_status;
    private String id1 = null;
    private String buyer_id1 = null;
    private String ref_transaction_id = null;
    private String firm_name1 = null;
    private String transaction_amount = null;
    private String credit_amount = null;
    private String debit_amount = null;
    private String balance_amount = null;
    private String transaction_type = null;
    private String transaction_date = null;
    private String transaction_time = null;
    private String payment_mode = null;
    private String note = null;
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

    public String getId() {
        return id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public String getShop_code() {
        return shop_code;
    }

    public String getAuto_guid() {
        return auto_guid;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getFirm_name() {
        return firm_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public String getCustomer_mobileno() {
        return customer_mobileno;
    }

    public String getCustomer_phone_no() {
        return customer_phone_no;
    }

    public String getCustomer_gender() {
        return customer_gender;
    }

    public String getCustomer_area() {
        return customer_area;
    }

    public String getGst_state_code() {
        return gst_state_code;
    }

    public String getGst_state() {
        return gst_state;
    }

    public String getGstin() {
        return gstin;
    }

    public String getCustomer_password() {
        return customer_password;
    }

    public String getCustomer_profilephoto() {
        return customer_profilephoto;
    }

    public String getCredit_limit() {
        return credit_limit;
    }

    public String getCustomer_date() {
        return customer_date;
    }

    public String getCustomer_time() {
        return customer_time;
    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public String getLast_update_time() {
        return last_update_time;
    }

    public String getAddress_line_1() {
        return address_line_1;
    }

    public String getAddress_line_2() {
        return address_line_2;
    }

    public String getAddress_city_id() {
        return address_city_id;
    }

    public String getAddress_city_name() {
        return address_city_name;
    }

    public String getAddress_state_id() {
        return address_state_id;
    }

    public String getAddress_state_name() {
        return address_state_name;
    }

    public String getAddress_pincode() {
        return address_pincode;
    }

    public String getAddress_landmark() {
        return address_landmark;
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getBank_ac_holder_name() {
        return bank_ac_holder_name;
    }

    public String getBank_ac_no() {
        return bank_ac_no;
    }

    public String getBank_ifsc() {
        return bank_ifsc;
    }

    public String getBank_address() {
        return bank_address;
    }

    public String getCustomer_status() {
        return customer_status;
    }

    public String getId1() {
        return id1;
    }

    public String getBuyer_id1() {
        return buyer_id1;
    }

    public String getRef_transaction_id() {
        return ref_transaction_id;
    }

    public String getFirm_name1() {
        return firm_name1;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public String getCredit_amount() {
        return credit_amount;
    }

    public String getDebit_amount() {
        return debit_amount;
    }

    public String getBalance_amount() {
        return balance_amount;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public String getTransaction_time() {
        return transaction_time;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public String getNote() {
        return note;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public void setShop_code(String shop_code) {
        this.shop_code = shop_code;
    }

    public void setAuto_guid(String auto_guid) {
        this.auto_guid = auto_guid;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setFirm_name(String firm_name) {
        this.firm_name = firm_name;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public void setCustomer_mobileno(String customer_mobileno) {
        this.customer_mobileno = customer_mobileno;
    }

    public void setCustomer_phone_no(String customer_phone_no) {
        this.customer_phone_no = customer_phone_no;
    }

    public void setCustomer_gender(String customer_gender) {
        this.customer_gender = customer_gender;
    }

    public void setCustomer_area(String customer_area) {
        this.customer_area = customer_area;
    }

    public void setGst_state_code(String gst_state_code) {
        this.gst_state_code = gst_state_code;
    }

    public void setGst_state(String gst_state) {
        this.gst_state = gst_state;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }

    public void setCustomer_profilephoto(String customer_profilephoto) {
        this.customer_profilephoto = customer_profilephoto;
    }

    public void setCredit_limit(String credit_limit) {
        this.credit_limit = credit_limit;
    }

    public void setCustomer_date(String customer_date) {
        this.customer_date = customer_date;
    }

    public void setCustomer_time(String customer_time) {
        this.customer_time = customer_time;
    }

    public void setLast_update_date(String last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setLast_update_time(String last_update_time) {
        this.last_update_time = last_update_time;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    public void setAddress_city_id(String address_city_id) {
        this.address_city_id = address_city_id;
    }

    public void setAddress_city_name(String address_city_name) {
        this.address_city_name = address_city_name;
    }

    public void setAddress_state_id(String address_state_id) {
        this.address_state_id = address_state_id;
    }

    public void setAddress_state_name(String address_state_name) {
        this.address_state_name = address_state_name;
    }

    public void setAddress_pincode(String address_pincode) {
        this.address_pincode = address_pincode;
    }

    public void setAddress_landmark(String address_landmark) {
        this.address_landmark = address_landmark;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public void setBank_ac_holder_name(String bank_ac_holder_name) {
        this.bank_ac_holder_name = bank_ac_holder_name;
    }

    public void setBank_ac_no(String bank_ac_no) {
        this.bank_ac_no = bank_ac_no;
    }

    public void setBank_ifsc(String bank_ifsc) {
        this.bank_ifsc = bank_ifsc;
    }

    public void setBank_address(String bank_address) {
        this.bank_address = bank_address;
    }

    public void setCustomer_status(String customer_status) {
        this.customer_status = customer_status;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public void setBuyer_id1(String buyer_id1) {
        this.buyer_id1 = buyer_id1;
    }

    public void setRef_transaction_id(String ref_transaction_id) {
        this.ref_transaction_id = ref_transaction_id;
    }

    public void setFirm_name1(String firm_name1) {
        this.firm_name1 = firm_name1;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public void setCredit_amount(String credit_amount) {
        this.credit_amount = credit_amount;
    }

    public void setDebit_amount(String debit_amount) {
        this.debit_amount = debit_amount;
    }

    public void setBalance_amount(String balance_amount) {
        this.balance_amount = balance_amount;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public void setTransaction_time(String transaction_time) {
        this.transaction_time = transaction_time;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @NonNull
    @Override
    public String toString() {
        return firm_name;
    }

}