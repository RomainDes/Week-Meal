package com.example.weekmeal.entity;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GrosseryList {

    private String id;
    private List<Pair<String, Ingredient>> ingredientList;
    private Date grosseryDate;


    public GrosseryList(List<Pair<String, Ingredient>> ingredientList, Date grosseryDate) {
        this.ingredientList = ingredientList;
        this.grosseryDate = grosseryDate;
    }

    public GrosseryList(){
        ingredientList = new ArrayList<>();
    }

    public List<Pair<String, Ingredient>> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(int i, Pair<String, Ingredient> p){
        this.ingredientList.set(i, p);
    }

    public Date getGrosseryDate() {
        return grosseryDate;
    }

    public void AddIngredientToGrossery(Ingredient ingredient, String quantity){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString(){
        StringBuilder textList = new StringBuilder();
        for(Pair<String, Ingredient> ingredient : ingredientList){
            textList.append(ingredient.first.split("--")[0]);
            textList.append(ingredient.second.getQuantity());
            textList.append(" ");
            textList.append(ingredient.second);
//            textList.append(ingredient.second.toString());
        }
        return textList.toString();
    }
}
