package com.example.weekmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.weekmeal.controler.DietController;
import com.example.weekmeal.controler.RecipeController;
import com.example.weekmeal.entity.Diet;
import com.example.weekmeal.entity.Recipe;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DietController.getInstance().readDiet(this);


//        Recipe r = new Recipe(
//                0,
//                "Poulet rôti miel & moutarde",
//                "Étape 1 : Préchauffez le four à 200°C. Épluchez puis coupez les carottes en deux puis en lamelles. \n" +
//                        "Étape 2 : Dans un petit bol, mélangez le miel avec la moutarde. \n" +
//                        "Étape 3 : Versez un filet d'huile d'olive, et râpez l'ail. Salez et poivrez, puis mélangez à nouveau. \n" +
//                        "Étape 4 : Versez de l'huile d'olive dans le fond d'un plat allant au four. Ajoutez les cuisses de poulet.\n" +
//                        "Étape 5 : Badigeonnez les cuisses de poulet avec le mélange miel/moutarde. \n" +
//                        "Étape 6 : Ajoutez les carottes émincées, et versez un filet d'huile d'olive. Parsemez le tout de thym.\n" +
//                        "Étape 7 : Salez et poivrez. Versez un petit peu d'eau (5cl par portion) sur le mélange et enfournez pendant 55 minutes à 200°C. \n" +
//                        "Étape 8 : Sortez du four, servez, c'est prêt !\n",
//                new ArrayList<>(),
//                new ArrayList<>({DietController.getInstance().getDiet(int i)})
//                );
//        RecipeController.getInstance().addRecipe(, this);

//        RecipeController.getInstance().addRecipe();

        DietController.getInstance().synchronizedWithDB(this);
        Diet d = DietController.getInstance().getDietByID(this,1);

        TextView txt = (TextView) findViewById(R.id.txt);
        txt.setText(d.toString());
    }
}