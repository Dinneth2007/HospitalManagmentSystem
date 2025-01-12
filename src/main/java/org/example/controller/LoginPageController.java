package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
    public JFXPasswordField Pwdtxt;
    public JFXTextField IDtxt;
    public JFXButton loginBtn;
    LoginController controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            controller=new LoginController();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }


    public void login(ActionEvent actionEvent) {
        try {
            boolean correct=controller.login(IDtxt.getText(),Pwdtxt.getText());
            if (correct){
                Stage stage=new Stage();

                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/MainView.fxml"))));
                stage.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
