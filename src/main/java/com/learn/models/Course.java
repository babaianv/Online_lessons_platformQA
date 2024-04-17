package com.learn.models;

public class Course {
    private  String title;
    private  String price;
    private  String description;

    public Course setTitle(String title) {
        this.title = title;
        return this;
    }

    public Course setPrice(String price) {
        this.price = price;
        return this;
    }

    public Course setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
