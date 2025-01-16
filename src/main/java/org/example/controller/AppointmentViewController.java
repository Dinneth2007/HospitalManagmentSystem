package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.db.DBConnection;
import org.example.model.Appointment;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AppointmentViewController implements Initializable {
    AppointmentController controller;
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
    void BtnActionBook(ActionEvent event) throws SQLException {
        if(controller.addAppointment(new Appointment(Integer.parseInt(DropPatientId.getValue().toString()),Integer.parseInt(DropDoctorId.getValue().toString()),txtDate.getText(),txtTime.getText()))){
            new Alert(Alert.AlertType.CONFIRMATION,"Appointment Booked Sucessfuly!!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error Occured").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            controller=new AppointmentController();
            loadDoctors();
            loadPatients();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void loadDoctors() throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        ObservableList<String> DocIdLists= FXCollections.observableArrayList();
        String SQL = "Select doctor_id From Doctor";
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            DocIdLists.add(rst.getString("doctor_id"));


        }
        DropDoctorId.setItems(DocIdLists);
    }
    public void loadPatients() throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        ObservableList<String> DocIdLists= FXCollections.observableArrayList();
        String SQL = "Select patient_id From Patient";
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            DocIdLists.add(rst.getString("patient_id"));


        }
        DropPatientId.setItems(DocIdLists);
    }


    public void PatientDropAction(ActionEvent actionEvent) {
        lblPatientName.setText(DropPatientId.getValue().toString());
    }

    public void DropDoctorAction(ActionEvent actionEvent) {
        lblDoctorName.setText(DropDoctorId.getValue().toString());
    }
}
