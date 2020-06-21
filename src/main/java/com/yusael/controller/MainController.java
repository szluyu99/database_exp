package com.yusael.controller;

import com.yusael.stage.LoginStage;
import com.yusael.stage.QueryStage;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // 票务管理
    public void ticketsManage(ActionEvent event) {
        try {
            new LoginStage().start(new Stage());
            ((Node)(event.getSource())).getScene().getWindow().hide(); // 关闭当前窗口
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 票务查询
    public void ticketsQuery(ActionEvent event) {
        try {
            new QueryStage().start(new Stage());
            ((Node)(event.getSource())).getScene().getWindow().hide(); // 关闭当前窗口
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}