package dev.marianof.gestionmultiservicios.Controller;

import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.View.ModificarClienteActivity;
import dev.marianof.gestionmultiservicios.View.ModificarUsuarioActivity;

public class ModificarClienteController {
    private static volatile ModificarClienteController controller;
    private static ModificarClienteActivity myActivity;

    private Cliente cliente;

    private ModificarClienteController() {

    }

    public static ModificarClienteController getSingleton() {
        if (controller == null)
            synchronized (ModificarUserController.class) {
                if (controller == null)
                    controller = new ModificarClienteController();
            }
        return controller;
    }

    public void setMyActivity(ModificarClienteActivity activity) {
        myActivity = activity;
    }

    public void makePetitionToModify(Cliente cliente) {
        Peticion peticion = new Peticion();
        peticion.modifyCliente(cliente);
    }

    public void makePetitionToDelete(Cliente cliente) {
        Peticion peticion = new Peticion();
        peticion.deleteCliente(cliente);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
