package com.yusael.controller;

import com.yusael.dao.impl.UserDAOImpl;
import com.yusael.entity.User;
import com.yusael.stage.MainStage;
import com.yusael.stage.OperateStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    TextField usernameText;
    @FXML
    TextField passwordText;

    public void enter(ActionEvent event) {
        String username = usernameText.getText();
        String password = passwordText.getText();
        // System.out.println(userService);
        // User user = userService.login(usernmae, password);

        User user = new UserDAOImpl().login(username, password);

        if (user != null) {
            System.out.println("存在该账户, 可以登录！！");
            try {
                new OperateStage().start(new Stage());
                ((Node)(event.getSource())).getScene().getWindow().hide(); // 关闭当前窗口
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("用户名或密码错误");
            alert.setHeaderText(null);
            alert.setContentText("请输入正确的用户名和密码！！！");

            alert.showAndWait();
        }
    }

    public void cancel(ActionEvent event) {
        try {
            new MainStage().start(new Stage());
            ((Node)(event.getSource())).getScene().getWindow().hide(); // 关闭当前窗口
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
