package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class yourchoice extends AppCompatActivity {


    private Button buttonShopping;
    private Button buttonReturn1;
    private Button buttonReturn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourchoice);


        buttonShopping = (Button) findViewById(R.id.shopping);
        buttonShopping.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(yourchoice.this, yourchoice2.class);
                startActivity(intent);

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