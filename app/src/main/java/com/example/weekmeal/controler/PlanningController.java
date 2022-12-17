package com.example.weekmeal.controler;

import android.app.Activity;

import com.example.weekmeal.entity.Ingredient;
import com.example.weekmeal.entity.Planning;
import com.example.weekmeal.entity.Recipe;
import com.example.weekmeal.tools.JSONTool;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlanningController {

    private List<Planning> planningList;

    public List<Planning> getPlanningList() {
        return planningList;
    }

    public PlanningController(){
        this.planningList = new ArrayList<Planning>();
    }

    public void addPlannings(Planning planning, Activity activity){
        readLocalBD(activity);
        planning.setId(planningList.size());
        this.planningList.add(planning);
        JSONTool.getInstance().writeJSON(activity, planningList, "Planning");
    }

    public void readLocalBD(Activity activity){
        planningList = new ArrayList<>();
        ArrayList<LinkedTreeMap> planningsLocal = (ArrayList<LinkedTreeMap>)  JSONTool.getInstance().loadJSONFromAsset(activity, planningList, "Planning");
        for (LinkedTreeMap ltm : planningsLocal){
            //get every Recipes :
            HashMap<String, List<Recipe>> mealsMenu = new HashMap<>();
            LinkedTreeMap recipeListLTM = (LinkedTreeMap) ltm.get("mealsMenu");
            for (Object keyLtm: recipeListLTM.keySet()){
                //convert to List<recipe> :
                List<Recipe> listToAdd = new ArrayList<>();
                for(LinkedTreeMap ltmRecipe: (List<LinkedTreeMap>)recipeListLTM.get(keyLtm)) {

                    listToAdd.add(RecipeController.getInstance().convertLTM(ltmRecipe));
                }
                mealsMenu.put((String) keyLtm, listToAdd);
            }

            planningList.add(new Planning(
                    ((Double)ltm.get("id")).intValue(),
                    mealsMenu
            ));
        }
    }

    //Singleton:
    private static PlanningController instance;

    public static PlanningController getInstance() {
        if(instance == null)
            instance = new PlanningController();
        return instance;
    }
}
