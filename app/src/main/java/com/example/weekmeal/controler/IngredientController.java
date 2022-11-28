package com.example.weekmeal.controler;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.weekmeal.entity.Ingredient;
import com.example.weekmeal.tools.JSONTool;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
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
            ingredientList.add(new Ingredient(new Integer(((Double) ltm.get(keyset[0])).intValue()), (String) ltm.get(keyset[1])));
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
