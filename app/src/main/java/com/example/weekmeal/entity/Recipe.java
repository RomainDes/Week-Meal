package com.example.weekmeal.entity;



import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recipe {

    private Integer id;
    private String title;
    private String direction;
    private HashMap<String, Ingredient> ingredients;
    private List<Diet> diets;

    public Recipe(){
        this.ingredients = new HashMap<>();
        this.diets = new ArrayList<>();
        Pair<String, Ingredient> p = new Pair<>("", new Ingredient());
    }

    public Recipe(Integer id, String title, String direction, HashMap<String, Ingredient> ingredients, List<Diet> diets) {
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

    public HashMap<String, Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Diet> getDiets() {
        return diets;
    }


    public String toString(){
        String str = new String();
        str = str.concat(this.getId()+": "+this.getTitle()+"\n");
        str = str.concat("Diets:\n");
        for(Diet diet: this.getDiets())
            str = str.concat(diet.toString());

        str = str.concat("Ingredients:\n");
        for(String key: this.getIngredients().keySet()) {
            Ingredient i = this.getIngredients().get(key);
            str = str.concat(key.split("/")[0]+ " " + i.getQuantity() + " " +i.toString());
        }

        str = str.concat("Directions: "+this.getDirection()+"\n");

        return str;
    }
}
