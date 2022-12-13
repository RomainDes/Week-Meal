package com.example.weekmeal.entity;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GrosseryList {

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

    public Date getGrosseryDate() {
        return grosseryDate;
    }

    public void AddIngredientToGrossery(Ingredient ingredient, String quantity){

    }
}
