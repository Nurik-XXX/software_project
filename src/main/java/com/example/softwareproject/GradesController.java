package com.example.softwareproject;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.softwareproject.courses.Grade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class GradesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Grade, String> courseIdColumn;

    @FXML
    private TableColumn<Grade, String> courseNameColumn;

    @FXML
    private TableColumn<Grade, Integer> gradeColumn;

    @FXML
    private TableView<Grade> gradesTable;

    private final ObservableList<Grade> data = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        courseIdColumn.setCellValueFactory(cellData -> cellData.getValue().courseIdProperty());
        courseNameColumn.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradesProperty().asObject());

        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        try (PreparedStatement statement = DataBaseConnection.getInstance().connection().prepareStatement("SELECT c.course_id,c.course_name,g.grade_percentage FROM grades g " +
                                                                                                                "JOIN courses c ON g.course_id = c.course_id " +
                                                                                                                "WHERE student_id = " + LoggedStudent.getLoggetStudent())) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String courseId = (resultSet.getString("course_id"));
                    String courseName = resultSet.getString("course_name");
                    int grade = (resultSet.getInt("grade_percentage"));

                    Grade gradeObject = new Grade(courseId,courseName,grade);
                    data.add(gradeObject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        gradesTable.setItems(data);
    }



    @FXML
    void closeButtonClick(MouseEvent event) {
        gradesTable.getScene().getWindow().hide();
    }
}
