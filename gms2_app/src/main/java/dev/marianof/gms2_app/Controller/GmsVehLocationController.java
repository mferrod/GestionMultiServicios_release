package dev.marianof.gms2_app.Controller;

import dev.marianof.gms2_app.GmsVehLocation;
import dev.marianof.gms2_app.Model.Posicion;

public class GmsVehLocationController {
    private static GmsVehLocationController controller;
    private static GmsVehLocation myActivity;
    private Posicion posicion;

    private GmsVehLocationController() {

    }

    public static GmsVehLocationController getSingleton() {
        if (controller == null)
            controller = new GmsVehLocationController();
        return controller;
    }

    public void setMyActivity(GmsVehLocation activity) {
        myActivity = activity;
    }

    public void makePetition() {
        Peticion p = new Peticion();
        p.getLocalizacion();
    }

    public void handleResponse(String string) {
        Respuesta r = new Respuesta();
        posicion = r.parsePosition(string);
        GmsVehLocationController.myActivity.setPos();
    }

    public Posicion getPosicion() {
        return this.posicion;
    }
}
