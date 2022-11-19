package com.example.weekmeal.entity;

public class Diet {
    private Integer id;
    private String title;

    public Diet(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Diet(){

    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
