package com.example.model;

/**
 * Created by Rudyard Moreno on 04/02/2017
 * DISH NETWORK - Galvanize Training
 * CNE-002 (Dish)
 * Unit 3 - Request and Responses
 * Model - Camelize Class
 */

public class Camelize {
    //Properties
    private String original;
    private boolean initialCap;

    //Getters
    public String getOriginal(){
        return original;
    }
    public boolean isInitialCap(){
        return initialCap;
    }

    //Setters
    public void setOriginal(String value){
        original=value;
    }
    public void setInitialCap(boolean value){
        initialCap=value;
    }
    public void setInitialCap(String value){
        if (value.toUpperCase()=="TRUE"){
            setInitialCap(true);
        } else {
            setInitialCap(false);
        }
    }

    //Method
    public String getCamelize(){
        String result="";
        int count=0;
        try {
            String[] originalParts = original.split("_");
            for (String part:originalParts) {
                //if (count!=0) result+=" ";
                if ((count==0 && initialCap) || (count!=0)) part=part.substring(0,1).toUpperCase() + part.substring(1);
                result+=part;
                count+=1;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    //Constructor
    Camelize(){
        setOriginal("");
        setInitialCap(false);
    }
}
