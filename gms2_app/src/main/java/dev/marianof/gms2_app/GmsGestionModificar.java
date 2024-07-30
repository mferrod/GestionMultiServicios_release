package dev.marianof.gms2_app;

import dev.marianof.gms2_app.Controller.GmsGestionModificarController;
import dev.marianof.gms2_app.Controller.Navigator;
import dev.marianof.gms2_app.Model.Producto;
import dev.marianof.gms2_app.Model.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GmsGestionModificar implements Initializable {
    @FXML
    private TextField nomProdTF;
    @FXML
    private TextField descProdTF;
    @FXML
    private TextField precioProdTF;
    @FXML
    private TextField cantidadProdTF;
    @FXML
    private Button modificarProdBtn;
    @FXML
    public Button salirBtn;
    @FXML
    private ListView<String> proveedorProdCB = new ListView<>();
    private Producto producto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GmsGestionModificarController.getSingleton().setMyActivity(this);
        GmsGestionModificarController.getSingleton().makePetitionProveedores();
        producto = GmsGestionModificarController.getSingleton().getProducto();
        nomProdTF.setText(producto.getNombre());
        descProdTF.setText(producto.getDescripcion());
        precioProdTF.setText(String.valueOf(producto.getPrecio()));
        cantidadProdTF.setText(String.valueOf(producto.getCantidad()));
    }

    @FXML
    public void modificarProducto(ActionEvent actionEvent) {
        producto = new Producto(
                this.producto.getId(),
                nomProdTF.getText(),
                descProdTF.getText(),
                Double.parseDouble(precioProdTF.getText()),
                Integer.parseInt(cantidadProdTF.getText()),
                String.valueOf(this.proveedorProdCB.getFocusModel().getFocusedIndex() + 1),
                this.producto.getNombreProveedor());
        GmsGestionModificarController.getSingleton().makePetition(producto);
        Navigator.getSingleton().goGestionMain(actionEvent, this);
    }

    @FXML
    public void setProveedores() {
        ArrayList<Proveedor> a = GmsGestionModificarController.getSingleton().getProveedores();
        ArrayList<String> s = new ArrayList<>();
        for (Proveedor p: a) s.add(p.getNombre());
        ObservableList<String> lista = FXCollections.observableList(s);
        proveedorProdCB.setItems(lista);
    }

    public void salir(ActionEvent actionEvent) {
        Navigator.getSingleton().goGestionMain(actionEvent, this);
    }
}
