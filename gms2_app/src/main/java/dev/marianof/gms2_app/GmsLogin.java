package dev.marianof.gms2_app;

import dev.marianof.gms2_app.Controller.GmsLoginController;
import dev.marianof.gms2_app.Controller.Navigator;
import dev.marianof.gms2_app.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GmsLogin implements Initializable {

    @FXML
    public PasswordField passField;
    @FXML
    public Button btnLogin;
    @FXML
    public TextField userField;
    private Alert alerta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alerta = new Alert(Alert.AlertType.NONE);
        GmsLoginController.getSingleton().setActivity(this);
    }

    @FXML
    protected void login(ActionEvent actionEvent) {
        if (userField.getText().isEmpty() || passField.getText().isEmpty()) {
            alerta.setAlertType(Alert.AlertType.WARNING);
            alerta.setHeaderText("Los campos no pueden estar vacios.");
            alerta.setResult(ButtonType.OK);
            alerta.show();
            return;
        }
        GmsLoginController.getSingleton().getUser(
                userField.getText(),
                passField.getText(),
                actionEvent
        );
    }

    public void tryLogin(ActionEvent actionEvent) {
        User user = GmsLoginController.getSingleton().getDataUser();
        if (user == null) {
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setResult(ButtonType.OK);
            alerta.setTitle("ERROR EN EL LOGIN");
            alerta.setHeaderText("EL USUARIO NO EXISTE");
            alerta.show();
            return;
        }
        if (user.isLogged()) {
            Navigator.getSingleton().goMainMenu(actionEvent, this);
        } else {
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setResult(ButtonType.OK);
            alerta.setTitle("ERROR EN EL LOGIN");
            alerta.setHeaderText("EL USUARIO O LA CONTRASEÑA INTRODUCIDOS SON INCORRECTOS");
            alerta.show();
        }
    }
}
