package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weekmeal.controler.GrosseryListController;
import com.example.weekmeal.controler.IngredientController;
import com.example.weekmeal.controler.PlanningController;
import com.example.weekmeal.controler.RecipeController;
import com.example.weekmeal.entity.Planning;
import com.example.weekmeal.entity.Recipe;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class yourchoice extends AppCompatActivity {

    private Button buttonReturn;
    private Button buttonReturn1;
    private Button buttonShare;
    private Button buttonShare1;
    private TextView yourChoice;
    private Button buttonShopping;
    private Button buttonPlanning;

    private List<Button> mealsButtonList;


    private ArrayList<Boolean> toggleList = new ArrayList<>();
    private Planning planning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourchoice);

        LinearLayout planningLayout = (LinearLayout) findViewById(R.id.planningLayout);
        LinearLayout choice1Recette1 = (LinearLayout) findViewById(R.id.choice1Recette1);

        buttonReturn = (Button) findViewById(R.id.return1);
        buttonReturn1 = (Button) findViewById(R.id.return2);

        //bouttons share à faire
        buttonShare = (Button) findViewById(R.id.choose1);
        buttonShare1 = (Button) findViewById(R.id.choose2);
        yourChoice  = (TextView) findViewById(R.id.your_choice_textChoice);

        buttonShopping = (Button) findViewById(R.id.shopping);
        buttonShopping.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                Intent intent = new Intent(yourchoice.this, yourchoice2.class);
