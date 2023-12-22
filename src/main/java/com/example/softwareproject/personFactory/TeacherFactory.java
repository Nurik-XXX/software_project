package com.example.softwareproject.personFactory;

import com.example.softwareproject.UML.persons.PersonInUniversity;
import com.example.softwareproject.UML.persons.Teacher;

public class TeacherFactory implements PersonFactory{
    @Override
    public PersonInUniversity createPerson(String name, String lastName, int login, String password, String email, int phoneNumber, int course, String gender) {
        return new Teacher(name,lastName,email,phoneNumber,gender);
    }
}
