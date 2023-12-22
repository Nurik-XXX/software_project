package com.example.softwareproject;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.softwareproject.personFactory.PersonFactory;
import com.example.softwareproject.personFactory.TeacherFactory;
import com.example.softwareproject.UML.persons.PersonInUniversity;
import com.example.softwareproject.UML.persons.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class addTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addTeacherButton;

    @FXML
    private TextField teacherEmailField;

    @FXML
    private TextField teacherGenderField;

    @FXML
    private TextField teacherNameField;

    @FXML
    private TextField teacherPhoneField;

    @FXML
    private TextField teacherSurnameField;

    @FXML
    void initialize() {

    }

    @FXML
    public void saveButtonClick(MouseEvent event) {
        try{
            String name = teacherNameField.getText();
            String surname = teacherSurnameField.getText();
            String email = teacherEmailField.getText();
            int phone = Integer.parseInt(teacherPhoneField.getText());
            String gender = teacherGenderField.getText();

//            if(checkCorrection(name,surname,email,phone,gender) == false)
//                return;

            PersonFactory personFactory = new TeacherFactory();
            PersonInUniversity teacher = personFactory.createPerson(name,surname,0,null,email,phone,0,gender);

            DataBaseConnection.getInstance().addTeacher((Teacher) teacher);

            showAlert("Success", "Teacher added successfully", Alert.AlertType.INFORMATION);
            addTeacherButton.getScene().getWindow().hide();
        }catch (NumberFormatException e) {
            showAlert("Error", "Invalid input. Please enter valid numeric values for phone number", Alert.AlertType.ERROR);
        }catch (SQLException e) {
            showAlert("Error", "Failed to add teacher to the database.Возможно такой тичер уже есть", Alert.AlertType.ERROR);
            e.printStackTrace(); // Log the exception for debugging purposes
        }
    }

    private boolean checkCorrection(String name, String surname, String email, int phone, String gender) {

        if (!name.matches("[a-zA-Z]+") || !surname.matches("[a-zA-Z]+")) {
            showAlert("Ошибка в данных", "Имя и фамилия должны состоять только из латинских букв.", Alert.AlertType.ERROR);
            return false;
        }

        if (!email.matches(".+@.+\\.(com|ru|kz)")) {
            showAlert("Ошибка в данных", "Почта должна быть в формате example@example.com, example@example.ru, или example@example.kz.", Alert.AlertType.ERROR);
            return false;
        }

        if (String.valueOf(phone).length() < 6) {
            showAlert("Ошибка в данных", "Номер телефона должен содержать не менее 6 цифр.", Alert.AlertType.ERROR);
            return false;
        }

        if (!gender.equals("male") && !gender.equals("female")) {
            showAlert("Ошибка в данных", "Гендер должен быть 'male' или 'female'.", Alert.AlertType.ERROR);
            return false;
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

}
