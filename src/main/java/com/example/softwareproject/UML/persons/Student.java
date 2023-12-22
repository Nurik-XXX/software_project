package com.example.softwareproject.UML.persons;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student implements PersonInUniversity{

    private String name;
    private String lastName;
    private int login;
    private String password;
    private String email;
    private int phoneNumber;
    private String gender;
    private int course;

    public Student(String name, String lastName,int login, String password, String email, int phoneNumber, int course, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.course = course;
    }

    public Student(int login, String name,String email,int phoneNumber){
        this.login = login;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public int getLogin(){
        return login;
    }
    public String getPassword(){
        return password;
    }
    public int getCourse(){
        return course;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getGender() {
        return gender;
    }



    public SimpleIntegerProperty loginProperty() {
        return new SimpleIntegerProperty(login);
    }

    public SimpleStringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public SimpleStringProperty emailProperty() {
        return new SimpleStringProperty(email);
    }

    public SimpleIntegerProperty phoneNumberProperty() {
        return new SimpleIntegerProperty(phoneNumber);
    }
}
