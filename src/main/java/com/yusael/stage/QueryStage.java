package com.yusael.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QueryStage {
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/query.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("查询界面");
        primaryStage.setScene(new Scene(root, 550, 380));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
