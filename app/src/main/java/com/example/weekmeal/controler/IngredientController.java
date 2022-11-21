package com.example.weekmeal.controler;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.weekmeal.entity.Ingredient;
import com.example.weekmeal.tools.JSONTool;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class IngredientController {

    private FirebaseFirestore db;
    private ArrayList<Ingredient> ingredientList;

    public ArrayList<Ingredient> getIngredientList(){
        return ingredientList;
    }

    public IngredientController(){
        db = FirebaseFirestore.getInstance();
        ingredientList = new ArrayList<>();
    }

    @Override
    public String toString(){
        String str = new String();
        str = str.concat("Ingredients :\n");
        for(Ingredient d : ingredientList){
            str = str.concat(d.getId()+": "+d.getTitle()+"\n");
        }
        str = str.concat("---");
        return str;
    }

    public void addIngredient(Ingredient Ingredient, Activity activity){
        CollectionReference dbIngredient = db.collection("Ingredient");
        dbIngredient.add(Ingredient).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(activity.getBaseContext(), "Ingredient added with success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Fail to add Ingredient !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void synchronizedWithDB(Activity activity ){
        CollectionReference dbIngredient = db.collection("Ingredient");
        ingredientList = new ArrayList<>();

        dbIngredient.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> listSnap = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot docSnap : listSnap){
                        Ingredient Ingredient = new Ingredient(
                                docSnap.toObject(Ingredient.class).getId(),
                                docSnap.toObject(Ingredient.class).getTitle()
                        );
                        ingredientList.add(Ingredient);
                    }
                    JSONTool.getInstance().writeJSON(activity, ingredientList, "Ingredient");
                    Toast.makeText(activity, ingredientList.size()+"Synchronized with data base !", Toast.LENGTH_SHORT).show();
//                    readLocalDB(activity);
                }
                else{
                    Toast.makeText(activity, "Fail to synchronize !", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Fail to connect !", Toast.LENGTH_SHORT).show();
            }
        });
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
