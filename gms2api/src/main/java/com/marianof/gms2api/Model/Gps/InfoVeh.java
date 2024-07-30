package com.marianof.gms2api.Model.Gps;

public class InfoVeh {
    private String nombre;
    private Double latitud;
    private Double longitud;

    public InfoVeh(String nombre, Double latitud, Double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "{" + "nombreVeh=" + nombre + ","
                + "latitudVeh=" + latitud + ","
                + "longitudVeh=" + longitud + "}";
    }
}
