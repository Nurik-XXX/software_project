package com.example.softwareproject.courses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Grade {
    private String courseId;
    private String courseName;
    private int grade;


    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getGrade() {
        return grade;
    }

    public Grade(String courseId, String courseName, int grade) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.grade = grade;
    }

    public SimpleIntegerProperty gradesProperty(){
        return new SimpleIntegerProperty(grade);
    }
    public SimpleStringProperty courseIdProperty(){
        return new SimpleStringProperty(courseId);
    }
    public SimpleStringProperty courseNameProperty(){
        return new SimpleStringProperty(courseName);
    }
}
