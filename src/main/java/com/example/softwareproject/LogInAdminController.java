package com.example.softwareproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class LogInAdminController {
    public boolean isAdmin = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label LogInAdminBackButton;

    @FXML
    private Button LogInAdminButton;

    @FXML
    private TextField LogInAdminLoginField;

    @FXML
    private PasswordField LogInAdminPasswordField;

    @FXML
    void initialize() {



    }
    @FXML
    void logInAdminButtonClick(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("University-view.fxml"));
        try {
            Parent root = loader.load();

            UniversityFacade universityFacadeController = loader.getController();
            universityFacadeController.hideStudentControls();
            universityFacadeController.setConnectionHistory();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 900, 613));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void LogInAdminBackButtonClick(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LogIn-view.fxml"));
        try {
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
