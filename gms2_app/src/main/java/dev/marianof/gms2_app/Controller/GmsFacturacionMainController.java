package dev.marianof.gms2_app.Controller;

import dev.marianof.gms2_app.GmsFacturacionMain;
import dev.marianof.gms2_app.Model.Factura;
import dev.marianof.gms2_app.Model.InfoFactura;

import java.sql.ResultSet;
import java.util.ArrayList;

public class GmsFacturacionMainController {
    private static GmsFacturacionMainController controller;
    private static GmsFacturacionMain activity;
    private ArrayList<Factura> facturas;
    private InfoFactura infoFactura;

    private GmsFacturacionMainController() {
        facturas = new ArrayList<>();
    }

    public static GmsFacturacionMainController getSingleton() {
        if (controller == null)
            controller = new GmsFacturacionMainController();
        return controller;
    }

    public void setActivity(GmsFacturacionMain gmsFacturacionMain) {
        activity = gmsFacturacionMain;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void makePetition() {
        Peticion p = new Peticion();
        p.getFacturas();
    }

    public void setFacturaData(String string) {
        Respuesta respuesta = new Respuesta();
        facturas = respuesta.parseFacturas(string);
        GmsFacturacionMainController.activity.initListView();
    }

    public void makePetitionForInfo(InfoFactura infoFactura) {
        Peticion p = new Peticion();
        p.getInfoFactura(infoFactura);
    }

    public void setInfoFacturaData(String string) {
        Respuesta respuesta = new Respuesta();
        infoFactura = respuesta.parseInfoFactura(string);
        GmsFacturacionMainController.activity.setInfoFactura();
    }
    public InfoFactura getInfoFactura() {
        return this.infoFactura;
    }
}
