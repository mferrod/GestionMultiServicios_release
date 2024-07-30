package dev.marianof.gms2_app;

import dev.marianof.gms2_app.Controller.AlertController;
import dev.marianof.gms2_app.Controller.GmsClientesModiaddController;
import dev.marianof.gms2_app.Controller.Navigator;
import dev.marianof.gms2_app.Model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GmsClientesModiadd implements Initializable {

    @FXML
    private Button salirBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Label txtLabel;
    @FXML
    private TextField editTextNombre;
    @FXML
    private TextField editTextApellidos;
    @FXML
    private TextField editTextEmail;
    @FXML
    private TextField editTextDNI;
    @FXML
    private TextField editTextDireccion;
    @FXML
    private TextField editTextTelefono;
    @FXML
    private Button guardarBtn;
    private Cliente cliente = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cliente = GmsClientesModiaddController.getSingleton().getCliente();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        if (cliente == null) {
            txtLabel.setText("AÑADIR CLIENTE");
            deleteBtn.setVisible(false);
            deleteBtn.setDisable(true);
        } else
            txtLabel.setText("MODIFICAR CLIENTE");
    }

    @FXML
    private void guardar(ActionEvent event) {
        if (editTextApellidos.getText().isEmpty() || editTextNombre.getText().isEmpty() ||
                editTextEmail.getText().isEmpty() || editTextDNI.getText().isEmpty()
        || editTextDireccion.getText().isEmpty() || editTextTelefono.getText().isEmpty()) {
            AlertController.getSingleton().getAlert("DEBES DE RELLENAR TODOS LOS CAMPOS.", "ERROR", Alert.AlertType.WARNING);
        } else {
            if (cliente == null)
                GmsClientesModiaddController.getSingleton().makePetitionAdd(new Cliente(
                        -1,
                        editTextNombre.getText(),
                        editTextApellidos.getText(),
                        editTextEmail.getText(),
                        editTextDNI.getText(),
                        editTextDireccion.getText(),
                        editTextTelefono.getText()
                ));
            else
                GmsClientesModiaddController.getSingleton().makePetitionModify(new Cliente(
                                cliente.idCliente(),
                                editTextNombre.getText(),
                                editTextApellidos.getText(),
                                editTextEmail.getText(),
                                editTextDNI.getText(),
                                editTextDireccion.getText(),
                                editTextTelefono.getText()
                        )
                );
        }
        Navigator.getSingleton().goClientesMain(event, this);
    }

    @FXML
    private void delete(ActionEvent event) {
        GmsClientesModiaddController.getSingleton().makePetitionDelete(cliente);
        Navigator.getSingleton().goClientesMain(event, this);
    }

    @FXML
    private void salir(ActionEvent event) {
        Navigator.getSingleton().goClientesMain(event, this);
    }
}
