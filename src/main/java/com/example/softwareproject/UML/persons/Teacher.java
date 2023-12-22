package com.example.softwareproject.UML.persons;

public class Teacher implements PersonInUniversity{
    private String name;
    private String lastName;
    private String email;
    private int phoneNumber;
    private String gender;


    public Teacher(String name, String lastName, String email, int phoneNumber, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;

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
}
