package dev.marianof.gestionmultiservicios.Controller;

import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.View.CrearClienteActivity;

public class CrearClientesController {
    private static volatile CrearClientesController controller;

    private CrearClientesController() {

    }

    public static CrearClientesController getSingleton() {
        if (controller == null)
            synchronized (CrearClientesController.class) {
                if (controller == null)
                    controller = new CrearClientesController();
            }
        return controller;
    }

    public void makePetition(Cliente cliente) {
        Peticion p = new Peticion();
        p.addCliente(cliente);
    }
}
