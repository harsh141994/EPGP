package com.epgp.inflibnet.epgp.model;

/**
 * Created by Dell on 09-07-2015.
 */
public class TeamContentWriters {

    private String cwname;
    private String fname;
    private String mname;
    private String lname;
    private String mcatid;
    private String cat_name;

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCwname() {
        return cwname;
    }

    public void setCwname(String cwname) {
        this.cwname = cwname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMcatid() {
        return mcatid;
    }

    public void setMcatid(String mcatid) {
        this.mcatid = mcatid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }
}
