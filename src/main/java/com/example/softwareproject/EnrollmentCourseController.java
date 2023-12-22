package com.example.softwareproject;

import com.example.softwareproject.courses.Course;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

//import com.example.softwareproject.courses.Course;


import java.sql.SQLException;
import java.util.List;

public class EnrollmentCourseController {

    @FXML
    private TableView<Course> enrollmentTableView;

    @FXML
    private TableColumn<Course, String> courseIdColumn;

    @FXML
    private TableColumn<Course, String> courseNameColumn;

    @FXML
    private TableColumn<Course, String> instructorIdColumn;

    @FXML
    private TableColumn<Course, Integer> creditsColumn;

    @FXML
    private TableColumn<Course, Button> pickButtonColumn;

    private String studentLogin;



    @FXML
    void initialize() throws SQLException {

        studentLogin = LoggedStudent.getLoggetStudent();

        // Предполагается, что у вас есть метод для извлечения списка курсов из базы данных
        List<Course> courses = DataBaseConnection.getInstance().retrieveCoursesFromDatabase();

        System.out.println("курсы получены!");

        // Заполните таблицу данными
        courseIdColumn.setCellValueFactory(cellData -> cellData.getValue().courseIdProperty());
        courseNameColumn.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
        instructorIdColumn.setCellValueFactory(cellData -> cellData.getValue().instructorProperty());
        creditsColumn.setCellValueFactory(cellData -> cellData.getValue().creditsProperty().asObject());

        enrollmentTableView.getItems().addAll(courses);

        pickButtonColumn.setCellFactory(param -> new TableCell<Course, Button>() {
            private final Button pickButton = new Button("Pick");

            {
                pickButton.setOnAction(event -> {
                    Course course = getTableView().getItems().get(getIndex());
                    handlePickButtonClick(course);
                });
            }

            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pickButton);
                    setAlignment(Pos.CENTER);
                }
            }
        });
    }

    private void handlePickButtonClick(Course course) {
        // Ваш код для сохранения данных в таблицу enrollments
        // Используйте объект course для получения необходимой информации
        // Например, course.getCourseId(), course.getInstructorId() и т.д.
        try {
            DataBaseConnection.getInstance().addEnrollment(course,studentLogin);

            DataBaseConnection.getInstance().addGrade(studentLogin,course,0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void closeButtonClick(MouseEvent event) {
        enrollmentTableView.getScene().getWindow().hide();
    }
}

