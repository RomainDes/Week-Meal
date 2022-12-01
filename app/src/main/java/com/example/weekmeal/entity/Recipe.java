package com.example.weekmeal.entity;



import android.util.Pair;

import com.example.weekmeal.controler.DietController;
import com.example.weekmeal.controler.IngredientController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recipe {

    private Integer id;
    private String title;
    private String direction;
    private HashMap<String, Integer> ingredients; //String = quantité -- IdIngredient  Integer = IdIngredient exemple : "2--1"
    private List<Integer> diets;

    public Recipe(){
        this.ingredients = new HashMap<>();
        this.diets = new ArrayList<>();
        Pair<String, Ingredient> p = new Pair<>("", new Ingredient());
    }

    public Recipe(Integer id, String title, String direction, HashMap<String, Integer> ingredients, List<Integer> diets) {
        this.id = id;
        this.title = title;
        this.direction = direction;
        this.ingredients = ingredients;
        this.diets = diets;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirection() {
        return direction;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    public List<Integer> getDiets() {
        return diets;
    }


    public String toString(){
        String str = new String();
        str = str.concat(this.getId()+": "+this.getTitle()+"\n");
        str = str.concat("Diets:\n");
        for(Integer dietId: this.getDiets())
            str = str.concat(DietController.getInstance().getDietByID(dietId).toString());

        str = str.concat("Ingredients:\n");
        for(String key: this.getIngredients().keySet()) {
            Integer ingredientId = this.getIngredients().get(key);
            str = str.concat(key.split("/")[0]+ " " +
                    IngredientController.getInstance().getIngredientByID(ingredientId).getQuantity()
                    + " " +IngredientController.getInstance().getIngredientByID(ingredientId).toString());
        }

        str = str.concat("Directions: "+this.getDirection()+"\n");

        return str;
    }
}
