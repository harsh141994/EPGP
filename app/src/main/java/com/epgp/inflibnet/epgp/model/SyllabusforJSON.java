package com.epgp.inflibnet.epgp.model;

/**
 * Created by Dell on 07-07-2015.
 */
public class SyllabusforJSON {

    private String syl_id;
    private String linkfile;
    private String linkfileext;
    private String cat_name;
    private String mcatid;
    private String mpcatid;

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getLinkfile() {
        return linkfile;
    }

    public void setLinkfile(String linkfile) {
        this.linkfile = linkfile;
    }

    public String getLinkfileext() {
        return linkfileext;
    }

    public void setLinkfileext(String linkfileext) {
        this.linkfileext = linkfileext;
    }

    public String getSyl_id() {
        return syl_id;
    }

    public void setSyl_id(String syl_id) {
        this.syl_id = syl_id;
    }

    public String getMcatid() {
        return mcatid;
    }

    public void setMcatid(String mcatid) {
        this.mcatid = mcatid;
    }

    public String getMpcatid() {
        return mpcatid;
    }

    public void setMpcatid(String mpcatid) {
        this.mpcatid = mpcatid;
    }
}
