package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.weekmeal.controler.PlanningController;
import com.example.weekmeal.controler.RecipeController;
import com.example.weekmeal.entity.Diet;
import com.example.weekmeal.entity.Recipe;
import com.example.weekmeal.entity.Planning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MakeAChoice extends AppCompatActivity {

    private Button buttonReturn;
    private Button buttonChoose;
    private Button buttonChoose1;
    private Button buttonReturn1;
    private TextView name;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private LinearLayout linearLayout1;

    private  ArrayList<Boolean> toggleList = new ArrayList<>();
    private int choice;
    /*private RecyclerView recyclerView;
        private MyRecyclerViewAdapter adapter;
        private List<Image> mViewImage;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_achoice);

        name = (TextView) findViewById(R.id.make_choice_makeachoice);
        buttonReturn = (Button) findViewById(R.id.your_choice_buttonReturn);
        buttonChoose = (Button) findViewById(R.id.choiceChoose);
        // partie de Romain avec les boutons
        buttonChoose1 = (Button) findViewById(R.id.choiceChoose1);
        buttonReturn1 = (Button) findViewById(R.id.return1);
        radioGroup = (RadioGroup) findViewById(R.id.make_choice_radio);
        radioButton1 = (RadioButton) findViewById(R.id.choice1);
        radioButton2 = (RadioButton) findViewById(R.id.choice2);
        radioButton3 = (RadioButton) findViewById(R.id.choice3);
        linearLayout1 = (LinearLayout) findViewById(R.id.make_choice_linear1);




        Bundle extras = getIntent().getExtras();
        ArrayList<Diet> dietsTrue = new ArrayList<>();
        dietsTrue = (ArrayList<Diet>) getIntent().getSerializableExtra("dietsTrue");

        toggleList = (ArrayList<Boolean>) getIntent().getSerializableExtra("toggleList");
        int mealPerDay = Integer.parseInt(extras.getString("mealPerDay"));
        int meals = mealPerDay*7;
        int numberPeople = Integer.parseInt(extras.getString ("numberPeople"));
        String planningName = extras.getString("planningName");

        // Ouverture BDD et cherche tout les plats
        RecipeController.getInstance().synchronizedWithDB(this);
        RecipeController.getInstance().readLocalDB(this);//n'importe où dans l'activity pour récup les recettes :
        List<Recipe> recettes = RecipeController.getInstance().getRecipeList();//pour avoir la les recettes dans l'activity sous forme de liste d'objet Java:
        List<Recipe> recettesUtiles = new ArrayList<>();

       // Log.i("taille liste :  ",recettes.size()+ ""); //Taille de la liste du receipe controller
        for(int i=0; i<recettes.size();i++){ //pour toutes les recettes
           // Log.i("liste recette :  ",recettes.get(i).getTitle()+ "");
            for (int j=0; j<dietsTrue.size();j++){
                //Log.i("liste diets :  ",dietsTrue.get(j).getId()+1+ ""); // recuperation des ids dans la liste des dietsTRue
                for (int k=0;k<recettes.get(i).getDiets().size();k++){
                   //Log.i("liste dietsRecettes :  ",recettes.get(i).getDiets().get(k)+ "");
                     if (dietsTrue.get(j).getId()+1 == recettes.get(i).getDiets().get(k)){
                       //recup les repas en fonction de si ils ont été coché
                       //Log.i("repas :  ",recettes.get(i).getTitle()+ "");

                         recettesUtiles.add(recettes.get(i));
                   }
               }
            }
        }

        //permet d'enlever les doublons de la liste recette utiles
        List<Recipe> recettesUtilesFinales = new ArrayList<>();
        for (Recipe recipe : recettesUtiles){
            if(!recettesUtilesFinales.contains(recipe)){
                recettesUtilesFinales.add(recipe);
            }
        }

/*
        Log.i("taille listeUtile :  ",recettesUtilesFinales.size()+ "");
        for (int i =0 ; i< recettesUtilesFinales.size();i++){
            Log.i("liste utile :  ",recettesUtilesFinales.get(i).getTitle()+ "");
        }
*/


        Planning planning1 = new Planning(); // correspond au choix 1
        planning1.setId(1);
        for (int i = 0;  i <meals;i++){
            try {
                int compteur = 1;
                List<Recipe> recipePass =  new ArrayList<>();
                if (i < mealPerDay%meals ){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning1.addMeal("LundiR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*2){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning1.addMeal("MardiR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*3){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning1.addMeal("MercrediR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*4){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning1.addMeal("JeudiR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*5){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning1.addMeal("VendrediR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*6){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning1.addMeal("SamediR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*7){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning1.addMeal("DimancheR"+compteur,recipePass);
                }
                if (compteur == mealPerDay){
                    compteur =1;
                }
            }catch(Exception e){
                break;
            }
        }

        Collections.shuffle(recettesUtilesFinales);
        Planning planning2 = new Planning(); // correspond au choix 2
        planning2.setId(2);
        for (int i = 0;  i <meals;i++){
            try {
                int compteur = 1;
                List<Recipe> recipePass =  new ArrayList<>();
                if (i < mealPerDay%meals ){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning2.addMeal("LundiR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*2){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning2.addMeal("MardiR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*3){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning2.addMeal("MercrediR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*4){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning2.addMeal("JeudiR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*5){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning2.addMeal("VendrediR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*6){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning2.addMeal("SamediR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*7){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning2.addMeal("DimancheR"+compteur,recipePass);
                }
                if (compteur == mealPerDay){
                    compteur =1;
                }
        }catch(Exception e){
            break;
        }
        }

        Collections.shuffle(recettesUtilesFinales);
        Planning planning3 = new Planning(); // correspond au choix 3
        planning3.setId(3);
        for (int i = 0;  i <meals;i++){
            try {
                int compteur = 1;
                List<Recipe> recipePass =  new ArrayList<>();
                if (i < mealPerDay%meals ){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning3.addMeal("LundiR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*2){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning3.addMeal("MardiR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*3){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning3.addMeal("MercrediR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*4){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning3.addMeal("JeudiR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*5){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning3.addMeal("VendrediR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*6){
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning3.addMeal("SamediR"+compteur,recipePass);
                }else if (i < (mealPerDay%meals)*7) {
                    recipePass.add(recettesUtilesFinales.get(i));
                    planning3.addMeal("DimancheR" + compteur, recipePass);
                }
                if (compteur == mealPerDay){
                    compteur =1;
                }
            }catch(Exception e){
                break;
            }
        }


        Log.i("planning1:  ",planning1.getMealMenu("LundiR1").get(0).getTitle()+ "");
        Log.i("planning1:  ",planning1.getMealMenu("DimancheR1").get(0).getTitle()+ "");
        Log.i("planning2:  ",planning2.getMealMenu("LundiR1").get(0).getTitle()+ "");
        Log.i("planning2:  ",planning2.getMealMenu("DimancheR1").get(0).getTitle()+ "");
        Log.i("planning3:  ",planning3.getMealMenu("LundiR1").get(0).getTitle()+ "");
        Log.i("planning3:  ",planning3.getMealMenu("DimancheR1").get(0).getTitle()+ "");




        name.setText("MAKE A CHOICE FOR "+ planningName);
        if(extras.getString ("choice") != null){
            choice =Integer.parseInt(extras.getString ("choice"));
            if (choice == 1){
                radioButton1.setChecked(true);
            }else if (choice ==2){
                radioButton2.setChecked(true);
            }else if (choice ==3){
                radioButton3.setChecked(true);
            }
        }

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (radioButton1.isChecked() == false && radioButton2.isChecked() == false && radioButton3.isChecked() == false ){

                }else{
                    if(radioButton1.isChecked()){
                        choice= 1;
                    }else if(radioButton2.isChecked()){
                        choice =2;
                    }else if (radioButton3.isChecked()){
                        choice =3;
                    }
                    Intent intent = new Intent(MakeAChoice.this, yourchoice.class);
                    intent.putExtra("toggleList",toggleList);
                    intent.putExtra("mealPerDay",mealPerDay +"");
                    intent.putExtra("numberPeople", numberPeople+"");
                    intent.putExtra("planningName", planningName);
                    intent.putExtra("choice",choice+"");
                    // ajouter envoie dietTrue
                    // ajouter envoie des plannings 
                    startActivity(intent);
                   }
            }
        });

    buttonReturn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(MakeAChoice.this, MainActivity.class);
            intent.putExtra("toggleList",toggleList);
            intent.putExtra("mealPerDay",mealPerDay +"");
            intent.putExtra("numberPeople", numberPeople+"");
            intent.putExtra("planningName", planningName);
            startActivity(intent);
        }
    });

    // partie de Romain avec les boutons
        buttonChoose1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (radioButton1.isChecked() == false && radioButton2.isChecked() == false && radioButton3.isChecked() == false ){

                }else{
                    if(radioButton1.isChecked()){
                        choice= 1;
                    }else if(radioButton2.isChecked()){
                        choice =2;
                    }else if (radioButton3.isChecked()){
                        choice =3;
                    }
                    Intent intent = new Intent(MakeAChoice.this, yourchoice.class);
                    intent.putExtra("toggleList",toggleList);
                    intent.putExtra("mealPerDay",mealPerDay +"");
                    intent.putExtra("numberPeople", numberPeople+"");
                    intent.putExtra("planningName", planningName);
                    intent.putExtra("choice",choice+"");
                    startActivity(intent);
                }

            }
        });

        buttonReturn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MakeAChoice.this, MainActivity.class);
                intent.putExtra("toggleList",toggleList);
                intent.putExtra("mealPerDay",mealPerDay +"");
                intent.putExtra("numberPeople", numberPeople+"");
                intent.putExtra("planningName", planningName);
                startActivity(intent);
            }
        });

 /*for (int i=1; i<= meals;i++){
            //ConstraintLayout constraint1 = new ConstraintLayout(getApplicationContext());
            LinearLayout.LayoutParams linearLayoutparam1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            Button button1 = new Button(getApplicationContext());
            button1.setText("choice 1 : "+i);
            button1.setId(View.generateViewId());
            linearLayout1.addView(button1,linearLayoutparam1);

            //constraint1.addView(button1);
            //linearLayout1.addView(constraint1);
        }*/

        /*LinearLayoutManager layoutManager
                = new LinearLayoutManager(MakeAChoice.this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myList = (RecyclerView) findViewById(R.id.recyclerview);
        myList.setLayoutManager(layoutManager);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, );
        recyclerView.setAdapter(adapter);*/
    }
}