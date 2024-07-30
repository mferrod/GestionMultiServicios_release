package com.marianof.gms2api.Model;

public class Producto {
    private final String id;
    private final String nombre;
    private final String descripcion;
    private final Float precio;
    private final Integer cantidad;
    private final String idProveedor;
    private final String nombreProveedor;

    public Producto(String id, String nombre, String descripcion, Float precio, Integer cantidad, String idProveedor, String nombreProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

}
