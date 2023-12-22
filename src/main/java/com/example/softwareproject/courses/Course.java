package com.example.softwareproject.courses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Course {
    private String courseId;
    private String courseName;
    private String instructorId;
    private int credits;

    public Course(String courseId, String courseName, String instructorId, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorId = instructorId;
        this.credits = credits;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public int getCredits() {
        return credits;
    }

    public String getCourseId() {
        return courseId;
    }


    public SimpleStringProperty courseIdProperty() {
        return new SimpleStringProperty(courseId);
    }

    public SimpleStringProperty courseNameProperty() {
        return new SimpleStringProperty(courseName);
    }

    public SimpleStringProperty instructorProperty() {
        return new SimpleStringProperty(instructorId);
    }

    public SimpleIntegerProperty creditsProperty() {
        return new SimpleIntegerProperty(credits);
    }
}
