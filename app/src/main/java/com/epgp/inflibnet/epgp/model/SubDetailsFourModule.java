package com.epgp.inflibnet.epgp.model;

/**
 * Created by Dell on 13-07-2015.
 */
public class SubDetailsFourModule {
    private String total;
    private String subject_id;
    private String cat_name;

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
