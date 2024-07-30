package dev.marianof.gms2_app.Controller;

import dev.marianof.gms2_app.GmsClientesMain;
import dev.marianof.gms2_app.Model.Cliente;

import java.util.ArrayList;

public class GmsClientesMainController {
    private static volatile GmsClientesMainController controller;
    private static GmsClientesMain myActivity;
    private ArrayList<Cliente> clientes;

    private GmsClientesMainController() {

    }

    public static GmsClientesMainController getSingleton() {
        if (controller == null)
            synchronized (GmsClientesMainController.class) {
                if (controller == null)
                    controller = new GmsClientesMainController();
            }
        return controller;
    }

    public void setMyActivity(GmsClientesMain gmsClientesMain) {
        myActivity = gmsClientesMain;
    }

    public void makePetition() {
        Peticion peticion = new Peticion();
        peticion.getClientes(3);
    }

    public void handleClientes(String string) {
        Respuesta respuesta = new Respuesta();
        clientes = respuesta.parseClientes(string);
        GmsClientesMainController.myActivity.setupListView();
    }

    public ArrayList<Cliente> getClientes() {
        return this.clientes;
    }
}
