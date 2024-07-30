package dev.marianof.gms2_app;

import dev.marianof.gms2_app.Controller.AlertController;
import dev.marianof.gms2_app.Controller.GmsFacturacionMainController;
import dev.marianof.gms2_app.Controller.GmsFacturacionVerFacturaController;
import dev.marianof.gms2_app.Controller.Navigator;
import dev.marianof.gms2_app.Model.Factura;
import dev.marianof.gms2_app.Model.InfoFactura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GmsFacturacionMain implements Initializable {
    @FXML
    public ListView<String> lvFacturas;
    @FXML
    public Button btnEditarFactura;
    @FXML
    public Button btnSalirFactura;
    @FXML
    public Label lblInfoFactura;
    @FXML
    public Button btnCrearFactura;
    private ObservableList<String> lista = FXCollections.observableArrayList();
    private ArrayList<Factura> facturas;
    private InfoFactura infoFactura;
    private Factura factura;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GmsFacturacionMainController.getSingleton().setActivity(this);
        GmsFacturacionMainController.getSingleton().makePetition();
        lvFacturas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int pos = lvFacturas.getFocusModel().getFocusedIndex();
                factura = facturas.get(pos);
                InfoFactura infoSended = new InfoFactura(Integer.parseInt(factura.getIdCliente()),null,Integer.parseInt(factura.getIdUsuario()),null);
                GmsFacturacionMainController.getSingleton().makePetitionForInfo(infoSended);
            }
        });
    }

    public void initListView() {
        facturas = GmsFacturacionMainController.getSingleton().getFacturas();
        for (Factura f : facturas)
            lista.add("#FACT-"
                    + f.getIdFactura()
                    + f.getFechaCreacion().replace("-","")
            );
        lvFacturas.setItems(lista);
    }

    @FXML
    public void seleccionarFactura(ActionEvent event) {
        int i = lvFacturas.getFocusModel().getFocusedIndex();
        GmsFacturacionVerFacturaController.getSingleton().setFactura(facturas.get(i));
        Navigator.getSingleton().goFacturacionVerFactura(event, this);

    }
    @FXML
    public void salir(ActionEvent actionEvent) {
        Navigator.getSingleton().goMainMenu(actionEvent, this);
    }

    public void setInfoFactura() {
        infoFactura = GmsFacturacionMainController.getSingleton().getInfoFactura();
        String infoFacturaLb = null;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        infoFacturaLb = "Cliente: " + infoFactura.getNombreCliente()
                + "\nUsuario: " + infoFactura.getNombreUsuario()
                + "\nMonto factura: " + df.format(factura.getMontoFactura())
                + "€\nFecha factura: " + factura.getFechaCreacion()
                + "\nHora factura: " + factura.getHoraCreacion();
        lblInfoFactura.setText(infoFacturaLb);
    }
    @FXML
    public void crearFactura(ActionEvent actionEvent) {
        Navigator.getSingleton().goFacturacionCrearFactura(actionEvent, this);
    }
}
