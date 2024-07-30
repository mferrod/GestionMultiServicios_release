package com.marianof.gms2api.Model;

public class Proveedor {
    private final String id;
    private final String nombre;

    public Proveedor(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
