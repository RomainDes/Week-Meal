package com.example.weekmeal.controler;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.weekmeal.entity.Diet;
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
import java.util.Set;

public class DietController {

    private FirebaseFirestore db;
    private ArrayList<Diet> dietList;

    public ArrayList<Diet> getDietList(){
        return dietList;
    }

    public DietController(){
        db = FirebaseFirestore.getInstance();
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
        CollectionReference dbDiet = db.collection("Diet");
        dbDiet.add(diet).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(activity.getBaseContext(), "Diet added with success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Fail to add Diet !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void synchronizedWithDB(Activity activity ){
        CollectionReference dbDiet = db.collection("Diet");
        dietList = new ArrayList<>();

        dbDiet.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> listSnap = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot docSnap : listSnap){
                        Diet diet = new Diet(
                                docSnap.toObject(Diet.class).getId(),
                                docSnap.toObject(Diet.class).getTitle()
                        );
                        dietList.add(diet);
                    }
                    JSONTool.getInstance().writeJSON(activity, dietList, "Diet");
                    Toast.makeText(activity, dietList.size()+"Synchronized with data base !", Toast.LENGTH_SHORT).show();
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
