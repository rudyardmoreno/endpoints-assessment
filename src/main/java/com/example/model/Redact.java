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
        result=getOriginal();
        try {
            for (String badWord : getBadWords()) {
                result = result.replaceAll(badWord, String.format("%" + badWord.length() + "s", " ").replace(' ', '*'));
            }
        } catch (Exception e) {
            //Nothing
        }
        return result;
    }

    //Constructor
    public Redact(){
        setOriginal("");
    }
}
