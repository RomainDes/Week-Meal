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

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class Connector {

    private FirebaseFirestore db;

    public Connector(){
        db = FirebaseFirestore.getInstance();
    }

    public void addEntity(Activity activity, Object entity){
        String type = entity.getClass().getName().split("\\.")[entity.getClass().getName().split("\\.").length-1];
        CollectionReference dbEntity = db.collection(type);
        dbEntity.add(entity).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(activity.getBaseContext(), type+" added with success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Fail to add "+type+" !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public <T> void synchronizedWithDB(Activity activity, T entity ) {
        List<T> objectList = new ArrayList<>();
        String type = entity.getClass().getName().split("\\.")[entity.getClass().getName().split("\\.").length-1];
        CollectionReference dbEntity = db.collection(type);
        dbEntity.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> listSnap = queryDocumentSnapshots.getDocuments();

                    switch (type){
                        case "Ingredient":
                            for (DocumentSnapshot docSnap : listSnap){
                                T Ingredient = (T) new Ingredient(
                                        docSnap.toObject(Ingredient.class).getId(),
                                        docSnap.toObject(Ingredient.class).getTitle()
                                );
                                //TODO: Debug

                                objectList.add(Ingredient);
                            }
                            break;
                        case "Diet":
                            for (DocumentSnapshot docSnap : listSnap){
                                T diet = (T) new Diet(
                                        docSnap.toObject(Diet.class).getId(),
                                        docSnap.toObject(Diet.class).getTitle()
                                );
                                objectList.add(diet);
                            }
                            break;
                    }

                    JSONTool.getInstance().writeJSON(activity, objectList, type);
                    JSONTool.getInstance().loadJSONFromAsset(activity, objectList, type);
                    Toast.makeText(activity, objectList.size()+" "+type+" synchronized with data base !", Toast.LENGTH_SHORT).show();
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

    private static Connector instance;

    public static Connector getInstance() {
        if(instance == null)
            instance = new Connector();
        return instance;
    }
}
