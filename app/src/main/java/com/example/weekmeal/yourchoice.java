package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class yourchoice extends AppCompatActivity {


    private Button buttonShopping;
    private Button buttonPlanning;
    private Button buttonReturn1;
    private Button buttonReturn2;

    private LinearLayout planningLayout;
    private LinearLayout shoppingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourchoice);

        planningLayout = (LinearLayout) findViewById(R.id.planningLayout);
        shoppingLayout = (LinearLayout) findViewById(R.id.shoppingLayout);


        buttonPlanning = (Button) findViewById(R.id.planning);
        buttonPlanning.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                planningLayout.setVisibility(View.VISIBLE);
                shoppingLayout.setVisibility(View.GONE);

            }
        });

        buttonShopping = (Button) findViewById(R.id.shopping);
        buttonShopping.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                planningLayout.setVisibility(View.GONE);
                shoppingLayout.setVisibility(View.VISIBLE);

            }
        });

        buttonReturn1 = (Button) findViewById(R.id.return1);
        buttonReturn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(yourchoice.this, MakeAChoice.class);
                startActivity(intent);

            }
        });

        buttonReturn2 = (Button) findViewById(R.id.return2);
        buttonReturn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(yourchoice.this, MakeAChoice.class);
                startActivity(intent);

            }
        });
    }
}