package com.example.travelbuddy;

import java.util.ArrayList;

public class TrainModel {

    ArrayList<data> data;

    public ArrayList<TrainModel.data> getData() {
        return data;
    }

    public void setData(ArrayList<TrainModel.data> data) {
        this.data = data;
    }

    public class data{
        String name;
        String email;
        String from;
        String to;
        String user;
        String people;
        String date;
        String time;
        String classs;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPeople() {
            return people;
        }

        public void setPeople(String people) {
            this.people = people;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getClasss() {
            return classs;
        }

        public void setClasss(String classs) {
            this.classs = classs;
        }
    }
}
