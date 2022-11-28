package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class yourchoice2 extends AppCompatActivity {


    private Button buttonPlanning;
    private Button buttonReturn1;
    private Button buttonReturn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourchoice2);

        /*buttonPlanning = (Button) findViewById(R.id.shopping);
        buttonPlanning.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(yourchoice2.this, yourchoice.class);
                startActivity(intent);

            }
        });

        buttonReturn1 = (Button) findViewById(R.id.buttonReturn1);
        buttonReturn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(yourchoice2.this, MakeAChoice.class);
                startActivity(intent);

            }
        });

        buttonReturn2 = (Button) findViewById(R.id.buttonReturn2);
        buttonReturn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(yourchoice2.this, MakeAChoice.class);
                startActivity(intent);

            }
        });*/
    }
}