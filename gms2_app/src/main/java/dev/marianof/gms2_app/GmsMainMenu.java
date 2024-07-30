package dev.marianof.gms2_app;

import dev.marianof.gms2_app.Controller.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GmsMainMenu {

    @FXML
    private Button facturacionBtn;
    @FXML
    private Button lclVehBtn;
    @FXML
    private Button gestionBtn;
    @FXML
    private Button clientesBtn;
    @FXML
    private Button logoutBtn;

    @FXML
    protected void lvlVehAction(ActionEvent event) {
        Navigator.getSingleton().goVehLocation(event, this);
    }

    @FXML
    protected void facturacionAction(ActionEvent event) {
        Navigator.getSingleton().goFacturacionMain(event, this);
    }

    @FXML
    protected void gestionAction(ActionEvent event) {
        Navigator.getSingleton().goGestionMain(event, this);
    }

    @FXML
    protected void logOutAction(ActionEvent event) { //Cerrar sesión
        Navigator.getSingleton().goLoginMain(event, this);
    }
    
    @FXML
    protected void clientesAction(ActionEvent event) {
        Navigator.getSingleton().goClientesMain(event, this);
    }
}
