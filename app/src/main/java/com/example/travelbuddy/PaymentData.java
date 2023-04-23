package com.example.travelbuddy;

public class PaymentData {

    private String cardname;
    private String cardnumber;
    private String expdate;
    private String cvv;
    private String user;
    private String createdAt;

    public PaymentData(String cardname,String cardnumber,String expdate,String cvv,String user,String createdAt){
        this.cardname=cardname;
        this.cardnumber=cardnumber;
        this.expdate=expdate;
        this.cvv=cvv;
        this.user=user;
        this.createdAt=createdAt;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
