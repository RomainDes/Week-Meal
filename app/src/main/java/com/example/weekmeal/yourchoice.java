package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.weekmeal.controler.GrosseryListController;
import com.example.weekmeal.controler.IngredientController;
import com.example.weekmeal.controler.PlanningController;
import com.example.weekmeal.controler.RecipeController;
import com.example.weekmeal.entity.Planning;
import com.example.weekmeal.entity.Recipe;

import java.util.ArrayList;
import java.util.Date;
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
}