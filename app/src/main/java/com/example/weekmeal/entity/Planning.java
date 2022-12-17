package com.example.weekmeal.entity;

import android.util.Pair;

import com.example.weekmeal.controler.IngredientController;

import java.util.ArrayList;
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

    public List<Pair<String, Ingredient>> getAllIngredients(){
        List<Pair<String, Ingredient>> ingredients = new ArrayList<>();
        for(List<Recipe> recipeList: mealsMenu.values()){
            for(Recipe r: recipeList){
                for(String idQuantityToAdd: r.getIngredients().keySet()){
                    Ingredient ingredientToAdd = IngredientController.getInstance()
                            .getIngredientByID(r.getIngredients().get(idQuantityToAdd));
                    String idQuantityExist = "";
                    int indexToAdd = ingredients.size()-1;
                    for(Pair<String, Ingredient> p : ingredients){
                        if(p.second == ingredientToAdd){
                            idQuantityExist = p.first;
                            indexToAdd = ingredients.indexOf(p);
                        }
                    }
                    if (!idQuantityExist.isEmpty()) {
                        String newIdQuantity = "";
//                        double quantity = Double.valueOf(idQuantityExist.split("--")[0]) +
//                                Double.valueOf(idQuantityToAdd.split("--")[0]);
                        double quantity = StringtoDouble(idQuantityExist.split("--")[0]) +
                                StringtoDouble(idQuantityToAdd.split("--")[0]);
                        newIdQuantity = newIdQuantity.concat(quantity+"--"+ingredientToAdd.getId().toString());
                        idQuantityToAdd = newIdQuantity;
                        //replace quantity of ingredient :
                        ingredients.set(indexToAdd, new Pair<>(idQuantityToAdd, ingredientToAdd));
                    }else{
                        //just add :
                        ingredients.add(new Pair<>(idQuantityToAdd, ingredientToAdd));
                    }
                }
            }
        }
        return ingredients;
    }

    private static double StringtoDouble(String dose){

        if(dose!=null&&dose.length()>0){
            if(dose.contains("/")){
                String[] inter = dose.trim().split("/");
                if(inter[0].contains(" ")){
                    String[] inter2 = inter[0].split(" ");
                    return (double)Math.round((Float.parseFloat(inter2[0])+Float.parseFloat(inter2[1])/Float.parseFloat(inter[1]))*100)/100;
                } else {
                    return (double)Math.floor((Float.parseFloat(inter[0])/Float.parseFloat(inter[1]))*100)/100;
                }
            } else {
                return (double)Math.floor((Float.parseFloat(dose))*100)/100;
            }
        } else {
            return 0;
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
