package dev.marianof.gestionmultiservicios.Controller;

import java.util.LinkedList;
import java.util.Objects;

import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.Model.Producto;
import dev.marianof.gestionmultiservicios.View.CrearFacturaActivity;
import dev.marianof.gestionmultiservicios.View.FacturacionMainActivity;
import dev.marianof.gestionmultiservicios.View.VerProductosFactura;

public class VerProductosFacturaController {
    private static volatile VerProductosFacturaController controller;
    private static VerProductosFactura miActivity;
    private LinkedList<Producto> productos;
    private String tipoFactu;
    private Factura factura;

    private Object activity;

    private VerProductosFacturaController() {

    }

    public static VerProductosFacturaController getSingleton() {
        if (controller == null)
            synchronized (VerProductosFacturaController.class) {
                if (controller == null)
                    controller = new VerProductosFacturaController();
            }
        return controller;
    }

    public void makePetition() {
        Peticion p = new Peticion();
        p.getProductos();
    }

    public void handleProductos(String json) {
        Respuesta respuesta = new Respuesta();
        productos = respuesta.parseProductos(json);
        VerProductosFacturaController.miActivity.setup();
    }

    public void setMiActivity(VerProductosFactura activity) {
        miActivity = activity;
    }

    public LinkedList<Producto> getProductos() {
        return this.productos;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void makePetitionUploadProductos(LinkedList<Producto> mList) {
        Peticion p = new Peticion();
        p.modifyProductos(mList);
    }

    public Object getActivityToChange() {
        return this.activity;
    }

    public void setActivityToChange(Object activity) {
        this.activity = activity;
    }
}
