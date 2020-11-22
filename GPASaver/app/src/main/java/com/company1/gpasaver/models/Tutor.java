package com.company1.gpasaver.models;

import java.util.ArrayList;

public class Tutor {

    private String name;
    private String id;
    private String phone;
    private String email;


    public Tutor(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Tutor(String name, String id, String phone, String email) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private ArrayList<Availability> availabilities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
