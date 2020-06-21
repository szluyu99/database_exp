package com.yusael.controller;

import com.yusael.dao.impl.FlightDAOImpl;
import com.yusael.entity.Flight;
import com.yusael.stage.MainStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OperateController implements Initializable {

    @FXML
    TextField idText;
    @FXML
    DatePicker dateText;
    @FXML
    TableView flightTableView;
    @FXML
    TableColumn f_id;
    @FXML
    TableColumn f_src; // 起点
    @FXML
    TableColumn f_des; // 终点
    @FXML
    TableColumn f_date; // 日期
    @FXML
    TableColumn f_start_time; // 起飞时刻
    @FXML
    TableColumn f_end_time; // 到达时刻
    @FXML
    TableColumn f_remain_seats; // 剩余座位
    @FXML
    TableColumn f_fares; // 票价
    @FXML
    TableColumn f_discount_nums; // 折扣票
    @FXML
    TableColumn f_discount; // 折扣率
    @FXML
    TableColumn f_subordinate_company;

    public void query(ActionEvent event) {
        // datePicker.setConverter(converter);
        // datePicker.setPromptText(pattern.toLowerCase());
        System.out.println(idText.getText());
        System.out.println(dateText.getValue().toString());
        Flight flight = new FlightDAOImpl().queryById(idText.getText());
        String res = " 【航班号】:\t" + flight.getF_id() + "\n 【起点】:\t\t" + flight.getF_src()
                + "\n 【终点】:\t\t" + flight.getF_des() + "\n 【日期】:\t\t" + flight.getF_date()
                + "\n 【起飞时刻】:\t" + flight.getF_start_time() + "\n 【到达时刻】:\t" + flight.getF_end_time()
                + "\n 【剩余座位】:\t" + flight.getF_remain_seats() + "\n 【票价】:\t\t" + flight.getF_fares()
                + "\n 【折扣票数】:\t" + flight.getF_discount_nums() + "\n 【折扣率】:\t" + flight.getF_discount()
                + "\n 【航班所属航空公司】: " + flight.getF_subordinate_company();
        alert("查询结果", res, null, Alert.AlertType.INFORMATION);
    }

    public void add(ActionEvent event) {

    }

    public void exit(ActionEvent event) {
        try {
            new MainStage().start(new Stage());
            ((Node)(event.getSource())).getScene().getWindow().hide(); // 关闭当前窗口
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {

        f_id.setCellValueFactory(new PropertyValueFactory("f_id"));
        f_src.setCellValueFactory(new PropertyValueFactory("f_src"));
        f_des.setCellValueFactory(new PropertyValueFactory("f_des"));
        f_date.setCellValueFactory(new PropertyValueFactory("f_date"));
        f_start_time.setCellValueFactory(new PropertyValueFactory("f_start_time"));
        f_end_time.setCellValueFactory(new PropertyValueFactory("f_end_time"));
        f_remain_seats.setCellValueFactory(new PropertyValueFactory("f_remain_seats"));
        f_fares.setCellValueFactory(new PropertyValueFactory("f_fares"));
        f_discount_nums.setCellValueFactory(new PropertyValueFactory("f_discount_nums"));
        f_discount.setCellValueFactory(new PropertyValueFactory("f_discount"));
        f_subordinate_company.setCellValueFactory(new PropertyValueFactory("f_subordinate_company"));

        // List<Flight> flights = flightService.queryAll();
        List<Flight> flights = new FlightDAOImpl().queryAll();
        ObservableList<Flight> data = FXCollections.observableArrayList();
        for (Flight flight : flights) {
            data.add(flight);
            System.out.println(flight.getF_date());
        }
        flightTableView.setItems(data);
    }

    private void alert(String title, String content, String header, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }
}
