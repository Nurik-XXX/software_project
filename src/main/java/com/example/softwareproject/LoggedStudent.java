package com.example.softwareproject;

public class LoggedStudent {
    private static String studentLogin;

    public static void setStudentLogin(String login){
        studentLogin = login;
    }

    public static String getLoggetStudent(){
        return studentLogin;
    }


}
