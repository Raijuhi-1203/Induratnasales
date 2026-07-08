package codesgesture.app.induratnasales.Models;

import java.io.Serializable;

public class PaymentModel implements Serializable {
    private String id;
    private String buyer_id;
    private String ref_transaction_id;
    private String firm_name;
    private String transaction_amount;
    private String credit_amount;
    private String debit_amount;
    private String balance_amount;
    private String transaction_type;
    private String transaction_date;
    private String transaction_time;
    private String payment_mode;
    private String note = null;
    private String transaction_section;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    private String balance;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public String getRef_transaction_id() {
        return ref_transaction_id;
    }

    public String getFirm_name() {
        return firm_name;
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

    public String getTransaction_section() {
        return transaction_section;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public void setRef_transaction_id(String ref_transaction_id) {
        this.ref_transaction_id = ref_transaction_id;
    }

    public void setFirm_name(String firm_name) {
        this.firm_name = firm_name;
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

    public void setTransaction_section(String transaction_section) {
        this.transaction_section = transaction_section;
    }
}
