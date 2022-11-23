package com.example.weekmeal.entity;

import com.google.gson.internal.LinkedTreeMap;

public class Ingredient {

    private Integer id;
    private String title;

    public Ingredient(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Ingredient(){

    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    //toString():
    public String toString(){
        String str = "";
        str = str.concat(this.getId()+": "+this.getTitle()+"\n");
        return str;
    }

    //Converter from LinkedTreeMap to Ingredient:
    public static Ingredient convertLTM(LinkedTreeMap ingredientLTM){
        return new Ingredient(new Integer(((Double) ingredientLTM.get("id")).intValue()), (String) ingredientLTM.get("title"));
    }
}
