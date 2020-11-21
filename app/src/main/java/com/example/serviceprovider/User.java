package com.example.serviceprovider;


// this file is used for data fetching
public class User {
    // private String UserID;
    private String userName;
    // private String UserEmail;
    // private String Userpassword;
    private String userphone;
    private String userfield;

    public User(){

    }

    public User(String userName, String userphone , String userfield) {
        this.userName = userName;
        this.userphone = userphone;
        this.userfield = userfield;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUserfield() {
        return userfield;
    }

    public void setUserfield(String userfield) {

        this.userfield = userfield;
    }
}


