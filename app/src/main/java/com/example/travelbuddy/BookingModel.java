package com.example.travelbuddy;

import java.util.ArrayList;

public class BookingModel {

    ArrayList<cards> cards;

    public ArrayList<BookingModel.cards> getCards() {
        return cards;
    }

    public void setCards(ArrayList<BookingModel.cards> cards) {
        this.cards = cards;
    }

    public class cards{
        String _id;
        String fname;
        String lname;
        String email;
        String contactno;
        String user;
        String checkIn;
        String checkOut;
        String place;


        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
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
}
