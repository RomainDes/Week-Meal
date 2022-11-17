package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.weekmeal.connector.FirebaseConnector;
import com.example.weekmeal.entity.Diet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void AddingDefaultDiet(){
                FirebaseConnector fc = FirebaseConnector.instance();
                fc.addDiet(new Diet(0, "Vegetarian"), this);
                fc.addDiet(new Diet(1, "Vegan"), this);
                fc.addDiet(new Diet(2, "Gluten Free"), this);
    }
}