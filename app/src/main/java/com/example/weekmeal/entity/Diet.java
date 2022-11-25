package com.example.weekmeal.entity;

import com.google.gson.internal.LinkedTreeMap;

import java.io.Serializable;

public class Diet implements Serializable {
    private Integer id;
    private String title;

    public Diet(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Diet(){

    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    //ToString():
    public String toString(){
        String str = "";
        str = str.concat(this.getId()+": "+this.getTitle()+"\n");
        return str;
    }

    //Converter from LinkedTreeMap to Diet:
    public static Diet convertLTM(LinkedTreeMap dietLTM){
        return new Diet(new Integer(((Double) dietLTM.get("id")).intValue()), (String) dietLTM.get("title"));
    }



}
