package com.endava.bookARoom.models;


import lombok.Data;

@Data //lombok will handle everything for us: hashcode and equals, getters and setters, toString...
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
