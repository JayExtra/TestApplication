package com.example.testapplication.api.model.service.model2;

/**
 * Created by JayExtra on 4/20/19
 */

public class User {

    private Integer id;

     private String name, email;
     private int age;
     private String[] topics;

    public User(String name, String email, int age, String[] topics) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.topics = topics;
    }

    public Integer getId() {
        return id;
    }
}
