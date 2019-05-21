package com.passionatesolutions.app.jsonrecyclerview.model;

public class School {


    private String dbn;
    private String city;
    private String name;

    public School(){}

    // Returns School data of Dbn (school id), city of school, and name of school

    public School(String dbn, String city, String name) {
        this.dbn = dbn;
        this.city = city;
        this.name = name;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
