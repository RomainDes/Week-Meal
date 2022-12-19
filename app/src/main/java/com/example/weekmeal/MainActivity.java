package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.weekmeal.controler.DietController;
import com.example.weekmeal.controler.IngredientController;
import com.example.weekmeal.controler.RecipeController;
import com.example.weekmeal.entity.Diet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private int numberPeople = 0;


    private EditText planningName;

    private TextView mealPerDayText;
    private TextView numberPeopleText;

    private Button buttonMoreMeal;
    private Button buttonLessMeal;
    private Button buttonMorePeople;
    private Button buttonLessPeople;
    private Button buttonGenerate;
    private ToggleButton togglepasta;
    private ToggleButton togglerice;
    private ToggleButton togglehealthy;
    private ToggleButton togglesteak;
    private ToggleButton togglechicken;
    private ToggleButton togglepotaotes;
    private ToggleButton toggleoven;
    private ToggleButton toggleegg;
    private ToggleButton togglevege;
    private ToggleButton togglebigeater;
    private ToggleButton togglemediumeater;
    private ToggleButton togglelittleeater;
    private ToggleButton togglecheese;
    private ToggleButton toggletomatoes;
    private ToggleButton togglepepper;
    private ToggleButton toggleonion;
    private ToggleButton togglecucumber;
    private ToggleButton togglecarrots;
    private ToggleButton togglelettuce;
    private ToggleButton togglespinach;
    private ToggleButton togglegarlic;
    private ToggleButton togglefish;
    private  ArrayList<Boolean> toggleList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        RecipeController.getInstance().synchronizedWithDB(this);
        RecipeController.getInstance().readLocalDB(this);
        DietController.getInstance().synchronizedWithDB(this);
        DietController.getInstance().readLocalDB(this);
        IngredientController.getInstance().synchronizedWithDB(this);
        IngredientController.getInstance().readLocalDB(this);
