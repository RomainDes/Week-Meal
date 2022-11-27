package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.weekmeal.entity.Diet;

import java.util.ArrayList;

public class MakeAChoice extends AppCompatActivity {

    private Button buttonReturn;
    private Button buttonChoose;

    private Button buttonChoose1;
    private Button buttonChoose2;

    private  ArrayList<Boolean> toggleList = new ArrayList<>();

    /*private RecyclerView recyclerView;
        private MyRecyclerViewAdapter adapter;
        private List<Image> mViewImage;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_achoice);

        buttonReturn = (Button) findViewById(R.id.choiceReturn);
        //buttonChoose = (Button) findViewById(R.id.choiceChoose);

        Bundle extras = getIntent().getExtras();
        ArrayList<Diet> dietsTrue = new ArrayList<>();
        dietsTrue = (ArrayList<Diet>) getIntent().getSerializableExtra("dietsTrue");
        toggleList = (ArrayList<Boolean>) getIntent().getSerializableExtra("toggleList");
        int mealPerDay = Integer.parseInt(extras.getString("mealPerDay"));
        int numberPeople = Integer.parseInt(extras.getString ("numberPeople"));



    buttonReturn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            Intent intentReturn = new Intent(MakeAChoice.this, MainActivity.class);
            intentReturn.putExtra("toggleList",toggleList);
            intentReturn.putExtra("mealPerDay",mealPerDay +"");
            intentReturn.putExtra("numberPeople", numberPeople+"");
            startActivity(intentReturn);
        }
    });

    // partie de Romain avec les boutons
        buttonChoose1 = (Button) findViewById(R.id.choiceChoose);
        buttonChoose2 = (Button) findViewById(R.id.choose2);

        buttonChoose1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MakeAChoice.this, yourchoice.class);
                startActivity(intent);

            }
        });

        buttonChoose2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MakeAChoice.this, yourchoice.class);
                startActivity(intent);

            }
        });


        /*LinearLayoutManager layoutManager
                = new LinearLayoutManager(MakeAChoice.this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myList = (RecyclerView) findViewById(R.id.recyclerview);
        myList.setLayoutManager(layoutManager);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, );
        recyclerView.setAdapter(adapter);*/
    }
}