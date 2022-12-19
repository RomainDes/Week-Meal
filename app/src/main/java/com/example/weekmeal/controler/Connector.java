package com.example.weekmeal.controler;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.weekmeal.entity.Diet;
import com.example.weekmeal.entity.Ingredient;
import com.example.weekmeal.entity.Recipe;
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

public class Connector {

    private FirebaseFirestore db;

    public Connector(){
        db = FirebaseFirestore.getInstance();
    }

    public <T> void addEntity(Activity activity, T entity){
        String type = entity.getClass().getName().split("\\.")[entity.getClass().getName().split("\\.").length-1];
        CollectionReference dbEntity = db.collection(type);

        dbEntity.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int lastId = 0;
                T entityToAdd = null;
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> listSnap = queryDocumentSnapshots.getDocuments();
                    List<T> entityList = new ArrayList<>();
                    switch (type){
                        case "Ingredient":
                            for (DocumentSnapshot docSnap : listSnap){
                                T Ingredient = (T) new Ingredient(
                                        docSnap.toObject(Ingredient.class).getId(),
                                        docSnap.toObject(Ingredient.class).getTitle(),
                                        docSnap.toObject(Ingredient.class).getQuantity());
                                entityList.add(Ingredient);
                            }

                            //look for the last id added:
                            for(Ingredient i : (List<Ingredient>) entityList){
                                if(i.getId() > lastId)
                                    lastId = i.getId();
                            }
                             entityToAdd =(T) new Ingredient(lastId+1, ((Ingredient) entity).getTitle(), ((Ingredient) entity).getQuantity());
                            break;
                        case "Diet":
                            for (DocumentSnapshot docSnap : listSnap){
                                T diet = (T) new Diet(
                                        docSnap.toObject(Diet.class).getId(),
                                        docSnap.toObject(Diet.class).getTitle()
                                );
                                entityList.add(diet);
                            }

                            //look for the last id added:
                            for(Diet i : (List<Diet>) entityList){
                                if(i.getId() > lastId)
                                    lastId = i.getId();
                            }
                            entityToAdd =(T) new Diet(lastId+1, ((Diet) entity).getTitle());
                            break;
                        case "Recipe":
                            for (DocumentSnapshot docSnap : listSnap){
                                T recipe = (T) new Recipe(
                                        docSnap.toObject(Recipe.class).getId(),
                                        docSnap.toObject(Recipe.class).getTitle(),
                                        docSnap.toObject(Recipe.class).getDirection(),
                                        docSnap.toObject(Recipe.class).getIngredients(),
                                        docSnap.toObject(Recipe.class).getDiets()
                                );
                                entityList.add(recipe);
                            }
                            //look for the last id added:
                            for(Recipe i : (List<Recipe>) entityList){
                                if(i.getId() > lastId)
                                    lastId = i.getId();
                            }
                            entityToAdd =(T) new Recipe(lastId+1, ((Recipe) entity).getTitle(), ((Recipe) entity).getDirection(), ((Recipe) entity).getIngredients(), ((Recipe) entity).getDiets());
                            break;
                    }
                }
                else {
                    entityToAdd = entity;
                }
                dbEntity.add(entityToAdd).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    //Toast.makeText(activity.getBaseContext(), type+" added with success", Toast.LENGTH_SHORT).show();
                }
                });
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
                                        docSnap.toObject(Ingredient.class).getTitle(),
                                        docSnap.toObject(Ingredient.class).getQuantity());
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
                            case "Recipe":
                            for (DocumentSnapshot docSnap : listSnap){
                                T recipe = (T) new Recipe(
                                        docSnap.toObject(Recipe.class).getId(),
                                        docSnap.toObject(Recipe.class).getTitle(),
                                        docSnap.toObject(Recipe.class).getDirection(),
                                        docSnap.toObject(Recipe.class).getIngredients(),
                                        docSnap.toObject(Recipe.class).getDiets()
                                );
                                objectList.add(recipe);
                            }
                            break;
                    }

                    JSONTool.getInstance().writeJSON(activity, objectList, type);
                    JSONTool.getInstance().loadJSONFromAsset(activity, objectList, type);
                    //Toast.makeText(activity, objectList.size()+" "+type+" synchronized with data base !", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(activity, "Fail to synchronize !", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(activity, "Fail to connect !", Toast.LENGTH_SHORT).show();
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
