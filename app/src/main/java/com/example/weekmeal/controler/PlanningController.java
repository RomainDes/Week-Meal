package com.example.weekmeal.controler;

import android.app.Activity;

import com.example.weekmeal.entity.Planning;
import com.example.weekmeal.tools.JSONTool;

import java.util.ArrayList;
import java.util.List;

public class PlanningController {

    private List<Planning> planningList;

    public List<Planning> getPlanningList() {
        return planningList;
    }

    public PlanningController(){
        this.planningList = new ArrayList<>();
    }

    public void addPlannings(Planning planning, Activity activity){
        JSONTool.getInstance().writeJSON(activity, planning, "planning");
    }

    //Singleton:
    private static PlanningController instance;

    public static PlanningController getInstance() {
        if(instance == null)
            instance = new PlanningController();
        return instance;
    }
}
