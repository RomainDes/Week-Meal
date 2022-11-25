package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.weekmeal.entity.Diet;

import java.util.ArrayList;
import java.util.List;

public class MakeAChoice extends AppCompatActivity {

    private Button buttonReturn;
    private Button buttonChoose;


    /*private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private List<Image> mViewImage;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_achoice);

        buttonReturn = (Button) findViewById(R.id.choiceReturn);
        buttonChoose = (Button) findViewById(R.id.choiceChoose);

        Bundle extras = getIntent().getExtras();

        ArrayList<Diet> dietsTrue = new ArrayList<>();
        dietsTrue = (ArrayList<Diet>) getIntent().getSerializableExtra("dietsTrue");
        int mealPerDay = Integer.parseInt(extras.getString("mealPerDay"));
        int numberPeople = Integer.parseInt(extras.getString ("numberPeople"));
        for (int i = 0 ; i< dietsTrue.size();i++){
            Log.i("Diet : ",dietsTrue.get(i).getId()+" || "+dietsTrue.get(i).getTitle() +"");
        }

        Log.i("taille :  ",dietsTrue.size()+ "");
        Log.i("meal :  ",mealPerDay +  "");
        Log.i("persone :  ",numberPeople+ "");


    buttonReturn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            Intent intentReturn = new Intent(MakeAChoice.this, MainActivity.class);
            startActivity(intentReturn);
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