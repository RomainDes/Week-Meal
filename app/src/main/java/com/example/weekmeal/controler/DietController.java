package com.example.weekmeal.controler;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.weekmeal.entity.Diet;
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
import java.util.Set;

public class DietController {



    private List<Diet> dietList;

    public List<Diet> getDietList(){
        return dietList;
    }

    public DietController(){
        dietList = new ArrayList<>();
    }

    @Override
    public String toString(){
        String str = new String();
        str = str.concat("Diets :\n");
        for(Diet d : dietList){
            str = str.concat(d.toString());
        }
        str = str.concat("---");
        return str;
    }

    public void addDiet(Diet diet, Activity activity){
        Connector.getInstance().addEntity(activity, diet);
        synchronizedWithDB(activity);
    }

    public void synchronizedWithDB(Activity activity ){
        dietList = new ArrayList<>();
        Connector.getInstance().synchronizedWithDB(activity, new Diet());
    }

    public void readLocalDB(Activity activity){
        ArrayList<LinkedTreeMap> dietsLocal = (ArrayList<LinkedTreeMap>) JSONTool.getInstance().loadJSONFromAsset(activity, dietList, "Diet");
        for(LinkedTreeMap ltm : dietsLocal){
            Object[] keyset = ltm.keySet().toArray();
            dietList.add(new Diet(new Integer(((Double) ltm.get(keyset[0])).intValue()), (String) ltm.get(keyset[1])));
        }
    }


    public Diet getDietByID(Activity activity, int i){
        readLocalDB(activity);
        for(Diet diet: dietList){
            if (diet.getId() == i)
                return diet;
        }

        return null;
    }

    public Diet getDietByTitle(Activity activity, String title){
        readLocalDB(activity);
        for(Diet diet: dietList){
            if (diet.getTitle().equals(title))
                return diet;
        }

        return null;
    }

    //Singleton:
    private static DietController instance;

    public static DietController getInstance() {
        if(instance == null){
            instance = new DietController();
        }
        return instance;
    }

    public ArrayList<Diet> getDietTrue(ArrayList<Diet> dietsTrue,boolean toggle,String valueToggle){
        int indice = -1;
        if (toggle == true){
            if (valueToggle.equals("PASTA")){
                indice =0;
            }else if (valueToggle.equals("RICE")){
                indice =1;
            }else if(valueToggle.equals("HEALTHY")){
                indice =2;
            }else if(valueToggle.equals("STEAK")){
                indice =3;
            }else if(valueToggle.equals("CHICKEN")){
                indice =4;
            }else if(valueToggle.equals("POTATOES")){
                indice =5;
            }else if(valueToggle.equals("OVEN")){
                indice =6;
            }else if(valueToggle.equals("EGG")){
                indice =7;
            }else if(valueToggle.equals("VEGETARIAN")){
                indice =8;
            }else if(valueToggle.equals("BIG EATER")){
                indice =9;
            }else if(valueToggle.equals("MEDIUM\nEATER")){
                indice =10;
            }else if(valueToggle.equals("LITTLE\nEATER")){
                indice =11;
            }else if(valueToggle.equals("CHESSE")){
                indice =12;
            }else if(valueToggle.equals("TOMATOES")){
                indice =13;
            }else if(valueToggle.equals("PEPPER")){
                indice =14;
            }else if(valueToggle.equals("ONION")){
                indice =15;
            }else if(valueToggle.equals("CUCUMBER")){
                indice =16;
            }else if(valueToggle.equals("CARROTS")){
                indice =17;
            }else if(valueToggle.equals("LETTUCE")){
                indice =18;
            }else if(valueToggle.equals("SPINACH")){
                indice =19;
            }else if(valueToggle.equals("GARLIC")){
                indice =20;
            }else if(valueToggle.equals("FISH")){
                indice =21;
            }
            Diet diet = new Diet(indice ,valueToggle);
            dietsTrue.add(diet);
        }

    return dietsTrue;
    }
}
