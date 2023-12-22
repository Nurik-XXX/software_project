package com.example.softwareproject;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.softwareproject.UML.persons.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UniversityFacade {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button addCourseButton;
    @FXML
    private Button addStudentButton;
    @FXML
    private Button addTeacherButton;
    @FXML
    private VBox adminControls;
    @FXML
    private VBox centerRoot;
    @FXML
    private Button attempQuizButton;
    @FXML
    private Button enrollmentCourseButton;
    @FXML
    private Button gradesButton;
    @FXML
    private TextField outCourse;
    @FXML
    private TextField outEmail;
    @FXML
    private TextField outID;
    @FXML
    private TextField outName;
    @FXML
    private TextField outPhone;
    @FXML
    private TextField outSurname;
    @FXML
    private Button signOutButton;
    @FXML
    private VBox studentControls;
    @FXML
    private Pane studentInformation;
    @FXML
    private Button updateDetailsButton;
    @FXML
    private Label studentInformationORLastConnection;
    @FXML
    private SplitMenuButton utilityButton;

    private Student student;



    @FXML
    void initialize() {

        outName.setEditable(false);
        outSurname.setEditable(false);
        outID.setEditable(false);
        outCourse.setEditable(false);
        outEmail.setEditable(false);
        outCourse.setEditable(false);
    }
    public void setStudentConnection(int login, String password){
        try (PreparedStatement statement = DataBaseConnection.getInstance().connection()
                .prepareStatement("SELECT * FROM students WHERE student_id = ? AND student_password = ?")) {
            statement.setInt(1, login);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    String name = resultSet.getString("student_name");
                    String lastName = resultSet.getString("student_surname");
                    String email = resultSet.getString("student_email");
                    int phoneNumber = resultSet.getInt("student_phone");
                    int course = resultSet.getInt("student_course");
                    String gender = resultSet.getString("student_gender");

                    student = new Student(name, lastName, login, password, email, phoneNumber, course, gender);

                    LoggedStudent.setStudentLogin(String.valueOf(student.getLogin()));

                    outName.setText(student.getName());
                    outSurname.setText(student.getLastName());
                    outID.setText(String.valueOf(student.getLogin()));
                    outCourse.setText(String.valueOf(student.getCourse()));
                    outEmail.setText(student.getEmail());
                    outPhone.setText(String.valueOf(student.getPhoneNumber()));
                } else {
                    throw new SQLException("Студент не найден");
                }
            } catch (SQLException e) {
                throw new SQLException(e + "setStudentConnection");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void hideStudentControls(){
        studentControls.setVisible(false);
        studentControls.setManaged(false);
    }

    public void hideAdminControls(){
        adminControls.setVisible(false);
        adminControls.setManaged(false);
    }



    @FXML
    void updateDetailsButtonClick(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("updateStudent-view.fxml"));

        try {
            loader.load();
            updateStudentController updateStudentController = loader.getController();
            updateStudentController.setUpdateDetails(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void setConnectionHistory() throws IOException {
        System.out.println("connectHistory");
        centerRoot.getChildren().removeAll(studentInformation,signOutButton);
        studentInformation = FXMLLoader.load(getClass().getResource("StudentsTable-view.fxml"));
        centerRoot.getChildren().addAll(studentInformation,signOutButton);
    }

    @FXML
    void gradesButtonClick(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("grades-view.fxml"));

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
    public void enrollmentCourseButtonClick(MouseEvent event){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("enrollmentCourse-view.fxml"));

        try {


//            LoggedStudent.setStudentLogin(String.valueOf(student.getLogin()));

            loader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
//
    }

    @FXML
    void attemptQuizButtonClick(MouseEvent event) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("attemptQuiz.fxml"));

        System.out.println("Stud login "+student.getLogin());

        try {
            loader.load();

//            EnrollmentCourseController enrollmentCourseController = new EnrollmentCourseController(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }





    @FXML
    public void AddStudentButtonClick(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addStudent-view.fxml"));
        try {
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addTeacherButtonClick(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addTeacher-view.fxml"));
        try {
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addCourseButtonClick(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addCourse-view.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }


    @FXML
    public void signOutButtonClick(MouseEvent event) {
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

    @FXML
    void openCalculator(ActionEvent event) {
        try {
            // Open the calculator on Windows
            Runtime.getRuntime().exec("calc");

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }

    @FXML
    void openCalendar(ActionEvent event) {
        try {
            // Open Notepad on Windows
            Runtime.getRuntime().exec("notepad");

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
}
