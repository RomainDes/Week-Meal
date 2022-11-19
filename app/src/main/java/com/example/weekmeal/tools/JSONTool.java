package com.example.weekmeal.tools;

import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class JSONTool {

    public JSONTool(){

    }

    public void writeJSON(Activity activity, Object o, String type){
        Gson gson = new Gson();
        String objectJSON = gson.toJson(o);
        //Write file :
        File DataBaseDir = new File(activity.getFilesDir(), "DataBase");
        if (!DataBaseDir.exists()) {
            DataBaseDir.mkdir();
        }
        try {
            File JSONFile = new File(DataBaseDir, type.concat("List.json"));
            FileWriter writer = new FileWriter(JSONFile);
            writer.append(objectJSON);
            writer.flush();
            writer.close();
            Toast.makeText(activity, "Synchronized with DB", Toast.LENGTH_LONG).show();
        } catch (Exception e) { }
    }

    public Object loadJSONFromAsset(Activity activity, Object o, String type){
        String json = null;
        Gson gson = new Gson();
        //read file
        File DataBaseDir = new File(activity.getFilesDir(), "DataBase");
        File JSONFile = new File(DataBaseDir, type.concat("List.json"));

        try {
            FileInputStream inputStream = new FileInputStream(JSONFile);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            json = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(json, o.getClass());
    }

    //Singleton :
    private static JSONTool instance;

    public static JSONTool getInstance() {
        if(instance == null)
            instance = new JSONTool();
        return instance;
    }
}
