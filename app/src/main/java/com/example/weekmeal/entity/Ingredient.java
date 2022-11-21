package com.example.weekmeal.entity;

public class Ingredient {

    private Integer id;
    private String title;

    public Ingredient(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
