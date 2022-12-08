package com.example.weekmeal.entity;

import java.util.HashMap;
import java.util.List;

public class Planning {

    private Integer id;

    //Format = {"LundiR1" : Recipe{Poulet}}  for lundi déjeuner
    private HashMap<String, List<Recipe>> mealsMenu;


    public Planning(Integer id, HashMap<String, List<Recipe>> mealsMenu) {
        this.id = id;
        this.mealsMenu = mealsMenu;
    }

    public Planning(){
        this.id = 0;
        this.mealsMenu = new HashMap<>();
    }

    public List<Recipe> getMealMenu(String menuDate) {
        return mealsMenu.get(menuDate);
    }

    public void addMeal(String menuDate, List<Recipe> meal){
        mealsMenu.put(menuDate, meal);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
