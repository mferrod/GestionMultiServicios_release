package dev.marianof.gms2_app;

import dev.marianof.gms2_app.Controller.GmsClientesMainController;
import dev.marianof.gms2_app.Controller.GmsClientesModiaddController;
import dev.marianof.gms2_app.Controller.GmsFacturacionMainController;
import dev.marianof.gms2_app.Controller.Navigator;
import dev.marianof.gms2_app.Model.Cliente;
import dev.marianof.gms2_app.Model.Factura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GmsClientesMain implements Initializable {


    @FXML
    private ListView lvClientes;
    @FXML
    private Label txtSobreClienteLabel;
    @FXML
    private Button addClienteBtn;
    @FXML
    private Button modifyClienteBtn;
    @FXML
    private Button salirBtn;
    private ObservableList<String> lista = FXCollections.observableArrayList();
    private ArrayList<Cliente> clientes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GmsClientesMainController.getSingleton().setMyActivity(this);
        GmsClientesMainController.getSingleton().makePetition();
        lvClientes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int i = lvClientes.getFocusModel().getFocusedIndex();
                setInfoFactura(clientes.get(i));
            }
        });
    }

    public void setupListView() {
        clientes = GmsClientesMainController.getSingleton().getClientes();
        for (Cliente c : clientes)
            lista.add(
                    c.nombreCliente() +
                    " " +
                    c.apellidosCliente()
            );
        lvClientes.setItems(lista);
    }

    private void setInfoFactura(Cliente c) {
        String infoFacturaLb = null;
        infoFacturaLb = "Nombre Cliente: " + c.nombreCliente()
                + "\nApellidos Cliente: " + c.apellidosCliente()
                + "\nEmail Cliente: " + c.emailCliente()
                + "\nDNI/NIF Cliente: " + c.dniCliente()
                + "\nDirección Cliente: " + c.direccionCliente()
                + "\nTelefono Cliente: " + c.telefonoCliente();
        txtSobreClienteLabel.setText(infoFacturaLb);
    }

    @FXML
    private void addCliente(ActionEvent event) {
        GmsClientesModiaddController.getSingleton().setCliente(null);
        Navigator.getSingleton().goClientesModyadd(event, this);
    }
    @FXML
    private void modificarCliente(ActionEvent event) {
        int i = lvClientes.getFocusModel().getFocusedIndex();
        GmsClientesModiaddController.getSingleton().setCliente(clientes.get(i));
        Navigator.getSingleton().goClientesModyadd(event, this);
    }

    @FXML
    private void salirBtn(ActionEvent event) {
        Navigator.getSingleton().goMainMenu(event, this);
    }
}
