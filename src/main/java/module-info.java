module com.example.softwareproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.softwareproject to javafx.fxml;
    exports com.example.softwareproject;
    exports com.example.softwareproject.Quiz;
    opens com.example.softwareproject.Quiz to javafx.fxml;
}