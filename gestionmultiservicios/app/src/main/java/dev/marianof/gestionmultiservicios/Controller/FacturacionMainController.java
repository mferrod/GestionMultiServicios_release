package dev.marianof.gestionmultiservicios.Controller;

import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.View.FacturacionMainActivity;

public class FacturacionMainController {
    private static volatile FacturacionMainController controller;
    private static FacturacionMainActivity myActivity;
    private LinkedList<Factura> facturas;
    private Factura factura;

    public static FacturacionMainController getSingleton() {
        if (controller == null)
            synchronized (FacturacionMainController.class) {
                if (controller == null)
                    controller = new FacturacionMainController();
            }
        return controller;
    }

    public void makePetition() {
        Peticion p = new Peticion();
        p.getFacturas();
    }

    public void handleResponse(String json) {
        Respuesta respuesta = new Respuesta();
        facturas = respuesta.parseFacturas(json);
        FacturacionMainController.myActivity.setup();
    }

    public void setActivity(FacturacionMainActivity facturacionMainActivity) {
        myActivity = facturacionMainActivity;
    }

    public LinkedList<Factura> getFacturas() {
        return facturas;
    }
    public void setFacturaOnController(Factura factura) {
        this.factura = factura;
    }

    public Factura getFactura() {
        return this.factura;
    }
}
