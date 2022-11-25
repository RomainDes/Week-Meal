package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;

import com.example.weekmeal.entity.Diet;

import java.util.ArrayList;
import java.util.List;

public class MakeAChoice extends AppCompatActivity {

    /*private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;

    private List<Image> mViewImage;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_achoice);
        Bundle extras = getIntent().getExtras();

        //ArrayList<Diet> dietsTrue = new ArrayList<>();
        //dietsTrue = (ArrayList<Diet>) extras.get("dietsTrue");
        int mealPerDay = Integer.parseInt(extras.getString("mealPerDay"));
        int numberPeople = Integer.parseInt(extras.getString ("numberPeople"));


        //Log.i("taille :  ",dietsTrue.size()+ "");
        Log.i("meal :  ",mealPerDay +  "");
        Log.i("persone :  ",numberPeople+ "");




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