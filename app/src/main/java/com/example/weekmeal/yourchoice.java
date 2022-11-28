package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class yourchoice extends AppCompatActivity {

    private Button buttonReturn;
    private Button buttonReturn1;
    private Button buttonShare;
    private Button buttonShare1;
    private TextView yourChoice;


    private ArrayList<Boolean> toggleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourchoice);

        buttonReturn = (Button) findViewById(R.id.your_choice_buttonReturn);
        buttonReturn1 = (Button) findViewById(R.id.your_choice_buttonReturn1);

        //bouttons share à faire
        buttonShare = (Button) findViewById(R.id.your_choice_buttonShare);
        buttonShare1 = (Button) findViewById(R.id.your_choice_buttonShare1);
        yourChoice  = (TextView) findViewById(R.id.your_choice_textChoice);



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



    }
}