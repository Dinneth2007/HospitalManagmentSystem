package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.model.Doctor;
import org.example.model.Patient;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class doctormanagerviewcontroller implements Initializable {
    public DatePicker dataPicker;
    public ComboBox datesavailable;
    DoctorController controller;
    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnDlt;

    @FXML
    private Button BtnSearch;

    @FXML
    private Button BtnUpdate;



    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextArea txtQuali;

    @FXML
    private TextField txtSpeciality;

    @FXML
    void BtnOnActionAdd(ActionEvent event) {
        try {
            System.out.println(controller.add(new Doctor(txtName.getText(),txtSpeciality.getText(),datesavailable.getValue().toString(),txtQuali.getText(),txtContact.getText())));
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    @FXML
    void BtnOnActionDelete(ActionEvent event) {
        try {
            if (controller.delete(Integer.parseInt(txtID.getText()))){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted Sucessfuly").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void BtnOnActionSearch(ActionEvent event) throws SQLException {
        setValues(controller.serachById(txtID.getText()));
    }

    @FXML
    void BtnOnActionUpdate(ActionEvent event) {
        try {
            controller.update(new Doctor(Integer.parseInt(txtID.getText()),txtName.getText(),txtSpeciality.getText(),datesavailable.getValue().toString(),txtQuali.getText(),txtContact.getText()));
            new Alert(Alert.AlertType.CONFIRMATION,"Updated SucessFuly").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller=new DoctorController();
        try {
            txtID.setText(String.valueOf(controller.loadNewDoctorId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadTitles();
    }
    private void setValues(Doctor doctor){
        txtName.setText(doctor.getName());
        txtContact.setText(doctor.getContact_details());
        txtQuali.setText(doctor.getQualifications());
        txtSpeciality.setText(doctor.getSpeciality());
        datesavailable.setPromptText(doctor.getAvailabilty());

    }
    public void loadTitles(){
        ObservableList<String> titleLists= FXCollections.observableArrayList();
        titleLists.add("Monday");
        titleLists.add("Tuesday");
        titleLists.add("Wednesday");
        titleLists.add("Thurday");
        titleLists.add("Friday");
        titleLists.add("Saturday");
        titleLists.add("Sunday");
        datesavailable.setItems(titleLists);
    }
}
