package com.example.softwareproject.UML.persons;

public interface PersonFactory {
    PersonInUniversity createPerson(String name, String lastName,int login,String password, String email, int phoneNumber,int course, String gender);
}