//récupérer tous les bouttons lors du lancement de l'application
        //buttonMoreMeal = (Button) findViewById(R.id.buttonMoreMeal);
        //buttonLessMeal = (Button) findViewById(R.id.buttonLessMeal);
        buttonMorePeople = (Button) findViewById(R.id.buttonMorePeople);
        buttonLessPeople = (Button) findViewById(R.id.buttonLessPeople);
        buttonGenerate = (Button) findViewById(R.id.buttonGenerate);

        togglepasta = (ToggleButton) findViewById(R.id.pasta);
        togglerice = (ToggleButton) findViewById(R.id.rice);
        togglehealthy = (ToggleButton) findViewById(R.id.healthy);
        togglesteak = (ToggleButton) findViewById(R.id.steak);
        togglechicken = (ToggleButton) findViewById(R.id.chicken);
        togglepotaotes = (ToggleButton) findViewById(R.id.potatoes);
        toggleoven = (ToggleButton) findViewById(R.id.oven);
        toggleegg = (ToggleButton) findViewById(R.id.egg);
        togglevege = (ToggleButton) findViewById(R.id.vegetarian);
        togglebigeater = (ToggleButton) findViewById(R.id.bigEater);
        togglemediumeater = (ToggleButton) findViewById(R.id.mediumEater);
        togglelittleeater = (ToggleButton) findViewById(R.id.littleEater);
        togglecheese = (ToggleButton) findViewById(R.id.cheese);
        toggletomatoes = (ToggleButton) findViewById(R.id.tomatoes);
        togglepepper = (ToggleButton) findViewById(R.id.pepper);
        toggleonion = (ToggleButton) findViewById(R.id.onion);
        togglecucumber = (ToggleButton) findViewById(R.id.cucumber);
        togglecarrots = (ToggleButton) findViewById(R.id.carrots);
        togglelettuce = (ToggleButton) findViewById(R.id.lettuce);
        togglespinach = (ToggleButton) findViewById(R.id.spinach);
        togglegarlic = (ToggleButton) findViewById(R.id.garlic);
        togglefish = (ToggleButton) findViewById(R.id.fish);

        toggleList = (ArrayList<Boolean>) getIntent().getSerializableExtra("toggleList");
        if(toggleList != null ){
            togglepasta.setChecked(toggleList.get(0));
            togglerice.setChecked(toggleList.get(1));
            togglehealthy.setChecked(toggleList.get(2));
            togglesteak.setChecked(toggleList.get(3));
            togglechicken.setChecked(toggleList.get(4));
            togglepotaotes.setChecked(toggleList.get(5));
            toggleoven.setChecked(toggleList.get(6));
            toggleegg.setChecked(toggleList.get(7));
            togglevege.setChecked(toggleList.get(8));
            togglebigeater.setChecked(toggleList.get(9));
            togglemediumeater.setChecked(toggleList.get(10));
            togglelittleeater.setChecked(toggleList.get(11));
            togglecheese.setChecked(toggleList.get(12));
            toggletomatoes.setChecked(toggleList.get(13));
            togglepepper.setChecked(toggleList.get(14));
            toggleonion.setChecked(toggleList.get(15));
            togglecucumber.setChecked(toggleList.get(16));
            togglecarrots.setChecked(toggleList.get(17));
            togglelettuce.setChecked(toggleList.get(18));
            togglespinach.setChecked(toggleList.get(19));
            togglegarlic.setChecked(toggleList.get(20));
            togglefish.setChecked(toggleList.get(21));
        }

        //récupérer tous les textes lors du lancement de l'application
        //mealPerDayText = (TextView) findViewById(R.id.mealPerDay);
        numberPeopleText = (TextView) findViewById(R.id.numberOfPeople);

        planningName =  (EditText)  findViewById(R.id.planningName);
        String name = getIntent().getStringExtra("planningName");
        if(name != null){
            planningName.setText(name);
        }


        if(getIntent().getStringExtra("numberPeople") != null){
            numberPeopleText.setText(getIntent().getStringExtra("numberPeople"));
        }


        /*buttonMoreMeal.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                mealPerDay =  Integer.parseInt(mealPerDayText.getText().toString());
                mealPerDay ++;
                mealPerDayText.setText(String.valueOf(mealPerDay));
            }
        });
        buttonLessMeal.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if(mealPerDay > 1){
                    mealPerDay =  Integer.parseInt(mealPerDayText.getText().toString());
                    mealPerDay--;
                    mealPerDayText.setText(String.valueOf(mealPerDay));
                }

            }
        });*/
        buttonMorePeople.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){
                numberPeople =  Integer.parseInt(numberPeopleText.getText().toString());
                numberPeople++;
                numberPeopleText.setText(String.valueOf(numberPeople));
            }
        });
        buttonLessPeople.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if(numberPeople > 1){
                    numberPeople =  Integer.parseInt(numberPeopleText.getText().toString());
                    numberPeople--;
                    numberPeopleText.setText(String.valueOf(numberPeople));
                }

            }
        });

        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MakeAChoice.class);
                ArrayList<Diet> dietsTrue = new ArrayList<Diet>();

                
                DietController.getInstance().getDietTrue(dietsTrue,togglepasta.isChecked(),togglepasta.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglerice.isChecked(),togglerice.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglehealthy.isChecked(),togglehealthy.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglesteak.isChecked(),togglesteak.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglechicken.isChecked(),togglechicken.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglepotaotes.isChecked(),togglepotaotes.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,toggleoven.isChecked(),toggleoven.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,toggleegg.isChecked(),toggleegg.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglevege.isChecked(),togglevege.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglebigeater.isChecked(),togglebigeater.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglemediumeater.isChecked(),togglemediumeater.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglelittleeater.isChecked(),togglelittleeater.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglecheese.isChecked(),togglecheese.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,toggletomatoes.isChecked(),toggletomatoes.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglepepper.isChecked(),togglepepper.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,toggleonion.isChecked(),toggleonion.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglecucumber.isChecked(),togglecucumber.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglecarrots.isChecked(),togglecarrots.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglelettuce.isChecked(),togglelettuce.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglespinach.isChecked(),togglespinach.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglegarlic.isChecked(),togglegarlic.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglefish.isChecked(),togglefish.getText().toString());

               if (toggleList == null ){
                   ArrayList<Boolean> toggleList =  new ArrayList<>();
                   toggleList.add(togglepasta.isChecked());
                    toggleList.add(togglerice.isChecked());
                    toggleList.add(togglehealthy.isChecked());
                    toggleList.add(togglesteak.isChecked());
                    toggleList.add(togglechicken.isChecked());
                    toggleList.add(togglepotaotes.isChecked());
                    toggleList.add(toggleoven.isChecked());
                    toggleList.add(toggleegg.isChecked());
                    toggleList.add(togglevege.isChecked());
                    toggleList.add(togglebigeater.isChecked());
                    toggleList.add(togglemediumeater.isChecked());
                    toggleList.add(togglelittleeater.isChecked());
                    toggleList.add(togglecheese.isChecked());
                    toggleList.add(toggletomatoes.isChecked());
                    toggleList.add(togglepepper.isChecked());
                    toggleList.add(toggleonion.isChecked());
                    toggleList.add(togglecucumber.isChecked());
                    toggleList.add(togglecarrots.isChecked());
                    toggleList.add(togglelettuce.isChecked());
                    toggleList.add(togglespinach.isChecked());
                    toggleList.add(togglegarlic.isChecked());
                    toggleList.add(togglefish.isChecked());
                   intent.putExtra("toggleList",toggleList);
               }else{
                   toggleList = (ArrayList<Boolean>) getIntent().getSerializableExtra("toggleList");
                    toggleList.set(0,togglepasta.isChecked());
                    toggleList.set(1,togglerice.isChecked());
                    toggleList.set(2,togglehealthy.isChecked());
                    toggleList.set(3,togglesteak.isChecked());
                    toggleList.set(4,togglechicken.isChecked());
                    toggleList.set(5,togglepotaotes.isChecked());
                    toggleList.set(6,toggleoven.isChecked());
                    toggleList.set(7,toggleegg.isChecked());
                    toggleList.set(8,togglevege.isChecked());
                    toggleList.set(9,togglebigeater.isChecked());
                    toggleList.set(10,togglemediumeater.isChecked());
                    toggleList.set(11,togglelittleeater.isChecked());
                    toggleList.set(12, togglecheese.isChecked());
                    toggleList.set(13,toggletomatoes.isChecked());
                    toggleList.set(14, togglepepper.isChecked());
                    toggleList.set(15, toggleonion.isChecked());
                    toggleList.set(16,togglecucumber.isChecked());
                    toggleList.set(17,togglecarrots.isChecked());
                    toggleList.set(18, togglelettuce.isChecked());
                    toggleList.set(19,togglespinach.isChecked());
                    toggleList.set(20, togglegarlic.isChecked());
                    toggleList.set(21, togglefish.isChecked());
                   intent.putExtra("toggleList",toggleList);
                }

               if (planningName.getText().toString().equals("") ==false){
                   intent.putExtra("planningName", planningName.getText().toString());
               }else{
                   intent.putExtra("planningName", "planning");
               }

               /*Log.i("taille :  ",dietsTrue.size()+ "");
                for (int i = 0 ; i< dietsTrue.size();i++){
                    Log.i("Diet : ",dietsTrue.get(i).getId()+" || "+dietsTrue.get(i).getTitle() +"");
                    }*/

                //Log.i("taille liste toggle  :",toggleList.size()+ "");
                intent.putExtra("dietsTrue", dietsTrue);
                intent.putExtra("numberPeople", numberPeopleText.getText().toString());

                startActivity(intent);
            }
        });
    }


}




