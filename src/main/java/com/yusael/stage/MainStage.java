package com.yusael.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends javafx.application.Application {
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("17210224陆振宇——主界面");
        primaryStage.setScene(new Scene(root, 550, 380));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}