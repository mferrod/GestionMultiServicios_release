package dev.marianof.gestionmultiservicios.Model;

public class Coordenadas {
    private final double latitud;
    private final double longitud;
    private final String nombreVeh;

    public Coordenadas(double latitud, double longitud, String nombreVeh) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombreVeh = nombreVeh;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getNombreVeh() {
        return nombreVeh;
    }
}
