package com.example.travelbuddy;

import java.util.ArrayList;

public class PaymentModel {

    ArrayList<cards> cards;

    public ArrayList<PaymentModel.cards> getCards() {
        return cards;
    }

    public void setCards(ArrayList<PaymentModel.cards> cards) {
        this.cards = cards;
    }

    public class cards{
        String cardname;
        String cardnumber;
        String expdate;
        String cvv;
        String user;
        String createdAt;

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
}
