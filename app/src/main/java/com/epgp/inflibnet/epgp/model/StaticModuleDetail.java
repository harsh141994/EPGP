package com.epgp.inflibnet.epgp.model;

/**
 * Created by Dell on 13-07-2015.
 */
public class StaticModuleDetail {

    private String total;
    private String cat_name;
    private String cat_parent;
    private String mcatid;

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_parent() {
        return cat_parent;
    }

    public void setCat_parent(String cat_parent) {
        this.cat_parent = cat_parent;
    }

    public String getMcatid() {
        return mcatid;
    }

    public void setMcatid(String mcatid) {
        this.mcatid = mcatid;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
