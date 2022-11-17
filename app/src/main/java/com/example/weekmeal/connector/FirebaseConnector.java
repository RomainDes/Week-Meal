package com.example.weekmeal.connector;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.weekmeal.entity.Diet;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Console;

public class FirebaseConnector {

    private FirebaseFirestore db;

    public static FirebaseConnector instance;

    public static FirebaseConnector instance(){
        if(instance == null){
            instance = new FirebaseConnector();
        }
        return instance;
    }

    public FirebaseConnector(){
        db = FirebaseFirestore.getInstance();
    }

    public void addDiet(Diet diet, Activity activity){
        CollectionReference dbDiet = db.collection("Diet");
        System.out.println("creating diet");
        dbDiet.add(diet).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(activity.getBaseContext(), "Diet added with success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity.getBaseContext(), "Fail to add Diet !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
