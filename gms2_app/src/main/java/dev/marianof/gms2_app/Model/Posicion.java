package dev.marianof.gms2_app.Model;

public class Posicion {
    private final double lat;
    private final double lon;

    public Posicion(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
