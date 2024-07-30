package dev.marianof.gms2_app;

import dev.marianof.gms2_app.Controller.AlertController;
import dev.marianof.gms2_app.Controller.GmsGestionMainController;
import dev.marianof.gms2_app.Controller.GmsGestionModificarController;
import dev.marianof.gms2_app.Controller.Navigator;
import dev.marianof.gms2_app.Model.Producto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class GmsGestionMain implements Initializable {
    @FXML
    private TableView<Producto> tabla;
    @FXML
    private TableColumn<Producto, String> nomProducto;
    @FXML
    private TableColumn<Producto, String> descProd;
    @FXML
    private TableColumn<Producto, String> precioProd;
    @FXML
    private TableColumn<Producto, String> cantidadProd;
    @FXML
    private Label nomProveLab;
    @FXML
    private Label cantidadProdLab;
    @FXML
    private Label precioProdLab;
    @FXML
    private Label descProdLab;
    @FXML
    private Label nomProdLab;
    @FXML
    private Button modificarProductoBtn;
    @FXML
    private Button hacerPedidoBtn;
    @FXML
    private Button salirBtn;
    private ObservableList<Producto> lista = FXCollections.observableArrayList();
    private Producto producto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GmsGestionMainController.getSingleton().setActivity(this);
        GmsGestionMainController.getSingleton().makePetition();
        nomProducto.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        descProd.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getPrecio())));
        precioProd.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getCantidad())));
        cantidadProd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombreProveedor()));
        tabla.setItems(lista);
    }

    @FXML
    public void modificarProducto(ActionEvent actionEvent) {
        if (producto == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DEBES SELECCIONAR UN PRODUCTO.");
            alert.show();
            return;
        }
        GmsGestionModificarController.getSingleton().setProducto(producto);
        Navigator.getSingleton().goGestionModificar(actionEvent, this);
    }
    @FXML
    public void hacerPedido(ActionEvent actionEvent) {
        Navigator.getSingleton().goGestionHacerPedido(actionEvent, this);
    }

    public void setDataToList(ObservableList<Producto> productos) {
        lista.clear();
        lista.addAll(productos);
    }

    @FXML
    public void mostrarDatos(MouseEvent mouseEvent) {
        TablePosition pos = null;
        try {
            pos = tabla.getSelectionModel().getSelectedCells().getFirst();
        } catch (Exception e) {
            AlertController.getSingleton().getAlert("NO HAS SELECCIONADO NADA", "ERROR", Alert.AlertType.ERROR);
            return;
        }
        int index = pos.getRow();
        int empiezo = 0;
        String selected = tabla.getItems().get(index).toString();
        String[] dataParsed = new String[5];
        for (int i = 0; i < 5; i++)
        {
            //System.out.println("" + i + "pos cursor");
            if (i == 4)
                dataParsed[i] = selected.substring(empiezo, selected.length());
            else
                dataParsed[i] = selected.substring(empiezo, selected.indexOf("$"));
            //System.out.println("" + empiezo + " " + selected.indexOf(","));
            empiezo = 0;
            //System.out.println("" + empiezo);
            selected = selected.substring(selected.indexOf("$") + 1, selected.length());
            //System.out.println(selected);
        }
        nomProdLab.setText(dataParsed[0]);
        descProdLab.setText(dataParsed[1]);
        precioProdLab.setText(dataParsed[2] + "€");
        cantidadProdLab.setText(dataParsed[3]);
        nomProveLab.setText(dataParsed[4]);
        producto = tabla.getItems().get(index);
    }

    @FXML
    public void salir(ActionEvent actionEvent) {
        Navigator.getSingleton().goMainMenu(actionEvent, this);
    }
}