//                startActivity(intent);
                findViewById(R.id.planningLayout).setVisibility(View.GONE);
                findViewById(R.id.shoppingLayout).setVisibility(View.VISIBLE);
            }
        });

        buttonPlanning = (Button) findViewById(R.id.planning);
        buttonPlanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.planningLayout).setVisibility(View.VISIBLE);
                findViewById(R.id.shoppingLayout).setVisibility(View.GONE);
            }
        });


        Bundle extras = getIntent().getExtras();
        toggleList = (ArrayList<Boolean>) getIntent().getSerializableExtra("toggleList");
        int mealPerDay = Integer.parseInt(extras.getString("mealPerDay"));
        int numberPeople = Integer.parseInt(extras.getString ("numberPeople"));
        String planningName = extras.getString("planningName");
        int choice = Integer.parseInt(extras.getString("choice"));

        yourChoice.setText("YOUR CHOICE " +choice+"");

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(yourchoice.this, MakeAChoice.class);
                intent.putExtra("toggleList",toggleList);
                intent.putExtra("mealPerDay",mealPerDay +"");
                intent.putExtra("numberPeople", numberPeople+"");
                intent.putExtra("planningName", planningName);
                intent.putExtra("choice", choice+"");
                startActivity(intent);
            }
        });

        buttonReturn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(yourchoice.this, MakeAChoice.class);
                intent.putExtra("toggleList",toggleList);
                intent.putExtra("mealPerDay",mealPerDay +"");
                intent.putExtra("numberPeople", numberPeople+"");
                intent.putExtra("planningName", planningName);
                intent.putExtra("choice", choice+"");
                startActivity(intent);
            }
        });

        //get the selected planning :
        planning = PlanningController.getInstance().getPlanningById("planning"+choice, this);

        TextView shoppingList = (TextView) findViewById(R.id.shoppingList);
        GrosseryListController.getInstance().generateGrossery("grosseryList"+choice, planning.getAllIngredients(), new Date(), this);
        //setting up quantity to match people numbers:
        GrosseryListController.getInstance().multiplyByNbPeople("grosseryList"+choice, numberPeople);
        shoppingList.setText(GrosseryListController.getInstance().getGrosseryListById("grosseryList"+choice).toString());

        //set meal picture :
        String[] listMealId = {
                "LundiR1",
                "LundiR2",
                "MardiR1",
                "MardiR2",
                "MercrediR1",
                "MercrediR2",
                "JeudiR1",
                "JeudiR2",
                "VendrediR1",
                "VendrediR2",
                "SamediR1",
                "SamediR2",
                "DimancheR1",
                "DimancheR2",
        };

        Planning p = PlanningController.getInstance().getPlanningById("planning"+choice, this);
        this.mealsButtonList = new ArrayList<>();

        int i =0;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;
        displayMealPicture(p, listMealId[i], i);i++;


        ArrayList<Button> listeButton = new ArrayList<>();
        Button r1 =  (Button) findViewById(R.id.LundiR1);
        Button r2 =  (Button) findViewById(R.id.LundiR2);
        Button r3 =  (Button) findViewById(R.id.MardiR1);
        Button r4 =  (Button) findViewById(R.id.MardiR2);
        Button r5 =  (Button) findViewById(R.id.MercrediR1);
        Button r6 =  (Button) findViewById(R.id.MercrediR2);
        Button r7 =  (Button) findViewById(R.id.JeudiR1);
        Button r8 =  (Button) findViewById(R.id.JeudiR2);
        Button r9 =  (Button) findViewById(R.id.VendrediR1);
        Button r10 =  (Button) findViewById(R.id.VendrediR2);
        Button r11 =  (Button) findViewById(R.id.SamediR1);
        Button r12 =  (Button) findViewById(R.id.SamediR2);
        Button r13 =  (Button) findViewById(R.id.DimancheR1);
        Button r14 =  (Button) findViewById(R.id.DimancheR2);

        listeButton.add(r1);
        listeButton.add(r2);
        listeButton.add(r3);
        listeButton.add(r4);
        listeButton.add(r5);
        listeButton.add(r6);
        listeButton.add(r7);
        listeButton.add(r8);
        listeButton.add(r9);
        listeButton.add(r10);
        listeButton.add(r11);
        listeButton.add(r12);
        listeButton.add(r13);
        listeButton.add(r14);

        for (int j = 0; j<14; j++){
            String repas = null;
            if (j == 0){
                repas = "LundiR1";
            }else if (j == 1){
                repas = "LundiR2";
            }else if (j == 2){
                repas = "MardiR1";
            }else if (j == 3){
                repas = "MardiR2";
            }else if (j == 4){
                repas = "MercrediR1";
            }else if (j == 5){
                repas = "MercrediR2";
            }else if (j == 6){
                repas = "JeudiR1";
            }else if (j == 7){
                repas = "JeudiR2";
            }else if (j == 8){
                repas = "VendrediR1";
            }else if (j == 9){
                repas = "VendrediR2";
            }else if (j == 10){
                repas = "SamediR1";
            }else if (j == 11){
                repas = "SamediR2";
            }else if (j == 12){
                repas = "DimancheR1";
            }else if (j == 13){
                repas = "DimancheR2";
            }
            String finalRepas = repas;
            listeButton.get(j).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    planningLayout.setVisibility(View.GONE);
                    TextView plat = (TextView) findViewById(R.id.p1recette);
                    plat.setText(p.getMealMenu(finalRepas).get(0).getTitle());
                    ImageView image = (ImageView) findViewById(R.id.imageRecette1);
                    displayMealPictureImage(p,finalRepas,image);
                    TextView ingredients = (TextView) findViewById(R.id.p1ingrédients);
                    String ingr ="";
                    HashMap<String, Integer> in = p.getMealMenu(finalRepas).get(0).getIngredients();
                    for(String keyi : in.keySet()){
                        int idi = in.get(keyi);
                        String quantity= keyi.split("--")[0];
                        ingr = ingr +"\n"+ quantity +" " + IngredientController.getInstance().getIngredientByID(idi).getQuantity() + " "  + IngredientController.getInstance().getIngredientByID(idi).toString();

                    }
                    ingredients.setText( ingr);
                    TextView  recette = (TextView) findViewById(R.id.p1platrecette);
                    recette.setText(p.getMealMenu(finalRepas).get(0).getDirection().toString());
                    choice1Recette1.setVisibility(View.VISIBLE);
                }
            });


           Button backchoice1Recette1 = (Button) findViewById(R.id.backchoice1Recette1);



            //pour chaque recette
            backchoice1Recette1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    planningLayout.setVisibility(View.VISIBLE);
                    choice1Recette1.setVisibility(View.GONE);
                }
            });
        }




    }

    private void displayMealPicture(Planning p, String mealID, int index) {
        Recipe r = p.getMealMenu(mealID).get(0);
        String mealRessId = "meal"+String.valueOf(r.getId()+1);
//        int viewID = R.id.LundiR1;
        int viewID = this.getResources().getIdentifier(mealID, "id", this.getPackageName());
        this.mealsButtonList.add(findViewById(viewID));
//        this.mealsButtonList.get(index).setText(mealRessId);
        this.mealsButtonList.get(index).setText(p.getMealMenu(mealID).get(0).getTitle());

        int ressID = this.getResources().getIdentifier(mealRessId, "drawable", this.getPackageName());
//        Drawable d = this.getResources().getDrawable();
        Drawable d = ContextCompat.getDrawable(this, ressID);
        this.mealsButtonList.get(index).setBackground(d);

    }


    private void displayMealPictureImage(Planning p, String mealID,ImageView b) {
        Recipe r = p.getMealMenu(mealID).get(0);
        String mealRessId = "meal"+String.valueOf(r.getId()+1);
        int ressID = this.getResources().getIdentifier(mealRessId, "drawable", this.getPackageName());
        Drawable d = ContextCompat.getDrawable(this, ressID);
        b.setBackground(d);
    }
}