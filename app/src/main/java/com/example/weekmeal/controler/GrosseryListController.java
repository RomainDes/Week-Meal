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
    public void generateGrossery(String id, List<Pair<String, Ingredient>> ingredientsList, Date date, Activity activity){
        GrosseryList gl = new GrosseryList(ingredientsList, date);
        //write in local files :
        readLocalBD(activity);
        gl.setId(id);
        //replace if exist :
        boolean isRegistered = false;
        for(int i = 0; i< this.grosseryLists.size(); i++){
            if(this.grosseryLists.get(i).getId().equals(id)) {
                grosseryLists.set(i, gl);
                isRegistered = true;
            }
        }
        if(!isRegistered)
            grosseryLists.add(gl);
        JSONTool.getInstance().writeJSON(activity, grosseryLists, "GrosseryList");
    }

    public void removeGrosseryById(String id, Activity activity){
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
        String id = (String) ltmGrosseryList.get("id");
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


    public GrosseryList getGrosseryListById(String i){
        for(GrosseryList g: grosseryLists)
            if(g.getId().equals(i))
                return g;
        return null;
    }

    public void multiplyByNbPeople(String idGrosseryList, int nbPeople){
        GrosseryList g = getGrosseryListById(idGrosseryList);
        for (int i =0; i < g.getIngredientList().size(); i++){
            String quantity = g.getIngredientList().get(i).first.split("--")[0];
            String id = g.getIngredientList().get(i).first.split("--")[1];
            String newQuantity = String.valueOf(StringtoDouble(quantity) * nbPeople);
            g.setIngredientList(i, new Pair<>(newQuantity+"--"+id, g.getIngredientList().get(i).second));
        }
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
