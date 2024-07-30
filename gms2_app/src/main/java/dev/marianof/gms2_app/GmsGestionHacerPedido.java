package dev.marianof.gms2_app;

import dev.marianof.gms2_app.Controller.GmsGestionHacerPedidoController;
import dev.marianof.gms2_app.Controller.GmsGestionModificarController;
import dev.marianof.gms2_app.Controller.Navigator;
import dev.marianof.gms2_app.Model.Producto;
import dev.marianof.gms2_app.Model.Proveedor;
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

import java.awt.color.ProfileDataException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GmsGestionHacerPedido implements Initializable {
    public ListView<String> lvProveedores;
    public ListView<String> lvProdProveedor;
    public Button addItemBtn;
    public Button rmvItemBtn;
    public Button completerPedidoBtn;
    public Label textoProducto;
    public Label textoProductoOrig;
    private ArrayList<Producto> productos;
    private ObservableList<String> listaProd;
    private HashMap<String, Integer> hashMap; //SE RECOJE LA ID DEL PRODUCTO Y LA CANTIDAD DE PRODUCTOS A PEDIR
    private Producto producto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productos = new ArrayList<>();
        listaProd = FXCollections.observableArrayList();
        hashMap = new HashMap<>();
        GmsGestionHacerPedidoController.getSingleton().setActivity(this);
        GmsGestionHacerPedidoController.getSingleton().makePetition();
        lvProveedores.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                listaProd.clear();
                textoProducto.setText("");
                textoProductoOrig.setText("");
                int i = lvProveedores.getFocusModel().getFocusedIndex() + 1;
                for (Producto p : productos)
                    if (Integer.parseInt(p.getIdProveedor()) == i)
                        listaProd.add(p.getNombre());
                lvProdProveedor.setItems(listaProd);
            }
        });
        lvProdProveedor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (lvProveedores.getFocusModel() == null)
                    return;
                for (Producto p: productos)
                    if (p.getNombre().equals(lvProdProveedor.getFocusModel().getFocusedItem()))
                        producto = p;
                actualizatexto();
            }
        });
    }

    @FXML
    private void actualizatexto() {
        textoProducto.setText(String.valueOf("Producto: " + producto.getNombre()
                + "\n Cantidad actual en pedido: "
                + hashMap.getOrDefault(producto.getId(), 0)));
        textoProductoOrig.setText("Producto: " + producto.getNombre()
                + "\n Cantidad en almacén: " + producto.getCantidad());
    }

    @FXML
    public void addItem(ActionEvent actionEvent) {
        int i;
        if (lvProdProveedor.getFocusModel() == null)
            return;
        if (hashMap.get(producto.getId()) != null)
            i = hashMap.get(producto.getId());
        else
            i = 0;
        hashMap.put(producto.getId(), i + 1);
        actualizatexto();
    }
    @FXML
    public void removeItem(ActionEvent actionEvent) {
        int i;
        if (lvProdProveedor.getFocusModel() == null)
            return;
        if (hashMap.get(producto.getId()) != null)
            i = hashMap.get(producto.getId());
        else
            i = 0;
        hashMap.put(producto.getId(), i - 1);
        actualizatexto();
    }

    @FXML
    public void completarPedido(ActionEvent actionEvent) {
        ArrayList<Producto> productosPedido = new ArrayList<>();
        for (Producto p: productos) {
            if (hashMap.get(p.getId()) != null) {
                p.setCantidad(
                        p.getCantidad() + hashMap.get(p.getId()
                ));
                productosPedido.add(p);
            }
        }
        if (!productosPedido.isEmpty())
            GmsGestionHacerPedidoController.getSingleton().makePetitionPedido(productosPedido);
        Navigator.getSingleton().goGestionMain(actionEvent, this);
    }

    @FXML
    public void setProveedores() {
        ArrayList<Proveedor> a = GmsGestionHacerPedidoController.getSingleton().getProveedores();
        ArrayList<String> s = new ArrayList<>();
        for (Proveedor p: a) s.add(p.getNombre());
        ObservableList<String> lista = FXCollections.observableList(s);
        lvProveedores.setItems(lista);
    }

    public void setProductoProveedor() {
        productos = GmsGestionHacerPedidoController.getSingleton().getProductos();
    }
}
