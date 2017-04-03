package com.example.model;

/**
 * Created by Rudyard Moreno on 04/02/2017
 * DISH NETWORK - Galvanize Training
 * CNE-002 (Dish)
 * Unit 3 - Request and Responses
 * Model - Sed Class
 */

public class Sed {
    //Properties
    private String message;
    private String find;
    private String replace;

    //Getters
    public String getMessage() {return message; }
    public String getFind(){
        return find;
    }
    public String getReplace(){
        return replace;
    }

    //Setters
    public void setMessage(String value) { message=value; }
    public void setFind(String value){
        find=value;
    }
    public void setReplace(String value){
        replace=value;
    }

    //Method
    public String getSed(){
        String result=message;
        try {
            if (find.length()!=0) {
                result=result.replaceAll(find,replace);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    //Constructor
    public Sed(){
        setMessage("");
        setFind("");
        setReplace("");
    }
}
