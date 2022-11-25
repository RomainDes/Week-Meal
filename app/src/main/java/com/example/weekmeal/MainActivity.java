package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.weekmeal.controler.DietController;
import com.example.weekmeal.entity.Diet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private int numberPeople = 0;
    private int mealPerDay = 0;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
//récupérer tous les bouttons lors du lancement de l'application
        buttonMoreMeal = (Button) findViewById(R.id.buttonMoreMeal);
        buttonLessMeal = (Button) findViewById(R.id.buttonLessMeal);
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





        //récupérer tous les textes lors du lancement de l'application
        mealPerDayText = (TextView) findViewById(R.id.mealPerDay);
        numberPeopleText = (TextView) findViewById(R.id.numberOfPeople);

        buttonMoreMeal.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                mealPerDay ++;
                mealPerDayText.setText(String.valueOf(mealPerDay));
            }
        });
        buttonLessMeal.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if(mealPerDay > 0){
                    mealPerDay--;
                    mealPerDayText.setText(String.valueOf(mealPerDay));
                }

            }
        });
        buttonMorePeople.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){
                numberPeople++;
                numberPeopleText.setText(String.valueOf(numberPeople));
            }
        });
        buttonLessPeople.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if(numberPeople > 0){
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
                DietController.getInstance().getDietTrue(dietsTrue,togglecucumber.isChecked(),togglecucumber.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglecarrots.isChecked(),togglecarrots.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglelettuce.isChecked(),togglelettuce.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglespinach.isChecked(),togglespinach.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglegarlic.isChecked(),togglegarlic.getText().toString());
                DietController.getInstance().getDietTrue(dietsTrue,togglefish.isChecked(),togglefish.getText().toString());




                Log.i("taille :  ",dietsTrue.size()+ "");
                for (int i = 0 ; i< dietsTrue.size();i++){
                    Log.i("Diet : ",dietsTrue.get(i).getId()+" || "+dietsTrue.get(i).getTitle() +"");
                    }
                startActivity(intent);
            }
        });
    }


}




