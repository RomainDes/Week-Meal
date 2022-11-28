package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MakeAChoice extends AppCompatActivity {

    /*private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;

    private List<Image> mViewImage;*/
    private Button buttonChoose1;
    private Button buttonChoose2;
    private Button buttonReturn1;
    private Button buttonReturn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_achoice);

        /*LinearLayoutManager layoutManager
                = new LinearLayoutManager(MakeAChoice.this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myList = (RecyclerView) findViewById(R.id.recyclerview);
        myList.setLayoutManager(layoutManager);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, );
        recyclerView.setAdapter(adapter);*/
        buttonReturn1 = (Button) findViewById(R.id.return1);
        buttonReturn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MakeAChoice.this, MainActivity.class);
                startActivity(intent);

            }
        });

        buttonReturn2 = (Button) findViewById(R.id.return2);
        buttonReturn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MakeAChoice.this, MainActivity.class);
                startActivity(intent);

            }
        });

        buttonChoose1 = (Button) findViewById(R.id.choose1);
        buttonChoose1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MakeAChoice.this, yourchoice.class);
                startActivity(intent);

            }
        });

        buttonChoose2 = (Button) findViewById(R.id.choose2);
        buttonChoose2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MakeAChoice.this, yourchoice.class);
                startActivity(intent);

            }
        });



    }
}