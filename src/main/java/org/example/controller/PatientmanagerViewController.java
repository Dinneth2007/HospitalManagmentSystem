package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.Patient;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientmanagerViewController implements Initializable{
    public Button BtnSearchByID;
    PatientController controller;
    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnDlt;

    @FXML
    private Button BtnUpdate;

    @FXML
    private Button BtnView;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmergencyCtct;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtId;

    @FXML
    private TextArea txtMedicalHistory;

    @FXML
    private TextField txtName;

    @FXML
    void BtnActionAdd(ActionEvent event)  {
        try {

            System.out.println(controller.add(new Patient(txtName.getText(),Integer.parseInt(txtAge.getText()),txtGender.getText(),txtContact.getText(),txtEmergencyCtct.getText(),txtMedicalHistory.getText()))==true?"Patient Added":"Error");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    void BtnActionDelete(ActionEvent event) {
        try {
            System.out.println(controller.delete(Integer.parseInt(txtId.getText())));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void BtnActionUpdate(ActionEvent event) {
        try {
            System.out.println(controller.update(new Patient(Integer.parseInt(txtId.getText()),txtName.getText(),Integer.parseInt(txtAge.getText()),txtGender.getText(),txtContact.getText(),txtEmergencyCtct.getText(),txtMedicalHistory.getText()))==true?"Updated Sucessfully":"Error");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void BtnActionView(ActionEvent event) throws IOException {
        Stage stage=new Stage();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PatientView.fxml"))));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller=new PatientController();
    }

    public void BtnActionSearchByID(ActionEvent actionEvent) throws SQLException {
        Patient patient=controller.serachById(txtId.getText());
        txtName.setText(patient.getName());
        txtContact.setText(patient.getContact_details());
        txtEmergencyCtct.setText(patient.getEmergency_contact());
        txtAge.setText(String.valueOf(patient.getAge()));
        txtGender.setText(patient.getGender());
        txtMedicalHistory.setText(patient.getMedical_history());


    }
}
