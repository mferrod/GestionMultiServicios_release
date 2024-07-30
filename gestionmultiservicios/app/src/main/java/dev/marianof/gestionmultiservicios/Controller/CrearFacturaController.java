package dev.marianof.gestionmultiservicios.Controller;

import androidx.core.graphics.drawable.IconCompat;

import java.util.ArrayList;
import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.Model.PosicionesFactura;
import dev.marianof.gestionmultiservicios.Model.Producto;
import dev.marianof.gestionmultiservicios.View.CrearFacturaActivity;

public class CrearFacturaController {
    private static volatile CrearFacturaController controller;
    private static CrearFacturaActivity miActivity;
    private LinkedList<Producto> productos;
    private ArrayList<Cliente> clientes;
    private PosicionesFactura posicionesFactura;

    private CrearFacturaController() {

    }

    public static CrearFacturaController getSingleton() {
        if (controller == null)
            synchronized (CrearFacturaController.class) {
                if (controller == null)
                    controller = new CrearFacturaController();
            }
        return controller;
    }

    public void makePetitionToGetClientes() {
        Peticion p = new Peticion();
        p.getClientes(1);
    }

    public void setMiActivity(CrearFacturaActivity activity) {
        miActivity = activity;
    }

    public LinkedList<Producto> getProductos() {
        return this.productos;
    }

    public Factura getFacturaFromVerProd() {
        return VerProductosFacturaController.getSingleton().getFactura();
    }

    public void handleClientes(String s) {
        Respuesta respuesta = new Respuesta();
        clientes = respuesta.parseClientes(s);
        CrearFacturaController.miActivity.setupClientesSpinner();
    }

    public ArrayList<Cliente> getClientes() {
        return this.clientes;
    }

    public void makePetitionToUploadFactura(Factura factura) {
        Peticion p = new Peticion();
        p.addFactura(factura);
    }

    public void setPosicionesFactura(PosicionesFactura posicionesFactura) {
        this.posicionesFactura = posicionesFactura;
    }

    public PosicionesFactura getPosicionesFactura() {
        return this.posicionesFactura;
    }
}
