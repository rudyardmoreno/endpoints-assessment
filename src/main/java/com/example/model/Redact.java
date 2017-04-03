package com.example.model;

import java.util.List;

/**
 * Created by Rudyard Moreno on 04/02/2017
 * DISH NETWORK - Galvanize Training
 * CNE-002 (Dish)
 * Unit 3 - Request and Responses
 * Model - Redact Class
 */

public class Redact {
    //Properties
    private String original;
    private List<String> badWords;

    //Getters
    public String getOriginal(){
        return original;
    }
    public List<String> getBadWords(){
        return badWords;
    }

    //Setters
    public void setOriginal(String value){
        original=value;
    }
    public void setBadWords(List<String> value){
        badWords=value;
    }

    //Method
    public String getRedact(){
        String result="";
        int count=0;
        try {
            result=original;
            for (String badWord : badWords) {
                result=result.replaceAll(badWord, String.format("%" + badWord.length() + "s"," ").replace(' ','*'));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    //Constructor
    public Redact(){
        setOriginal("");
    }
}
