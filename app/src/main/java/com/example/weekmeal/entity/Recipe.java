package com.example.weekmeal.entity;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private Integer id;
    private String title;
    private String direction;
    private List<Ingredient> ingredients;
    private List<Diet> diets;

    public Recipe(){
        this.ingredients = new ArrayList<>();
        this.diets = new ArrayList<>();
    }

    public Recipe(Integer id, String title, String direction, List<Ingredient> ingredients, List<Diet> diets) {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Diet> getDiets() {
        return diets;
    }
}
