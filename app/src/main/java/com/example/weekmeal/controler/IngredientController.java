package com.example.weekmeal.controler;

import android.app.Activity;

import com.example.weekmeal.entity.Ingredient;
import com.example.weekmeal.tools.JSONTool;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class IngredientController {

    private List<Ingredient> ingredientList;

    public List<Ingredient> getIngredientList(){
        return ingredientList;
    }

    public IngredientController(){
        ingredientList = new ArrayList<>();
    }

    @Override
    public String toString(){
        String str = new String();
        str = str.concat("Ingredients :\n");
        for(Ingredient d : ingredientList){
            str = str.concat(d.toString());
        }
        str = str.concat("---");
        return str;
    }

    public void addIngredient(Ingredient ingredient, Activity activity){
        Connector.getInstance().addEntity(activity, ingredient);
        synchronizedWithDB(activity);
    }

    public void synchronizedWithDB(Activity activity ){
        ingredientList = new ArrayList<>();
        Connector.getInstance().synchronizedWithDB(activity, new Ingredient());
    }

    public void readLocalDB(Activity activity){
        ArrayList<LinkedTreeMap> IngredientsLocal = (ArrayList<LinkedTreeMap>) JSONTool.getInstance().loadJSONFromAsset(activity, ingredientList, "Ingredient");
        for(LinkedTreeMap ltm : IngredientsLocal){
            Object[] keyset = ltm.keySet().toArray();
            ingredientList.add(new Ingredient(new Integer(((Double) ltm.get("id")).intValue()), (String) ltm.get("title"), (String) ltm.get("quantity")));
        }
    }
    //Singleton:
    private static IngredientController instance;

    public static IngredientController getInstance() {
        if(instance == null){
            instance = new IngredientController();
        }
        return instance;
    }
}
