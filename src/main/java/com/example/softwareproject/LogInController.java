package com.example.softwareproject;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LogInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label LogInAsAdmin;

    @FXML
    private Button LogInButton;

    @FXML
    private TextField LogInLoginField;

    @FXML
    private PasswordField LogInPasswordField;


    @FXML
    void initialize() {
//        LogInAsAdmin.setOnMouseClicked(event -> {
//
//            LogInAsAdmin.getScene().getWindow().hide();
//
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("LogInAdmin-view.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            Parent root = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.showAndWait();
//        });


//        LogInButton.setOnMouseClicked(event -> {
//            LogInButton.getScene().getWindow().hide();
//
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("University-view.fxml"));
//            try {
//                Parent root = loader.load();
//                UniversityFacade universityFacadeController = loader.getController();
//                universityFacadeController.hideAdminControls();
//
//                Stage stage = new Stage();
//                stage.setScene(new Scene(root, 900, 613));
//                stage.showAndWait();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
    }
    public void LoginButtonClick(MouseEvent event) {
        String studentLoginCheck = LogInLoginField.getText();
        String studentPassword = LogInPasswordField.getText();


        if(checkCorrection(studentLoginCheck,studentPassword) == false)
            return;

        try {
            int studentLogin = Integer.parseInt(studentLoginCheck);

            DataBaseConnection.getInstance().checkStudent(studentLogin,studentPassword);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("University-view.fxml"));
            Parent root = loader.load();

            UniversityFacade universityFacadeController = loader.getController();
            universityFacadeController.hideAdminControls();
            universityFacadeController.setStudentConnection(studentLogin,studentPassword);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 900, 613));
            stage.show();
        } catch (SQLException e) {
            showAlert("Error","Student ID or password is incorrect!",Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (IOException e) {
            showAlert("Error","IOException", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private boolean checkCorrection(String studentLoginCheck, String studentPassword) {
        if (studentLoginCheck.isEmpty() || studentPassword.isEmpty()) {
            showAlert("Error","Login or password can not be empty", Alert.AlertType.ERROR);
            return false;
        }
        if(studentLoginCheck.matches(".*[a-zA-Z].*")){
            showAlert("Error","Логин должен состоять только из цифр", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }

    @FXML
    public void LoginAsAdminClick(MouseEvent event) {


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LogInAdmin-view.fxml"));
        try {
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
