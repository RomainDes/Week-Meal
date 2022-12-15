package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import java.util.List;

public class MakeAChoice extends AppCompatActivity {

    /*private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;

    private List<Image> mViewImage;*/
    private Button buttonChoose1;
    private Button buttonChoose2;
    private Button buttonReturn1;
    private Button buttonReturn2;

    private RadioButton choice1;
    private RadioButton choice2;
    private RadioButton choice3;

    private LinearLayout makeachoice;

    private LinearLayout choice1Planning;
    private LinearLayout choice2Planning;
    private LinearLayout choice3Planning;

    private Button back1;
    private Button back2;
    private Button back3;


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

        makeachoice = (LinearLayout) findViewById(R.id.makeachoice);

        choice1Planning = (LinearLayout) findViewById(R.id.choice1Planning);
        choice1 = (RadioButton) findViewById(R.id.choice1);
        choice1.setOnClickListener(new DoubleClickListener() {

            @Override
 				public void onDoubleClick() {
                    makeachoice.setVisibility(View.GONE);
                    choice1Planning.setVisibility(View.VISIBLE);
 				}
        });

        back1 = (Button) findViewById(R.id.back1);
        back1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                makeachoice.setVisibility(View.VISIBLE);
                choice1Planning.setVisibility(View.GONE);

            }
        });

        choice2Planning = (LinearLayout) findViewById(R.id.choice2Planning);
        choice2 = (RadioButton) findViewById(R.id.choice2);
        choice2.setOnClickListener(new DoubleClickListener() {

            @Override
            public void onDoubleClick() {
                makeachoice.setVisibility(View.GONE);
                choice2Planning.setVisibility(View.VISIBLE);
            }
        });

        back2 = (Button) findViewById(R.id.back2);
        back2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                makeachoice.setVisibility(View.VISIBLE);
                choice2Planning.setVisibility(View.GONE);

            }
        });

        choice3Planning = (LinearLayout) findViewById(R.id.choice3Planning);
        choice3 = (RadioButton) findViewById(R.id.choice3);
        choice3.setOnClickListener(new DoubleClickListener() {

            @Override
            public void onDoubleClick() {
                makeachoice.setVisibility(View.GONE);
                choice3Planning.setVisibility(View.VISIBLE);
            }
        });

        back3 = (Button) findViewById(R.id.back3);
        back3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                makeachoice.setVisibility(View.VISIBLE);
                choice3Planning.setVisibility(View.GONE);
            }
        });



    }
}