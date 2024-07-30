package dev.marianof.gms2_app.Controller;


import dev.marianof.gms2_app.GmsFacturacionCrearFactura;
import dev.marianof.gms2_app.Model.Cliente;
import dev.marianof.gms2_app.Model.Factura;
import dev.marianof.gms2_app.Model.Producto;

import java.util.ArrayList;

public class GmsFacturacionCrearFacturaController {
    private static GmsFacturacionCrearFacturaController controller;
    private static GmsFacturacionCrearFactura myActivity;
    private ArrayList<Producto> productos;
    private ArrayList<Cliente> clientes;

    private GmsFacturacionCrearFacturaController () {

    }

    public static GmsFacturacionCrearFacturaController getSingleton() {
        if (controller == null)
            controller = new GmsFacturacionCrearFacturaController();
        return controller;
    }

    public void setMyActivity(GmsFacturacionCrearFactura activity) {
        myActivity = activity;
    }

    public void makePetitionClientes() {
        Peticion p = new Peticion();
        p.getClientes(1);
    }

    public void makePetitionProductos() {
        Peticion p = new Peticion();
        p.getProductos(3);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void handleClientes(String string) {
        Respuesta respuesta = new Respuesta();
        clientes = respuesta.parseClientes(string);
        GmsFacturacionCrearFacturaController.myActivity.initClienteCombo();
    }
    public void handleProductos(String string) {
        Respuesta respuesta = new Respuesta();
        productos = respuesta.parseProducts(string);
        GmsFacturacionCrearFacturaController.myActivity.initListView();
    }

    public ArrayList<Cliente> getClientes() {
        return this.clientes;
    }

    public void makePetitionUpFactura(Factura factura) {
        Peticion p = new Peticion();
        p.addFactura(factura);
    }
}
