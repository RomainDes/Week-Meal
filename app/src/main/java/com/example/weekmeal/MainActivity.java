package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.weekmeal.controler.DietController;


public class MainActivity extends AppCompatActivity {
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DietController.getInstance().readDiet(this);

    }
        //récupérer tous les bouttons lors du lancement de l'application
        buttonMoreMeal = (Button) findViewById(R.id.buttonMoreMeal);
        buttonLessMeal = (Button) findViewById(R.id.buttonLessMeal);
        buttonMorePeople = (Button) findViewById(R.id.buttonMorePeople);
        buttonLessPeople = (Button) findViewById(R.id.buttonLessPeople);
        buttonGenerate = (Button) findViewById(R.id.buttonGenerate);

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
                startActivity(intent);

            }
        });
    }



}