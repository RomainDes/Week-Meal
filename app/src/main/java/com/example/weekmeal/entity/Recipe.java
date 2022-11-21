package com.example.weekmeal.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recipe {

    private Integer id;
    private String title;
    private String directions;
    private List<Diet> diets;
    //Ingredients with there quantity
    private HashMap<Ingredient, Float> ingredients;

    public Recipe(Integer id, String title, String directions, List<Diet> diets, HashMap<Ingredient, Float> ingredients) {
        this.id = id;
        this.title = title;
        this.directions = directions;
        this.diets = diets;
        this.ingredients = ingredients;
    }

    public Recipe(){
        diets = new ArrayList<>();
        ingredients = new HashMap<>();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirections() {
        return directions;
    }

    public List<Diet> getDiets() {
        return diets;
    }

    public HashMap<Ingredient, Float> getIngredients() {
        return ingredients;
    }
}
