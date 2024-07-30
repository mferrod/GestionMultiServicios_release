package dev.marianof.gms2_app.Controller;

import dev.marianof.gms2_app.GmsFacturacionVerFactura;
import dev.marianof.gms2_app.GmsLogin;
import dev.marianof.gms2_app.Model.Cliente;
import dev.marianof.gms2_app.Model.Factura;
import dev.marianof.gms2_app.Model.Producto;

import java.util.ArrayList;

public class GmsFacturacionVerFacturaController {
    private static GmsFacturacionVerFacturaController controller;
    private static GmsFacturacionVerFactura myActivity;
    private ArrayList<Producto> productos;
    private Factura factura = null;
    ArrayList<Cliente> clientes;

    private GmsFacturacionVerFacturaController() {
    }

    public static GmsFacturacionVerFacturaController getSingleton() {
        if (controller == null)
            controller = new GmsFacturacionVerFacturaController();
        return controller;
    }

    public void setMyActivity(GmsFacturacionVerFactura activity) {
        myActivity = activity;
    }
    public void makePetition() {
        Peticion p = new Peticion();
        p.getProductos(4);
    }
    public void handleProductos(String string) {
        Respuesta respuesta = new Respuesta();
        productos = respuesta.parseProducts(string);
        GmsFacturacionVerFacturaController.myActivity.initListView();
    }

    public ArrayList<Producto> getProductos() {
        return this.productos;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void makePetitionUpdateProductos(ArrayList<Producto> productos) {
        Peticion p = new Peticion();
        p.modifyProductos(productos);
    }
    public void makePetitionDeleteFactura(Factura factura) {
        Peticion p = new Peticion();
        p.deleteFactura(factura);
    }

    public void makePetitionModifyFactura(Factura factura) {
        Peticion p = new Peticion();
        p.modifyFactura(factura);
    }

    public void doPdf() {
        GmsPDFController.getSingleton().crearPDF(factura, GmsLoginController.getSingleton().getDataUser(), clientes);
    }

    public void handleClientes(String string) {
        Respuesta respuesta = new Respuesta();
        clientes = respuesta.parseClientes(string);
        GmsFacturacionVerFacturaController.myActivity.initClientesCombo();
    }

    public void makePetitionClientes() {
        Peticion p = new Peticion();
        p.getClientes(2);
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
}
