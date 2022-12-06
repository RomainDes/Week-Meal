package com.example.weekmeal.entity;

import com.google.gson.internal.LinkedTreeMap;

public class Ingredient {

    private Integer id;
    private String title;
    private String quantity;

    public Ingredient(Integer id, String title, String quantity) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
    }

    public Ingredient(){

    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public String getQuantity() {
        return quantity;
    }

    //toString():
    public String toString(){
        String str = "";
        str = str.concat(this.getTitle()+"\n");
        return str;
    }

    //Converter from LinkedTreeMap to Ingredient:
    public static Ingredient convertLTM(LinkedTreeMap ingredientLTM){
        return new Ingredient(new Integer(((Double) ingredientLTM.get("id")).intValue()), (String) ingredientLTM.get("title"), (String) ingredientLTM.get("quantity"));
    }
}
