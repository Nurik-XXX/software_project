package com.example.softwareproject.UML.persons;

public class StudentFactory implements PersonFactory {
    @Override
    public PersonInUniversity createPerson(String name, String lastName,int login,String password, String email, int phoneNumber,int course, String gender) {
        return new Student(name,lastName,login,password,email,phoneNumber,course,gender);
    }
}
