package com.example.weekmeal.controler;

import android.app.Activity;
import android.util.Pair;

import com.example.weekmeal.entity.Diet;
import com.example.weekmeal.entity.Ingredient;
import com.example.weekmeal.entity.Recipe;
import com.example.weekmeal.tools.JSONTool;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeController {

    private List<Recipe> recipeList;

    public List<Recipe> getRecipeList(){
        return recipeList;
    }

    public RecipeController(){
        recipeList = new ArrayList<>();
    }

    @Override
    public String toString(){
        String str = new String();
        str = str.concat("Recipes :\n");
        for(Recipe r : recipeList){
            str.concat(r.toString());
        }
        str = str.concat("---");
        return str;
    }

    public void addRecipe(Recipe Recipe, Activity activity){
        Connector.getInstance().addEntity(activity, Recipe);
        synchronizedWithDB(activity);
    }

    public void synchronizedWithDB(Activity activity ){
        recipeList = new ArrayList<>();
        Connector.getInstance().synchronizedWithDB(activity, new Recipe());
    }

    public void readLocalDB(Activity activity){
        ArrayList<LinkedTreeMap> RecipesLocal = (ArrayList<LinkedTreeMap>) JSONTool.getInstance().loadJSONFromAsset(activity, recipeList, "Recipe");
        for(LinkedTreeMap ltm : RecipesLocal){
            //get every ingredients and diets:
            HashMap<String, Integer> ingredients = new HashMap<>();
            LinkedTreeMap ingredientListLTM = (LinkedTreeMap) ltm.get("ingredients");
            for (Object keyLTM : ingredientListLTM.keySet()){
                ingredients.put((String) keyLTM, ((Double) ingredientListLTM.get(keyLTM)).intValue());
            }
            List<Integer> diets = new ArrayList<>();
            List<Double> dietsListLTM = (ArrayList) ltm.get("diets");
            for (Double i : dietsListLTM){
                diets.add(i.intValue());
            }
            recipeList.add(new Recipe(
            new Integer(((Double) ltm.get("id")).intValue()),
                    (String) ltm.get("title"),
                    (String) ltm.get("direction"),
                    ingredients,
                    diets
            ));
        }
    }
    //Singleton:
    private static RecipeController instance;

    public static RecipeController getInstance() {
        if(instance == null){
            instance = new RecipeController();
        }
        return instance;
    }
}
