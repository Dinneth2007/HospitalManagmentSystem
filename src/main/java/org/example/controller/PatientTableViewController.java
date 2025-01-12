package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Patient;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientTableViewController implements Initializable {
    public TableView table;
    PatientController controller;
    @FXML
    private Button BtnReload;

    @FXML
    private TableColumn agecol;

    @FXML
    private TableColumn contactcol;

    @FXML
    private TableColumn emergencycol;

    @FXML
    private TableColumn gendercol;

    @FXML
    private TableColumn idcol;

    @FXML
    private TableColumn mhcol;

    @FXML
    private TableColumn namecol;

    @FXML
    void BtnOnActionReload(ActionEvent event) {
        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        agecol.setCellValueFactory(new PropertyValueFactory<>("age"));
        contactcol.setCellValueFactory(new PropertyValueFactory<>("contact_details"));
        emergencycol.setCellValueFactory(new PropertyValueFactory<>("emergency_contact"));
        mhcol.setCellValueFactory(new PropertyValueFactory<>("medical_history"));
        controller=new PatientController();
        loadTable(); 
    }
    public void loadTable(){
        ObservableList<Patient> patientObsList= FXCollections.observableArrayList();
        try {
            controller.getPatients().forEach(patient -> {
                patientObsList.add(patient);
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        table.setItems(patientObsList);
    }
}






