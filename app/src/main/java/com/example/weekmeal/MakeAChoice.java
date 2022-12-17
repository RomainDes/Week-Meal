package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.weekmeal.entity.Diet;

import java.util.ArrayList;

public class MakeAChoice extends AppCompatActivity {

    private Button buttonReturn;
    private Button buttonChoose;
    private Button buttonChoose1;
    private Button buttonReturn1;
    private TextView name;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private LinearLayout linearLayout1;

    private  ArrayList<Boolean> toggleList = new ArrayList<>();
    private int choice;
    /*private RecyclerView recyclerView;
        private MyRecyclerViewAdapter adapter;
        private List<Image> mViewImage;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_achoice);

        name = (TextView) findViewById(R.id.make_choice_makeachoice);
        buttonReturn = (Button) findViewById(R.id.return2);
        buttonChoose = (Button) findViewById(R.id.choose1);
        // partie de Romain avec les boutons
        buttonChoose1 = (Button) findViewById(R.id.choose2);
        buttonReturn1 = (Button) findViewById(R.id.return1);
        radioGroup = (RadioGroup) findViewById(R.id.make_choice_radio);
        radioButton1 = (RadioButton) findViewById(R.id.choice1);
        radioButton2 = (RadioButton) findViewById(R.id.choice2);
        radioButton3 = (RadioButton) findViewById(R.id.choice3);
//        linearLayout1 = (LinearLayout) findViewById(R.id.make_choice_linear1);





        Bundle extras = getIntent().getExtras();
        ArrayList<Diet> dietsTrue = new ArrayList<>();
        dietsTrue = (ArrayList<Diet>) getIntent().getSerializableExtra("dietsTrue");

        toggleList = (ArrayList<Boolean>) getIntent().getSerializableExtra("toggleList");
        int mealPerDay = Integer.parseInt(extras.getString("mealPerDay"));
        int meals = mealPerDay*7;
        /*for (int i=1; i<= meals;i++){
            //ConstraintLayout constraint1 = new ConstraintLayout(getApplicationContext());
            LinearLayout.LayoutParams linearLayoutparam1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            Button button1 = new Button(getApplicationContext());
            button1.setText("choice 1 : "+i);
            button1.setId(View.generateViewId());
            linearLayout1.addView(button1,linearLayoutparam1);

            //constraint1.addView(button1);
            //linearLayout1.addView(constraint1);
        }*/

        int numberPeople = Integer.parseInt(extras.getString ("numberPeople"));


        String planningName = extras.getString("planningName");
        name.setText("MAKE A CHOICE FOR "+ planningName);

        if(extras.getString ("choice") != null){
            choice =Integer.parseInt(extras.getString ("choice"));
            if (choice == 1){
                radioButton1.setChecked(true);
            }else if (choice ==2){
                radioButton2.setChecked(true);
            }else if (choice ==3){
                radioButton3.setChecked(true);
            }
        }

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (radioButton1.isChecked() == false && radioButton2.isChecked() == false && radioButton3.isChecked() == false ){

                }else{
                    if(radioButton1.isChecked()){
                        choice= 1;
                    }else if(radioButton2.isChecked()){
                        choice =2;
                    }else if (radioButton3.isChecked()){
                        choice =3;
                    }
                    Intent intent = new Intent(MakeAChoice.this, yourchoice.class);
                    intent.putExtra("toggleList",toggleList);
                    intent.putExtra("mealPerDay",mealPerDay +"");
                    intent.putExtra("numberPeople", numberPeople+"");
                    intent.putExtra("planningName", planningName);
                    intent.putExtra("choice",choice+"");
                    startActivity(intent);
                   }
            }
        });

    buttonReturn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(MakeAChoice.this, MainActivity.class);
            intent.putExtra("toggleList",toggleList);
            intent.putExtra("mealPerDay",mealPerDay +"");
            intent.putExtra("numberPeople", numberPeople+"");
            intent.putExtra("planningName", planningName);
            startActivity(intent);
        }
    });

    // partie de Romain avec les boutons
        buttonChoose1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (radioButton1.isChecked() == false && radioButton2.isChecked() == false && radioButton3.isChecked() == false ){

                }else{
                    if(radioButton1.isChecked()){
                        choice= 1;
                    }else if(radioButton2.isChecked()){
                        choice =2;
                    }else if (radioButton3.isChecked()){
                        choice =3;
                    }
                    Intent intent = new Intent(MakeAChoice.this, yourchoice.class);
                    intent.putExtra("toggleList",toggleList);
                    intent.putExtra("mealPerDay",mealPerDay +"");
                    intent.putExtra("numberPeople", numberPeople+"");
                    intent.putExtra("planningName", planningName);
                    intent.putExtra("choice",choice+"");
                    startActivity(intent);
                }

            }
        });

        buttonReturn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MakeAChoice.this, MainActivity.class);
                intent.putExtra("toggleList",toggleList);
                intent.putExtra("mealPerDay",mealPerDay +"");
                intent.putExtra("numberPeople", numberPeople+"");
                intent.putExtra("planningName", planningName);
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