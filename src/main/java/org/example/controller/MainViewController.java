package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    @FXML
    private Button BtnPatientReg;

    @FXML
    void BtnActionAppointmentManager(ActionEvent event) {

    }

    @FXML
    void BtnActionDoctorReg(ActionEvent event) throws IOException {
        Stage stage=new Stage();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DoctorManagerView.fxml"))));
        stage.show();
    }

    @FXML
    void BtnActionPatientReg(ActionEvent event) throws IOException {
        Stage stage=new Stage();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PatientManagerView.fxml"))));
        stage.show();
    }

    @FXML
    void BtnActionPrescriptionManager(ActionEvent event) {

    }

}
