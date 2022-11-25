package com.example.weekmeal.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recipe {

    private Integer id;
    private String title;
    private String direction;
    private HashMap<Integer, Ingredient> ingredients;
    private List<Diet> diets;

    public Recipe(){
        this.ingredients = new HashMap<>();
        this.diets = new ArrayList<>();
    }

    public Recipe(Integer id, String title, String direction, HashMap<Integer, Ingredient> ingredients, List<Diet> diets) {
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

    public HashMap<Integer, Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Diet> getDiets() {
        return diets;
    }
}
