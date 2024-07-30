package dev.marianof.gms2_app;

import dev.marianof.gms2_app.Controller.*;
import dev.marianof.gms2_app.Model.Cliente;
import dev.marianof.gms2_app.Model.Factura;
import dev.marianof.gms2_app.Model.Producto;
import dev.marianof.gms2_app.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GmsFacturacionVerFactura implements Initializable {
    @FXML
    private Button eliminarFacturaBtn;
    @FXML
    private Button hacerPdfBtn;
    @FXML
    private ListView<String> productosLView;
    @FXML
    private ListView<String> productosFactuLView;
    @FXML
    private Button addBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private Label montoSinIVALabel;
    @FXML
    private Label montoConIVALabel;
    @FXML
    private ComboBox<String> usuarioCombo;
    private Button crearFacturaBtn;
    @FXML
    private TextField searchTField;
    @FXML
    private Button searchBtn;
    @FXML
    private ComboBox<String> tipoFactuCombo;
    @FXML
    private Button salirBtn;
    @FXML
    private Button modificarFacturaBtn;
    @FXML
    private ComboBox<String> clienteCombo;
    private User usuario;
    private ArrayList<Producto> productos;
    private ArrayList<Producto> productosF;
    private ArrayList<Cliente> clientes;
    private Factura factura;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GmsFacturacionVerFacturaController.getSingleton().setMyActivity(this);
        GmsFacturacionVerFacturaController.getSingleton().makePetition();
        GmsFacturacionVerFacturaController.getSingleton().makePetitionClientes();
        this.initTipoFactuCombo();
        this.initListViewF();
        this.initUsuarioCombo();
        factura = GmsFacturacionVerFacturaController.getSingleton().getFactura();
    }

    public void initTipoFactuCombo() {
        ObservableList<String> lista = FXCollections.observableArrayList("Pedido", "Venta");
        this.tipoFactuCombo.setItems(lista);
        this.tipoFactuCombo.getSelectionModel().selectFirst();
    }

    private void initUsuarioCombo() {
        usuario = GmsLoginController.getSingleton().getDataUser();
        ObservableList<String> list = FXCollections.observableArrayList(usuario.getUsername());
        this.usuarioCombo.setItems(list);
        this.usuarioCombo.getSelectionModel().selectFirst();
    }

    public void initListView() {
        this.productos = GmsFacturacionVerFacturaController.getSingleton().getProductos();
        ObservableList<String> lista = FXCollections.observableArrayList();
        for (Producto p : productos)
            lista.add(p.getNombre());
        this.productosLView.setItems(lista);
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

    public void salir(ActionEvent actionEvent) {
        Navigator.getSingleton().goFacturacionMain(actionEvent, this);
    }

    public void initListViewF() {
        Factura f = GmsFacturacionVerFacturaController.getSingleton().getFactura();
        productosF = f.getListaProductos();
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
        montoSinIVALabel.setText("Monto sin IVA: " + df.format(precioSinIva - (precioSinIva * 0.21)) + "€");
        montoConIVALabel.setText("Monto con IVA: " + df.format((precioSinIva)) + "€");
    }
    private boolean pInList(Producto producto) {
        if (productosF.isEmpty())
            return false;
        for (Producto p: productosF)
            if (p.getId().equals(producto.getId()))
                return true;
        return false;
    }

    public void changeFactura(ActionEvent actionEvent) {


    }

    public void modificarFactura(ActionEvent actionEvent) {
        GmsFacturacionVerFacturaController.getSingleton().makePetitionModifyFactura(factura);
        GmsFacturacionVerFacturaController.getSingleton().makePetitionUpdateProductos(productos);
        Navigator.getSingleton().goFacturacionMain(actionEvent, this);
    }

    public void hacerPdf(ActionEvent event) {
        GmsFacturacionVerFacturaController.getSingleton().doPdf();
    }

    public void eliminarFactura(ActionEvent event) {
        GmsFacturacionVerFacturaController.getSingleton().makePetitionDeleteFactura(factura);
        Navigator.getSingleton().goFacturacionMain(event, this);
    }

    public void initClientesCombo() {
        clientes = GmsFacturacionVerFacturaController.getSingleton().getClientes();
        Cliente cliente = null;
        for (Cliente c: clientes)
            if (c.idCliente() == Integer.parseInt(factura.getIdCliente()))
                        cliente = c;
        ObservableList<String> clienteOb = FXCollections.observableArrayList(cliente.nombreCliente() + " " + cliente.apellidosCliente());
        this.clienteCombo.setItems(clienteOb);
        this.clienteCombo.getSelectionModel().selectFirst();
    }
}
