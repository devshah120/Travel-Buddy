package com.example.travelbuddy;

import java.util.ArrayList;

public class UserdataModel {

    ArrayList<user> user;

    public ArrayList<UserdataModel.user> getUser() {
        return user;
    }

    public void setUser(ArrayList<UserdataModel.user> user) {
        this.user = user;
    }

    public class user{
        String _id;
        String fname;
        String lname;
        String contactno;
        String email;
        String avatar;

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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
