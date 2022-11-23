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
            str = str.concat(d.getId()+": "+d.getTitle()+"\n");
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
    //Singleton:
    private static DietController instance;

    public static DietController getInstance() {
        if(instance == null){
            instance = new DietController();
        }
        return instance;
    }
}
