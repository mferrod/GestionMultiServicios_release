package dev.marianof.gestionmultiservicios.Controller;

import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.View.CrearClienteActivity;

public class CrearUsersController {
    private static volatile CrearUsersController controller;

    private CrearUsersController() {

    }

    public static CrearUsersController getSingleton() {
        if (controller == null)
            synchronized (CrearUsersController.class) {
                if (controller == null)
                    controller = new CrearUsersController();
            }
        return controller;
    }

    public void makePetition(User user) {
        Peticion p = new Peticion();
        p.addUser(user);
    }
}
