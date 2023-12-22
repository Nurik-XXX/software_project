package com.example.softwareproject;

import com.example.softwareproject.courses.Course;
import com.example.softwareproject.UML.persons.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AttemptQuizController {

    @FXML
    private TableColumn<Course,String> courseIdColumn;

    @FXML
    private TableColumn<Course, String> courseNameColumn;

    @FXML
    private TableColumn<Course, Button> startButtonColumn;

    @FXML
    private TableView<Course> startQuizTable;

    private Student student;


    @FXML
    void initialize() throws SQLException {

        List<Course> courses = DataBaseConnection.getInstance().retrieveAvailableCoursesFromDatabase();


        courseIdColumn.setCellValueFactory(cellData -> cellData.getValue().courseIdProperty());
        courseNameColumn.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());

        startQuizTable.getItems().addAll(courses);


        startButtonColumn.setCellFactory(param -> new TableCell<Course,Button>(){
            private final Button startButton = new Button("start");

            {
                startButton.setOnAction(event -> {
                    Course course = getTableView().getItems().get(getIndex());
                    handleStartButtonClick(course);
                });
            }
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(startButton);
                    setAlignment(Pos.CENTER);
                }
            }
        });
    }
    private void handleStartButtonClick(Course course) {
        // Ваш код для сохранения данных в таблицу enrollments
        // Используйте объект course для получения необходимой информации
        // Например, course.getCourseId(), course.getInstructorId() и т.д.

        ClassTemplate.setCourse(course);

        FXMLLoader loader = new FXMLLoader();

        if(course.getCourseId().equals("MAT251"))
            loader.setLocation(getClass().getResource("MAT251-view.fxml"));
        else if(course.getCourseId().equals("CSS215"))
            loader.setLocation(getClass().getResource("CSS215-view.fxml"));
        else
            loader.setLocation(getClass().getResource("CSS215-view.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void closeButtonClick(MouseEvent event) {
        startQuizTable.getScene().getWindow().hide();
    }

}