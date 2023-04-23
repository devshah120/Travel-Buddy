package com.example.travelbuddy;

public class BookingData {

    private String fname;
    private String lname;
    private String email;
    private String contactno;
    private String user;
    private String checkIn;
    private String checkOut;
    private String place;

    public BookingData(String fname,String lname,String email,String contactno,String user,String checkIn,String checkOut,String place){
        this.fname=fname;
        this.lname=lname;
        this.email=email;
        this.contactno=contactno;
        this.user=user;
        this.checkIn=checkIn;
        this.checkOut=checkOut;
        this.place=place;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
