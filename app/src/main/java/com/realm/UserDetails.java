package com.realm;

public class UserDetails {
    // private constructor
    public UserDetails() {

    }

    public static UserDetails getInstance() {
        if (instance == null){
            instance = new UserDetails();
        }
        return instance;
    }

    private static UserDetails instance = null;

    public String username = "";
    public String password = "";
    public String chatWith = "";
    public String avatar = "";
}
