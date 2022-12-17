package com.example.weekmeal.controler;

import android.app.Activity;
import android.util.Pair;

import com.example.weekmeal.entity.GrosseryList;
import com.example.weekmeal.entity.Ingredient;
import com.example.weekmeal.entity.Planning;
import com.example.weekmeal.entity.Recipe;
import com.example.weekmeal.tools.JSONTool;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GrosseryListController {


    private List<GrosseryList> grosseryLists;

    public GrosseryListController() {
        grosseryLists = new ArrayList<>();
    }

    /**generate a grossery list
     * @param date: Date planed for the grossery by the user,
     * @param ingredientsList :list of ingredient with their quantities
     * */
    public void generateGrossery(List<Pair<String, Ingredient>> ingredientsList, Date date, Activity activity){
        GrosseryList gl = new GrosseryList(ingredientsList, date);
        //write in local files :
        readLocalBD(activity);
        gl.setId(grosseryLists.size());
        grosseryLists.add(gl);
        JSONTool.getInstance().writeJSON(activity, grosseryLists, "GrosseryList");
    }

    public void removeGrosseryById(int id, Activity activity){
        //get all the grossery :
        readLocalBD(activity);
        for(int i = 0; i < grosseryLists.size(); i++){
            if(grosseryLists.get(i).getId() == id){
                grosseryLists.remove(i);
                break;
            }
        }
        JSONTool.getInstance().writeJSON(activity, grosseryLists, "GrosseryList");
    }

    public void readLocalBD(Activity activity){
        grosseryLists = new ArrayList<>();
        ArrayList<LinkedTreeMap> grosseryListsLocal = (ArrayList<LinkedTreeMap>) JSONTool.getInstance().loadJSONFromAsset(
                activity, grosseryLists, "GrosseryList");
        for(LinkedTreeMap ltmGrosseryList : grosseryListsLocal){
            grosseryLists.add(convertLTM(ltmGrosseryList));
        }
    }

    public GrosseryList convertLTM(LinkedTreeMap ltmGrosseryList){
        Date date = new Date((String) ltmGrosseryList.get("grosseryDate"));
        Integer id = ((Double) ltmGrosseryList.get("id")).intValue();
        List<Pair<String, Ingredient>> ingredientList = new ArrayList<>();
        for(LinkedTreeMap ltmIngredient : (ArrayList<LinkedTreeMap>) ltmGrosseryList.get("ingredientList")){
            String ingredientId = (String) ltmIngredient.get("first");
            Ingredient ingredient = Ingredient.convertLTM((LinkedTreeMap) ltmIngredient.get("second"));
            ingredientList.add(new Pair<String, Ingredient>(ingredientId, ingredient));
        }
        GrosseryList grosseryList = new GrosseryList(ingredientList, date);
        grosseryList.setId(id);
        return grosseryList;
    }


    public GrosseryList getGrosseryListById(int i){
        for(GrosseryList g: grosseryLists)
            if(g.getId() == i)
                return grosseryLists.get(i);
        return null;
    }

    //Singleton :
    private static GrosseryListController instance;

    public static GrosseryListController getInstance(){
        if(instance == null){
            instance = new GrosseryListController();
        }
        return instance;
    }



    ///TEST grossery generate + remove:
    /*
//        RecipeController.getInstance().synchronizedWithDB(this);
//        IngredientController.getInstance().synchronizedWithDB(this);
        //get recipe :
        RecipeController.getInstance().readLocalDB(this);
        IngredientController.getInstance().readLocalDB(this);
        DietController.getInstance().readLocalDB(this);
        //new planning :
        Planning p = new Planning();
        p.addMeal("Lundi1", RecipeController.getInstance().getRecipeList());
        PlanningController.getInstance().addPlannings(p, this);
//        PlanningController.getInstance().readLocalBD(this);
        //generate liste :
        GrosseryListController.getInstance().generateGrossery(PlanningController.getInstance().getPlanningList().get(0).getAllIngredients(), new Date(), this);
        //remove grossery with id (the second register grossery) :
        GrosseryListController.getInstance().removeGrosseryById(1, this);

        */
}
