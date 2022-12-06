package com.example.weekmeal.entity;

import java.util.HashMap;
import java.util.List;

public class Planning {

    //Format = {"Lundi-D" : Receip{Poulet}}  for lundi déjeuner
    private HashMap<String, List<Recipe>> mealsMenu;

    private Integer id;

    public Planning(HashMap<String, List<Recipe>> mealsMenu, Integer id) {
        this.mealsMenu = mealsMenu;
        this.id = id;
    }

    public Planning(){

    }

    public List<Recipe> getMealMenu(String menuDate) {
        return mealsMenu.get(menuDate);
    }

    public void setMeal(String menuDate, List<Recipe> meal){
        mealsMenu.put(menuDate, meal);
    }


    public Integer getId() {
        return id;
    }
}
