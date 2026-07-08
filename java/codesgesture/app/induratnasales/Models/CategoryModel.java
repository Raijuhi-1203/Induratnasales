package codesgesture.app.induratnasales.Models;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private float id;
    private float category_temp_id;
    private String category_id;
    private String main_category_id;
    private String main_sub_category_id = null;
    private String category_title;
    private String category_name;
    private String category_photo;
    private float category_orderno;
    private String category_status;
    private String category_type;
    private String category_display;
    private String category_banner = null;
    private String auto_guid;
    private String last_update_date;
    private String last_update_time;


    // Getter Methods

    public float getId() {
        return id;
    }

    public float getCategory_temp_id() {
        return category_temp_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getMain_category_id() {
        return main_category_id;
    }

    public String getMain_sub_category_id() {
        return main_sub_category_id;
    }

    public String getCategory_title() {
        return category_title;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getCategory_photo() {
        return category_photo;
    }

    public float getCategory_orderno() {
        return category_orderno;
    }

    public String getCategory_status() {
        return category_status;
    }

    public String getCategory_type() {
        return category_type;
    }

    public String getCategory_display() {
        return category_display;
    }

    public String getCategory_banner() {
        return category_banner;
    }

    public String getAuto_guid() {
        return auto_guid;
    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public String getLast_update_time() {
        return last_update_time;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setCategory_temp_id(float category_temp_id) {
        this.category_temp_id = category_temp_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setMain_category_id(String main_category_id) {
        this.main_category_id = main_category_id;
    }

    public void setMain_sub_category_id(String main_sub_category_id) {
        this.main_sub_category_id = main_sub_category_id;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setCategory_photo(String category_photo) {
        this.category_photo = category_photo;
    }

    public void setCategory_orderno(float category_orderno) {
        this.category_orderno = category_orderno;
    }

    public void setCategory_status(String category_status) {
        this.category_status = category_status;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

    public void setCategory_display(String category_display) {
        this.category_display = category_display;
    }

    public void setCategory_banner(String category_banner) {
        this.category_banner = category_banner;
    }

    public void setAuto_guid(String auto_guid) {
        this.auto_guid = auto_guid;
    }

    public void setLast_update_date(String last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setLast_update_time(String last_update_time) {
        this.last_update_time = last_update_time;
    }
}
