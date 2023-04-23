package com.example.travelbuddy;

import java.io.Serializable;

public class Model implements Serializable {

//    int image;
private String id, title, description,place ,price;

    public Model(String id, String title, String description,String place,String price){
        this.id=id;
        this.title = title;
        this.description = description;
        this.place=place;
        this.price = price;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
