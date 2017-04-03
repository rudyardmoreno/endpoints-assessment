package com.example.model;

/**
 * Created by Rudyard Moreno on 04/02/2017
 * DISH NETWORK - Galvanize Training
 * CNE-002 (Dish)
 * Unit 3 - Request and Responses
 * Model - Encode Class
 */

public class Encode {
    //Properties
    private String message;
    private String key;
    private static final String ALPHABET="abcdefghijklmnopqrstuvwzyzABCDEFGHIJKLMNOPQRSTUVWZYZ";

    //Getters
    public String getMessage(){
        return message;
    }
    public String getKey(){
        return key;
    }

    //Setters
    public void setMessage(String value){
        message=value;
    }
    public void setKey(String value){
        key=value + value.toUpperCase();
    }

    //Method
    public String getEncode(){
        String result="";
        int count=0;
        int index=0;
        try {
            if ( ALPHABET.length() != key.length() ) {
                result = String.format("Invalid key (%s). It does not have same length than English alphabet (%s)", key.toString(), ALPHABET.toString());
            } else {
                for (int i=0 ; i<message.length() ; i++) {
                    index=ALPHABET.indexOf(message.charAt(i));
                    result+= (index>=0) ? key.charAt(index) : message.charAt(i);
                }
            }
        } catch (Exception e) {
            result = "";
        }
        return result;
    }

    //Constructor
    public Encode(){
        setMessage("");
        setKey("");
    }
}
