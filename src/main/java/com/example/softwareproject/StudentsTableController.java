package com.example.softwareproject;

import com.example.softwareproject.UML.persons.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.*;

public class StudentsTableController implements Initializable {

    @FXML
    private Pane StudentsTablePane;

    @FXML
    private TableColumn<Student, Integer> studentIdColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> emailColumn;

    @FXML
    private TableColumn<Student, Integer> phoneColumn;

    @FXML
    private TableView<Student> studentsTableView;

    private final ObservableList<Student> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentsTableView.setEditable(false);
        studentIdColumn.setResizable(false);
        nameColumn.setResizable(false);
        emailColumn.setResizable(false);
        phoneColumn.setResizable(false);

        loadDate();
        loadDataFromDatabase();
    }

    private void loadDate() {
        studentIdColumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty().asObject());
    }

    private void loadDataFromDatabase() {
        try (PreparedStatement statement = DataBaseConnection.getInstance().connection().prepareStatement("SELECT * FROM students")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int login = resultSet.getInt("student_id");
                    String name = resultSet.getString("student_name");
                    String email = resultSet.getString("student_email");
                    int phoneNumber = resultSet.getInt("student_phone");

                    Student student = new Student(login, name, email, phoneNumber);
                    data.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        studentsTableView.setItems(data);
    }

}
