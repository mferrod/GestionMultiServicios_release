package dev.marianof.gestionmultiservicios.Controller;

import dev.marianof.gestionmultiservicios.Model.Coordenadas;
import dev.marianof.gestionmultiservicios.View.GpsActivity;

public class GpsController {
    private static volatile GpsController controller;
    private static GpsActivity myActivity;

    private Coordenadas coordenadas;

    private GpsController() {

    }

    public static GpsController getSingleton() {
        if (controller == null)
            synchronized (GpsController.class) {
                if (controller == null)
                    controller = new GpsController();
            }
        return controller;
    }

    public void makePetition() {
        Peticion p = new Peticion();
        p.getPosition();
    }

    public void handleResponse(String json) {
        Respuesta respuesta = new Respuesta();
        coordenadas = respuesta.parsePosition(json);
        GpsController.myActivity.setPos();
    }

    public Coordenadas getCoordenadas() {
        return this.coordenadas;
    }

    public void setMyActivity(GpsActivity activity) {
        myActivity = activity;
    }
}
