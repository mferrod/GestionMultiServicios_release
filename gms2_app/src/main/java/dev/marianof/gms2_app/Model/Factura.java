package dev.marianof.gms2_app.Model;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;

public class Factura {
    private final String idFactura;
    private final String idUsuario;
    private final String idCliente;
    private final ArrayList<Producto> listaProductos;
    private final double montoFactura;
    private final String tipoFactura;
    private final String fechaCreacion;
    private final String horaCreacion;

    public Factura(String idFactura, String idUsuario, String idCliente, ArrayList<Producto> listaProductos, double montoFactura, String tipoFactura, String fechaCreacion, String horaCreacion) {
        this.idFactura = idFactura;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.listaProductos = listaProductos;
        this.montoFactura = montoFactura;
        this.tipoFactura = tipoFactura;
        this.fechaCreacion = fechaCreacion;
        this.horaCreacion = horaCreacion;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public double getMontoFactura() {
        return montoFactura;
    }

    public String getHoraCreacion() {
        return horaCreacion;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }
}
