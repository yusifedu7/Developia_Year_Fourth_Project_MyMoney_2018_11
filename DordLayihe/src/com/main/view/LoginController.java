package com.main.view;

import com.main.db.DB;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    private DB db = DB.getInstange();

    @FXML
    private Button createAccountButton;

    @FXML
    private Label warningsLabel;

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private Button enterButton;

    @FXML
    void enterButtonPressed(ActionEvent event) {

        String username = usernameTF.getText();
        String password = passwordPF.getText();

        if (db.hasRowInTableForThisCondition("users", "")) {
            if (db.hasRowInTableForThisCondition("users", "where username='" + username + "' and password ='" + password + "'")) {

                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("main/Main.fxml"));
stage.initModality(Modality.APPLICATION_MODAL);
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                msg("Məlumatları səhv daxil etdiniz");
            }
        } else {
            msg("Hesab açmamısız");
        }

    }

    private void msg(String m) {
        warningsLabel.setText(m);
    }

    @FXML
    void createAccountButtonPressed(ActionEvent event) {

        String username = usernameTF.getText();
        String password = passwordPF.getText();

        if (username.trim().equals("")) {
            msg("İstifadəçi adını boş qoymaq olmaz");
        } else {

            if (password.trim().equals("")) {
                msg("Şifrəni    boş qoymaq olmaz");
            } else {
                db.iud("insert into users (username,password)  values ('" + username + "','" + password + "') ");
                createAccountButton.setVisible(false);
                msg("Hesab yaradıldı");

            }
        }

    }

    public void deleteAccountButtonPressed(ActionEvent event) {
        db.iud("truncate table users");// bu kod users cedvelini tam silir, 
        createAccountButton.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (db.hasRowInTableForThisCondition("users", "")) {
            createAccountButton.setVisible(false);
        }
    }

}
