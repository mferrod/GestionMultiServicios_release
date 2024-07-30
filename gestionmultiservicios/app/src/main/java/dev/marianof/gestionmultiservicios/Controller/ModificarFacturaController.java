package dev.marianof.gestionmultiservicios.Controller;

import java.util.ArrayList;

import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.View.ModificarFacturaActivity;

public class ModificarFacturaController {
    private static volatile ModificarFacturaController controller;
    private static ModificarFacturaActivity myActivity;
    private Factura factura;
    private ArrayList<Cliente> clientes;
    private boolean changed;

    private ModificarFacturaController() {
        changed = false;
    }

    public static ModificarFacturaController getSingleton() {
        if (controller == null)
            synchronized (ModificarFacturaController.class) {
                if (controller == null)
                    controller = new ModificarFacturaController();
            }
        return controller;
    }

    public void setMyActivity(ModificarFacturaActivity activity) {
        myActivity = activity;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Factura getFactura() {
        return factura;
    }

    public ArrayList<Cliente> getClientes() {
        return this.clientes;
    }
    public void handleClientes(String s) {
        Respuesta respuesta = new Respuesta();
        clientes = respuesta.parseClientes(s);
        ModificarFacturaController.myActivity.setupClientesSpinner();
    }

    public void makePetitionToGetClientes() {
        Peticion p = new Peticion();
        p.getClientes(2);
    }

    public void makePetitionModifyFactura(Factura factura) {
        Peticion p = new Peticion();
        p.modifyFactura(factura);
    }

    public void makePetitionDeleteFactura(Factura factura) {
        Peticion p = new Peticion();
        p.deleteFactura(factura);
    }

    public void setChanged(boolean b) {
        this.changed = b;
    }

    public boolean isChanged() {
        return changed;
    }
}
