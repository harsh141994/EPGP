package com.epgp.inflibnet.epgp.model;

/**
 * Created by Dell on 08-07-2015.
 */
public class SubDetails {

    private String fname;
    private String mname;
    private String lname;
    private String usrno;
    private String designation;
    private String organization;
    private String photofile;
    private String photofileext;
    private String contactno;
    private String emailid;
    private String r_subject;

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
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

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPhotofile() {
        return photofile;
    }

    public void setPhotofile(String photofile) {
        this.photofile = photofile;
    }

    public String getPhotofileext() {
        return photofileext;
    }

    public void setPhotofileext(String photofileext) {
        this.photofileext = photofileext;
    }

    public String getUsrno() {
        return usrno;
    }

    public void setUsrno(String usrno) {
        this.usrno = usrno;
    }

    public String getR_subject() {
        return r_subject;
    }

    public void setR_subject(String r_subject) {
        this.r_subject = r_subject;
    }
}
