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

import java.util.ArrayList;
import java.util.List;

public class DietController {

    private FirebaseFirestore db;
    private ArrayList<Diet> dietList;

    public DietController(){
        db = FirebaseFirestore.getInstance();
        dietList = new ArrayList<>();
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

    public void readDiet(Activity activity ){
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
                    Toast.makeText(activity, dietList.size()+" diets found !", Toast.LENGTH_SHORT).show();
//                    synchronizedWithDB(activity);
                    readLocalDB(activity);
                }
                else{
                    Toast.makeText(activity, "No data found", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Fail to get the data", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void synchronizedWithDB(Activity activity){
        JSONTool.getInstance().writeJSON(activity, dietList, "Diet");
    }

    public void readLocalDB(Activity activity){
        ArrayList<Diet> dietsLocal = (ArrayList<Diet>) JSONTool.getInstance().loadJSONFromAsset(activity, dietList, "Diet");
        int i = 0;
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
