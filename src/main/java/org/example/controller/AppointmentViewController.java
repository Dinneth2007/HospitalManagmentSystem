package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.db.DBConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AppointmentViewController implements Initializable {

    @FXML
    private Button BtnBook;

    @FXML
    private ComboBox DropDoctorId;

    @FXML
    private ComboBox DropPatientId;

    @FXML
    private Label lblDoctorName;

    @FXML
    private Label lblPatientName;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtTime;

    @FXML
    void BtnActionBook(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadTitles();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void loadTitles() throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        ObservableList<String> DocIdLists= FXCollections.observableArrayList();
        String SQL = "Select name From Doctor";
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            DocIdLists.add(rst.getString("name"));


        }
        DropDoctorId.setItems(DocIdLists);
    }
}
