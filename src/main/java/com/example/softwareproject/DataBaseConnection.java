package com.example.softwareproject;

import com.example.softwareproject.courses.Course;
import com.example.softwareproject.UML.persons.Student;
import com.example.softwareproject.UML.persons.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.*;


// TODO: 15.12.2023  Singleton
public class DataBaseConnection{
    private static DataBaseConnection instance;
    private final Connection connection;
    private DataBaseConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SoftwareProject","postgres","nurik2004" );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static synchronized DataBaseConnection getInstance(){
        if(instance == null)
            instance = new DataBaseConnection();
        return instance;
    }
    public Connection connection(){
        return connection;
    }

    public void checkStudent(int student_id,String student_password) throws SQLException{
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE student_id = ? and student_password = ?")){
            statement.setInt(1,student_id);
            statement.setString(2,student_password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Студент существует.");
                } else {
                    throw new SQLException("Студент не найден");
                }
            }
        }

    }



    public void addStudent(Student student) throws SQLException{
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO students (student_id,student_password,student_name,student_surname,student_email,student_phone,student_course,student_gender) VALUES(?,?,?,?,?,?,?,?)")){
            statement.setInt(1,student.getLogin());
            statement.setString(2,student.getPassword());
            statement.setString(3,student.getName());
            statement.setString(4,student.getLastName());
            statement.setString(5,student.getEmail());
            statement.setInt(6,student.getPhoneNumber());
            statement.setInt(7,student.getCourse());
            statement.setString(8,student.getGender());
            statement.executeUpdate();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE students SET student_name=?,student_surname=?, student_password=?, student_email=?, student_phone=? WHERE student_id=?")) {
            statement.setString(1,student.getName());
            statement.setString(2,student.getLastName());
            statement.setString(3, student.getPassword());
            statement.setString(4, student.getEmail());
            statement.setInt(5, student.getPhoneNumber());
            statement.setInt(6, student.getLogin());
            statement.executeUpdate();

        }
    }


    public void addTeacher(Teacher teacher) throws SQLException {

        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO teachers (teacher_name,teacher_surname,teacher_email,teacher_phone,teacher_gender) VALUES(?,?,?,?,?)")){
            statement.setString(1,teacher.getName());
            statement.setString(2,teacher.getLastName());
            statement.setString(3,teacher.getEmail());
            statement.setInt(4,teacher.getPhoneNumber());
            statement.setString(5,teacher.getGender());
            statement.executeUpdate();
        }
    }

    public void addCourse(Course course) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO courses (course_id,course_name,instructor_id,course_credits) VALUES(?,?,?,?)")){
            statement.setString(1,course.getCourseId());
            statement.setString(2,course.getCourseName());
            statement.setInt(3,getInstructorIdByName(course.getInstructorId()));
            statement.setInt(4,course.getCredits());
            statement.executeUpdate();
        }
    }


    public void addEnrollment(Course course, String studentLogin) throws SQLException {
        try (PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM enrollments WHERE student_id = ? AND course_id = ?")) {
            checkStatement.setInt(1, Integer.parseInt(studentLogin));
            checkStatement.setString(2, course.getCourseId());

            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
                    showAlert("Дубликат записи", "Студент уже записан на данный курс.",Alert.AlertType.ERROR);
                } else {
                    try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO enrollments( student_id, course_id) VALUES(?,?)")) {
//                        insertStatement.setString(1, "E" + cntEnrollment++);
                        insertStatement.setInt(1, Integer.parseInt(studentLogin));
                        insertStatement.setString(2, course.getCourseId());
                        insertStatement.executeUpdate();

                        showAlert("Nice job!","Student: "+ studentLogin + " succesfully picked course: " + course.getCourseId(),Alert.AlertType.CONFIRMATION);
                    }
                }
            }
        }
    }

    public void addGrade(String studentLogin,Course course,int scoreInPercentage) throws SQLException {
        try (PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM grades WHERE student_id = ? AND course_id = ?")) {
            checkStatement.setInt(1, Integer.parseInt(studentLogin));
            checkStatement.setString(2, course.getCourseId());

            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
                    return;
                } else {
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO grades (student_id,course_id,grade_percentage) VALUES(?,?,?)")) {
                        statement.setInt(1, Integer.parseInt(studentLogin));
                        statement.setString(2, course.getCourseId());
                        statement.setInt(3, scoreInPercentage);
                        statement.executeUpdate();
                    }
                }
            }
        }
    }
//    public void updateStudent(Student student) throws SQLException {
//        try (PreparedStatement statement = connection.prepareStatement(
//                "UPDATE students SET student_name=?,student_surname=?, student_password=?, student_email=?, student_phone=? WHERE student_id=?")) {
//            statement.setString(1,student.getName());
//            statement.setString(2,student.getLastName());
//            statement.setString(3, student.getPassword());
//            statement.setString(4, student.getEmail());
//            statement.setInt(5, student.getPhoneNumber());
//            statement.setInt(6, student.getLogin());
//            statement.executeUpdate();
//
//        }
//    }

    public void updateGrade(String studentLogin,Course course,int scoreInPercentage) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE grades SET grade_percentage=? WHERE student_id=? AND course_id =?")){
            statement.setInt(1, scoreInPercentage);
            statement.setInt(2, Integer.parseInt(studentLogin));
            statement.setString(3,course.getCourseId());
            statement.executeUpdate();
        }
    }

    private void showAlert(String title, String content,Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public int getInstructorIdByName(String instructorName) throws SQLException {
        String query = "SELECT teacher_id FROM teachers WHERE teacher_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, instructorName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("teacher_id");
                } else {
                    throw new SQLException("Инструктор не найден");
                }
            }
        }
    }


    public List<Course> retrieveCoursesFromDatabase() throws SQLException {
        List<Course> courses = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM courses")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String courseId = resultSet.getString("course_id");
                    String courseName = resultSet.getString("course_name");
                    String instructorId = resultSet.getString("instructor_id");
                    int credits = resultSet.getInt("course_credits");

                    Course course = new Course(courseId, courseName, instructorId, credits);
                    courses.add(course);
                }
            }
        }

        return courses;
    }

    public List<Course> retrieveAvailableCoursesFromDatabase() throws SQLException {
        List<Course> courses = new ArrayList<>();

        String  studentLogin = LoggedStudent.getLoggetStudent();
        System.out.println("DB inter " + studentLogin);



        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM courses c " +
                                                                            "JOIN enrollments e ON e.course_id = c.course_id " +
                                                                             "WHERE e.student_id = "+studentLogin )) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String courseId = resultSet.getString("course_id");
                    String courseName = resultSet.getString("course_name");
                    String instructorId = resultSet.getString("instructor_id");
                    int credits = resultSet.getInt("course_credits");

                    Course course = new Course(courseId, courseName, instructorId, credits);
                    courses.add(course);
                }
            }
        }

        return courses;
    }

}
