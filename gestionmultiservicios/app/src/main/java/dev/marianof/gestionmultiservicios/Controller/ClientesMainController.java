package dev.marianof.gestionmultiservicios.Controller;

import java.util.ArrayList;
import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.View.ClientesMainActivity;

public class ClientesMainController {
    private static volatile ClientesMainController controller;
    private static ClientesMainActivity myActivity;
    private LinkedList<Cliente> clientes;

    private ClientesMainController() {
        clientes = new LinkedList<>();
    }

    public static ClientesMainController getSingleton() {
        if (controller == null)
            synchronized (ClientesMainController.class) {
                if (controller == null)
                    controller = new ClientesMainController();
            }
        return controller;
    }

    public void setMyActivity(ClientesMainActivity clientesMainActivity) {
        myActivity = clientesMainActivity;
    }

    public void makePetition() {
        Peticion p = new Peticion();
        p.getClientes();
    }

    public void handleClientes(String res) {
        Respuesta respuesta = new Respuesta();
        clientes = new LinkedList<>();
        ArrayList<Cliente> arrayList = respuesta.parseClientes(res);
        for (Cliente c:
             arrayList) {
            clientes.add(c);
        }
        ClientesMainController.myActivity.setup();
    }

    public LinkedList<Cliente> getClientes() {
        return clientes;
    }
}
