package com.example.travelbuddy;

public class Data {

    private String fname;
    private String lname;
    private String contactno;
    private String email;

    public Data(String fname,String lname,String contactno,String email){
        this.fname=fname;
        this.lname=lname;
        this.contactno=contactno;
        this.email=email;
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

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
