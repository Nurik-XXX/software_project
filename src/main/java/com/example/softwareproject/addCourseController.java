package com.example.softwareproject;

import com.example.softwareproject.courses.Course;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addCourseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addCourseButton;

    @FXML
    private TextField courseCreditsField;

    @FXML
    private TextField courseIdField;

    @FXML
    private TextField courseNameField;

    @FXML
    private ComboBox<String> instructorIdComboBox;

    @FXML
    void saveButtonClick(MouseEvent event) {
        try{
            String courseId = courseIdField.getText();
            String courseName = courseNameField.getText();
            String instructorId = (String) instructorIdComboBox.getSelectionModel().getSelectedItem();
            int credits = Integer.parseInt(courseCreditsField.getText());

            if(checkCorrection(instructorId,credits) == false)
                return;

            Course course = new Course(courseId,courseName,instructorId,credits);

            DataBaseConnection.getInstance().addCourse(course);

            showAlert("Success", "Course added successfully", Alert.AlertType.INFORMATION);
            addCourseButton.getScene().getWindow().hide();
        }catch (NumberFormatException e) {
            showAlert("Error", "Invalid input. Please enter valid numeric values for course credits.", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            showAlert("Error", "Failed to add course to the database. Возможно такой курс уже есть", Alert.AlertType.ERROR);
            e.printStackTrace(); // Log the exception for debugging purposes
        }
    }

    private boolean checkCorrection(String instructorId, int credits) {
        if (credits < 1 || credits > 7) {
            showAlert("Ошибка в данных", "Курс может весить от 1 до 8и кредитов", Alert.AlertType.ERROR);
            return false;
        }

        if(instructorId.equals("Choose instructor") || instructorId == null){
            showAlert("Ошибка в данных", "Выберите инструктора !", Alert.AlertType.ERROR);
        }

        return true;
    }
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        populateInstructorsComboBox();

        instructorIdComboBox.setValue("Choose instructor");
    }

    private void populateInstructorsComboBox() {
        try {
            Connection connection = DataBaseConnection.getInstance().connection();

            String query = "SELECT teacher_name FROM teachers";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                instructorIdComboBox.getItems().clear();

                while (resultSet.next()) {
                    instructorIdComboBox.getItems().add(resultSet.getString("teacher_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
