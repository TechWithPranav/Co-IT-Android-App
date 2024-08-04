package com.thirtyseventyc.gpian20;


public class messageCardModel {

    protected String username;
    protected String message;
    protected String time;

    public messageCardModel(){}
    public messageCardModel(String username, String message,String time) {
        this.username = username;
        this.message = message;
        this.time=time;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

}
