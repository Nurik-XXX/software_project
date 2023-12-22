package com.example.softwareproject;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.softwareproject.personFactory.PersonFactory;
import com.example.softwareproject.personFactory.StudentFactory;
import com.example.softwareproject.UML.persons.PersonInUniversity;
import com.example.softwareproject.UML.persons.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class updateStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField studentCourseField;

    @FXML
    private TextField studentEmailField;

    @FXML
    private TextField studentGenderField;

    @FXML
    private TextField studentIDField;

    @FXML
    private TextField studentNameField;

    @FXML
    private TextField studentPasswordField;

    @FXML
    private TextField studentPhoneField;

    @FXML
    private TextField studentSurnameField;

    @FXML
    private Button updateStudentButton;

    @FXML
    void initialize() {
        studentIDField.setEditable(false);
        studentCourseField.setEditable(false);
        studentGenderField.setEditable(false);
    }

    @FXML
    void updateButtonClick(MouseEvent event) {

        try{
            int id = Integer.parseInt(studentIDField.getText());
            String name = studentNameField.getText();
            String surname = studentSurnameField.getText();
            String password = studentPasswordField.getText();
            String email = studentEmailField.getText();
            int phone = Integer.parseInt(studentPhoneField.getText());
            int course = Integer.parseInt(studentCourseField.getText());
            String gender = studentGenderField.getText();

            if(checkCorrection(id,name,surname,password,email,phone,course,gender) == false)
                return;


            PersonFactory personFactory = new StudentFactory();
            PersonInUniversity student = personFactory.createPerson(name,surname, id,password,email,phone,course,gender);

            DataBaseConnection.getInstance().updateStudent((Student) student);

            showAlert("Success", "Student details updated successfully", Alert.AlertType.INFORMATION);
            updateStudentButton.getScene().getWindow().hide();
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input. Please enter valid numeric values for Phone.", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            showAlert("Error", "Failed to update student details to the database.", Alert.AlertType.ERROR);
            e.printStackTrace(); // Log the exception for debugging purposes
        }

    }

    public void setUpdateDetails(Student student){
        try{
            studentIDField.setText(String.valueOf(student.getLogin()));
            studentNameField.setText(student.getName());
            studentSurnameField.setText(student.getLastName());
            studentPasswordField.setText(student.getPassword());
            studentEmailField.setText(student.getEmail());
            studentPhoneField.setText(String.valueOf(student.getPhoneNumber()));
            studentCourseField.setText(String.valueOf(student.getCourse()));
            studentGenderField.setText(student.getGender());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkCorrection(int id, String name, String surname, String password, String email, int phone, int course, String gender) {
        if (id <= 99999 || id >= 1000000000) {
            showAlert("Ошибка в данных", "ID должен быть больше 5-и цифр и меньше 20-и цифр.", Alert.AlertType.ERROR);
            return false;
        }

        if (!name.matches("[a-zA-Z]+") || !surname.matches("[a-zA-Z]+")) {
            showAlert("Ошибка в данных", "Имя и фамилия должны состоять только из латинских букв.", Alert.AlertType.ERROR);
            return false;
        }

        if (!password.matches("[a-zA-Z0-9]+") || password.length() < 5) {
            showAlert("Ошибка в данных", "Пароль должен состоять из латинских букв и цифр, и быть не менее 5 символов.", Alert.AlertType.ERROR);
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

        if (course < 1 || course > 4) {
            showAlert("Ошибка в данных", "Курс должен быть 1, 2, 3 или 4.", Alert.AlertType.ERROR);
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
