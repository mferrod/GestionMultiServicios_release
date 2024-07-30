package dev.marianof.gms2_app;

import dev.marianof.gms2_app.Controller.*;
import dev.marianof.gms2_app.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.RoundingMode;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GmsFacturacionCrearFactura implements Initializable {
    @FXML
    private Button salirBtn;
    @FXML
    private ComboBox<String> tipoFactuCombo;
    @FXML
    private ComboBox<String> clienteCombo;
    @FXML
    private ComboBox<String> usuarioCombo;
    @FXML
    private ListView<String> productosLView;
    @FXML
    private ListView<String> productosFactuLView;
    @FXML
    private Button addBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private Button crearFacturaBtn;
    @FXML
    private Label montoSinIVALabel;
    @FXML
    private Label montoConIVALabel;
    @FXML
    private TextField searchTField;
    private User usuario;
    private ArrayList<Producto> productos;
    private ArrayList<Producto> productosF;
    private ArrayList<Cliente> clientes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GmsFacturacionCrearFacturaController.getSingleton().setMyActivity(this);
        GmsFacturacionCrearFacturaController.getSingleton().makePetitionClientes();
        GmsFacturacionCrearFacturaController.getSingleton().makePetitionProductos();
        this.initUsuarioCombo();
        this.initTipoFactuCombo();
        productosF = new ArrayList<>();
        tipoFactuCombo.getSelectionModel().selectFirst();
        usuarioCombo.getSelectionModel().selectFirst();
        clienteCombo.getSelectionModel().selectFirst();
    }

    public void add(ActionEvent actionEvent) {
        int indexP = productosLView.getFocusModel().getFocusedIndex();
        Producto producto = productos.get(indexP);
        Producto productoF = null;
        int index = 0;
        if (!productosF.isEmpty() && pInList(producto)) {
            for (; productoF == null; index++)
                if (producto.getId().equals(productosF.get(index).getId())) {
                    productoF = productosF.get(index);
                    index--;
                }
        } else {
            productosF.add(
                    new Producto(
                            producto.getId(),
                            producto.getNombre(),
                            producto.getDescripcion(),
                            producto.getPrecio(),
                            0,
                            producto.getIdProveedor(),
                            producto.getNombreProveedor()
                    )
            );
            while (!productosF.get(index).getId().equals(producto.getId()))
                index++;
            productoF = productosF.get(index);
        }
        if (productosF.get(index).getId().equals(producto.getId())) {
            if (tipoFactuCombo.getSelectionModel().getSelectedItem().equals("Venta")) {
                if (producto.getCantidad() > 0) {
                    productos.get(indexP).setCantidad(producto.getCantidad() - 1);
                    productosF.get(index).setCantidad(productoF.getCantidad() + 1);
                }
                else
                    AlertController.getSingleton().getAlert(
                            "NO QUEDA STOCK EN EL ALMACÉN",
                            "ERROR",
                            Alert.AlertType.WARNING
                    );
            } else {
                productosF.get(index).setCantidad(productoF.getCantidad() + 1);
            }
        }
        this.initListViewF();
    }

    public void remove(ActionEvent actionEvent) {
        int indexP = productosLView.getFocusModel().getFocusedIndex();
        Producto producto = productos.get(indexP);
        Producto productoF = null;
        int index = 0;
        if (!productosF.isEmpty() && pInList(producto)) {
            for (; productoF == null; index++)
                if (producto.getId().equals(productosF.get(index).getId())) {
                    productoF = productosF.get(index);
                    index--;
                }
            if (productoF.getCantidad() - 1 <= 0) {
                productosF.remove(index);
                productos.get(indexP).setCantidad(producto.getCantidad() + 1);
            }
            else {
                productosF.get(index).setCantidad(productoF.getCantidad() - 1);
                productos.get(indexP).setCantidad(producto.getCantidad() + 1);
            }
        }
        this.initListViewF();
    }

    public void crearFactura(ActionEvent actionEvent) {
        GmsFacturacionCrearFacturaController.getSingleton().makePetitionUpFactura(
                new Factura(
                        null,
                        String.valueOf(usuario.getIdUser()),
                        String.valueOf(clientes.get(clienteCombo.getSelectionModel().getSelectedIndex()).idCliente()),
                        productosF,
                        Double.parseDouble(
                                montoConIVALabel.getText().substring(
                                        montoConIVALabel.getText().indexOf(": ") + 2,
                                        montoConIVALabel.getText().indexOf("€")
                                ).replace(',','.')
                        ),
                        tipoFactuCombo.getSelectionModel().getSelectedItem(),
                        new Date(Date.valueOf(LocalDate.now()).getTime()).toString(),
                        new Time(Time.valueOf(LocalTime.now()).getTime()).toString()
                )
        );
        GmsFacturacionVerFacturaController.getSingleton().makePetitionUpdateProductos(productos);
        Navigator.getSingleton().goFacturacionMain(actionEvent, this);
    }

    public void initListViewF() {
        ObservableList<String> lista = FXCollections.observableArrayList();
        for (Producto p : productosF)
            lista.add(p.getNombre() + " - " + p.getCantidad());
        this.productosFactuLView.setItems(lista);
        this.updateMonto();
    }

    private void updateMonto() {
        double precioSinIva = 0;
        for (Producto p: productosF)
            precioSinIva += (p.getCantidad() * p.getPrecio());
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        montoSinIVALabel.setText("Monto sin IVA: " + df.format(precioSinIva) + "€");
        montoConIVALabel.setText("Monto con IVA: " + df.format((precioSinIva + (precioSinIva * 0.21))) + "€");
    }

    public void initListView() {
        this.productos = GmsFacturacionCrearFacturaController.getSingleton().getProductos();
        ObservableList<String> lista = FXCollections.observableArrayList();
        for (Producto p : productos)
            lista.add(p.getNombre());
        this.productosLView.setItems(lista);
    }

    public void initClienteCombo() {
        clientes = GmsFacturacionCrearFacturaController.getSingleton().getClientes();
        ObservableList<String> lista = FXCollections.observableArrayList();
        for (Cliente c : clientes)
            lista.add(c.nombreCliente() + " " + c.apellidosCliente());
        this.clienteCombo.setItems(lista);
    }

    public void initUsuarioCombo() {
        usuario = GmsLoginController.getSingleton().getDataUser();
        ObservableList<String> lista = FXCollections.observableArrayList();
        lista.add(usuario.getUsername());
        this.usuarioCombo.setItems(lista);
    }

    public void initTipoFactuCombo() {
        ObservableList<String> lista = FXCollections.observableArrayList("Pedido", "Venta");
        this.tipoFactuCombo.setItems(lista);
    }

    @FXML
    public void changeFactura(ActionEvent event) {
        productosF = new ArrayList<>();
        GmsFacturacionCrearFacturaController.getSingleton().makePetitionProductos();
        this.initListViewF();
    }
    private boolean pInList(Producto producto) {
        if (productosF.isEmpty())
            return false;
        for (Producto p: productosF)
            if (p.getId().equals(producto.getId()))
                return true;
        return false;
    }

    @FXML
    private void salir(ActionEvent event) {
        Navigator.getSingleton().goFacturacionMain(event, this);
    }
}
