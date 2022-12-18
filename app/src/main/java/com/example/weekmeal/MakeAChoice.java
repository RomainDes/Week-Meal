package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.weekmeal.controler.IngredientController;
import com.example.weekmeal.controler.PlanningController;
import com.example.weekmeal.controler.RecipeController;
import com.example.weekmeal.entity.Diet;
import com.example.weekmeal.entity.Recipe;
import com.example.weekmeal.entity.Planning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
    private LinearLayout choice1Planning;
    private LinearLayout choice2Planning;
    private LinearLayout choice3Planning;
    private LinearLayout makeachoice;
    private Button back1;
    private Button back2;
    private Button back3;


    private LinearLayout choice1Recette1;

    private Button backchoice1Recette1;



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
        buttonReturn = (Button) findViewById(R.id.return2);
        buttonChoose = (Button) findViewById(R.id.choose1);
        // partie de Romain avec les boutons
        buttonChoose1 = (Button) findViewById(R.id.choose2);
        buttonReturn1 = (Button) findViewById(R.id.return1);
        radioGroup = (RadioGroup) findViewById(R.id.make_choice_radio);
        radioButton1 = (RadioButton) findViewById(R.id.choice1);
        radioButton2 = (RadioButton) findViewById(R.id.choice2);
        radioButton3 = (RadioButton) findViewById(R.id.choice3);
        choice1Planning = (LinearLayout) findViewById(R.id.choice1Planning);
        choice2Planning = (LinearLayout) findViewById(R.id.choice2Planning);
        choice3Planning = (LinearLayout) findViewById(R.id.choice3Planning);
        makeachoice = (LinearLayout) findViewById(R.id.makeachoice);
        back1 = (Button) findViewById(R.id.back1);
        back2 = (Button) findViewById(R.id.back2);
        back3 = (Button) findViewById(R.id.back3);





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
        Log.i("taille listeUtile :  ",recettesUtilesFinales.size()+ "");
         if(recettesUtilesFinales.size() != meals){
             Collections.shuffle(recettes);
             int repasManquants = meals - recettesUtilesFinales.size();
             for(int i=0; i<repasManquants; i++){
                 recettesUtilesFinales.add(recettes.get(i));
                 if (i == recettes.size()- 1){
                    i = 0;
                 }
                 if(meals == recettesUtilesFinales.size()){
                     break;
                 }
             }
         }



        Log.i("taille listeUtile :  ",recettesUtilesFinales.size()+ "");
 /*       for (int i =0 ; i< recettesUtilesFinales.size();i++){
            Log.i("liste utile :  ",recettesUtilesFinales.get(i).getTitle()+ "");
        }
*/


        Planning planning1 =  getIntent().getParcelableExtra("planning1"); // correspond au choix 1
        if (planning1 == null){
            planning1 = new Planning();
            planning1.setId("planning1");
            int compteur = 1;
            for (int i = 0;  i <meals;i++){

                try {

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
                    compteur ++;
                    if (compteur == mealPerDay+1){
                        compteur =1;
                    }

                }catch(Exception e){
                    break;
                }
            }
        }

        Planning planning2 = getIntent().getParcelableExtra("planning2"); // correspond au choix 2
        if (planning2 == null){
            Collections.shuffle(recettesUtilesFinales);
            planning2 = new Planning();
            planning2.setId("planning2");
            int compteur = 1;
            for (int i = 0;  i <meals;i++){
                try {
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
                    compteur ++;
                    if (compteur == mealPerDay){
                        compteur =1;
                    }
                }catch(Exception e){
                    break;
                }
            }
        }



        Planning planning3 = getIntent().getParcelableExtra("planning3"); // correspond au choix 3
        if (planning3 == null){
            Collections.shuffle(recettesUtilesFinales);
            planning3 = new Planning();
            planning3.setId("planning3");
            int compteur = 1;
            for (int i = 0;  i <meals;i++){
                try {

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
                    compteur ++;
                    if (compteur == mealPerDay){
                        compteur =1;
                    }
                }catch(Exception e){
                    break;
                }
            }
        }



        Log.i("planning1:  ",planning1.getMealMenu("LundiR1").get(0).getTitle()+ "");
        Log.i("planning1:  ",planning1.getMealMenu("DimancheR1").get(0).getTitle()+ "");
        //Log.i("planning2:  ",planning2.getMealMenu("LundiR1").get(0).getTitle()+ "");
        //Log.i("planning2:  ",planning2.getMealMenu("DimancheR1").get(0).getTitle()+ "");
        //Log.i("planning3:  ",planning3.getMealMenu("LundiR1").get(0).getTitle()+ "");
        //Log.i("planning3:  ",planning3.getMealMenu("DimancheR1").get(0).getTitle()+ "");




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

        Planning finalPlanning1 = planning1;
        Planning finalPlanning2 = planning2;
        Planning finalPlanning3 = planning3;

        Activity activity = this;
        
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
                    intent.putExtra("planning1", finalPlanning1.getId());
                    intent.putExtra("planning2", finalPlanning2.getId());
                    intent.putExtra("planning3", finalPlanning3.getId());
                    intent.putExtra("mealPerDay",mealPerDay +"");
                    intent.putExtra("numberPeople", numberPeople+"");
                    intent.putExtra("planningName", planningName);
                    intent.putExtra("choice",choice+"");
                    // ajouter envoie dietTrue
                    // ajouter envoie des plannings
                    startActivity(intent);

                    //register planning :
                    PlanningController.getInstance().addPlannings(finalPlanning1, activity);
                    PlanningController.getInstance().addPlannings(finalPlanning2, activity);
                    PlanningController.getInstance().addPlannings(finalPlanning3, activity);
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
                    intent.putExtra("planning1", finalPlanning1.getId());
                    intent.putExtra("planning2", finalPlanning2.getId());
                    intent.putExtra("planning3", finalPlanning3.getId());
                    intent.putExtra("mealPerDay",mealPerDay +"");
                    intent.putExtra("numberPeople", numberPeople+"");
                    intent.putExtra("planningName", planningName);
                    intent.putExtra("choice",choice+"");
                    // ajouter envoie dietTrue
                    // ajouter envoie des plannings
                    startActivity(intent);

                    //register planning :
                    PlanningController.getInstance().addPlannings(finalPlanning1, activity);
                    PlanningController.getInstance().addPlannings(finalPlanning2, activity);
                    PlanningController.getInstance().addPlannings(finalPlanning3, activity);
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


        radioButton1.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onDoubleClick() {
 					choice1Planning.setVisibility(View.VISIBLE);
                    makeachoice.setVisibility(View.GONE);
 			}
        });

        radioButton2.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onDoubleClick() {
                choice2Planning.setVisibility(View.VISIBLE);
                makeachoice.setVisibility(View.GONE);
            }
        });

        radioButton3.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onDoubleClick() {
                choice3Planning.setVisibility(View.VISIBLE);
                makeachoice.setVisibility(View.GONE);
            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                choice1Planning.setVisibility(View.GONE);
                makeachoice.setVisibility(View.VISIBLE);
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                choice2Planning.setVisibility(View.GONE);
                makeachoice.setVisibility(View.VISIBLE);
            }
        });

        back3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                choice3Planning.setVisibility(View.GONE);
                makeachoice.setVisibility(View.VISIBLE);
            }
        });


        //Planning1
        Button bp1r1 = (Button) findViewById(R.id.BP1R1);
        bp1r1.setText(planning1.getMealMenu("LundiR1").get(0).getTitle());
        Button bp1r2 = (Button) findViewById(R.id.BP1R2);
        bp1r2.setText(planning1.getMealMenu("LundiR2").get(0).getTitle());
        Button bp1r3 = (Button) findViewById(R.id.BP1R3);
        bp1r3.setText(planning1.getMealMenu("MardiR1").get(0).getTitle());
        Button bp1r4 = (Button) findViewById(R.id.BP1R4);
        bp1r4.setText(planning1.getMealMenu("MardiR2").get(0).getTitle());
        Button bp1r5 = (Button) findViewById(R.id.BP1R5);
        bp1r5.setText(planning1.getMealMenu("MercrediR1").get(0).getTitle());
        Button bp1r6 = (Button) findViewById(R.id.BP1R6);
        bp1r6.setText(planning1.getMealMenu("MercrediR2").get(0).getTitle());
        Button bp1r7 = (Button) findViewById(R.id.BP1R7);
        bp1r7.setText(planning1.getMealMenu("JeudiR1").get(0).getTitle());
        Button bp1r8 = (Button) findViewById(R.id.BP1R8);
        bp1r8.setText(planning1.getMealMenu("JeudiR2").get(0).getTitle());
        Button bp1r9 = (Button) findViewById(R.id.BP1R9);
        bp1r9.setText(planning1.getMealMenu("VendrediR1").get(0).getTitle());
        Button bp1r10 = (Button) findViewById(R.id.BP1R10);
        bp1r10.setText(planning1.getMealMenu("VendrediR2").get(0).getTitle());
        Button bp1r11 = (Button) findViewById(R.id.BP1R11);
        bp1r11.setText(planning1.getMealMenu("SamediR1").get(0).getTitle());
        Button bp1r12 = (Button) findViewById(R.id.BP1R12);
        bp1r12.setText(planning1.getMealMenu("SamediR2").get(0).getTitle());
        Button bp1r13 = (Button) findViewById(R.id.BP1R13);
        bp1r13.setText(planning1.getMealMenu("DimancheR1").get(0).getTitle());
        Button bp1r14 = (Button) findViewById(R.id.BP1R14);
        bp1r14.setText(planning1.getMealMenu("DimancheR2").get(0).getTitle());
        ArrayList<Button> buttonp1 =  new ArrayList<>();
        buttonp1.add(bp1r1);
        buttonp1.add(bp1r2);
        buttonp1.add(bp1r3);
        buttonp1.add(bp1r4);
        buttonp1.add(bp1r5);
        buttonp1.add(bp1r6);
        buttonp1.add(bp1r7);
        buttonp1.add(bp1r8);
        buttonp1.add(bp1r9);
        buttonp1.add(bp1r10);
        buttonp1.add(bp1r11);
        buttonp1.add(bp1r12);
        buttonp1.add(bp1r13);
        buttonp1.add(bp1r14);



        choice1Recette1  = (LinearLayout) findViewById(R.id.choice1Recette1);
        backchoice1Recette1 = (Button) findViewById(R.id.backchoice1Recette1);

        for (int i = 0; i<14; i++){
            String repas = null;
            if (i == 0){
                repas = "LundiR1";
            }else if (i == 1){
                repas = "LundiR2";
            }else if (i == 2){
                repas = "MardiR1";
            }else if (i == 3){
                repas = "MardiR2";
            }else if (i == 4){
                repas = "MercrediR1";
            }else if (i == 5){
                repas = "MercrediR2";
            }else if (i == 6){
                repas = "JeudiR1";
            }else if (i == 7){
                repas = "JeudiR1";
            }else if (i == 8){
                repas = "VendrediR1";
            }else if (i == 9){
                repas = "VendrediR1";
            }else if (i == 10){
                repas = "SamediR1";
            }else if (i == 11){
                repas = "SamediR2";
            }else if (i == 12){
                repas = "DimancheR1";
            }else if (i == 13){
                repas = "DimancheR2";
            }
            String finalRepas = repas;
            buttonp1.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    choice1Planning.setVisibility(View.GONE);
                    TextView plat = (TextView) findViewById(R.id.p1recette);
                    plat.setText(finalPlanning1.getMealMenu(finalRepas).get(0).getTitle());
                    TextView ingredients = (TextView) findViewById(R.id.p1ingrédients);

                    String ingr ="";
                    HashMap<String, Integer> in = finalPlanning1.getMealMenu(finalRepas).get(0).getIngredients();
                    for(String keyi : in.keySet()){
                       int idi = in.get(keyi);
                       String quantity= keyi.split("--")[0];
                       ingr = ingr +"\n"+ quantity +" " + IngredientController.getInstance().getIngredientByID(idi).getQuantity() + " "  + IngredientController.getInstance().getIngredientByID(idi).toString();

                    }

                    ingredients.setText( ingr);
                    Log.i("planning1ing:  ",ingr+ "");
                    TextView  recette = (TextView) findViewById(R.id.p1platrecette);
                    recette.setText(finalPlanning1.getMealMenu(finalRepas).get(0).getDirection().toString());
                    choice1Recette1.setVisibility(View.VISIBLE);
                }
            });
        }
        //Recette1


        backchoice1Recette1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                choice1Planning.setVisibility(View.VISIBLE);
                choice1Recette1.setVisibility(View.GONE);
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