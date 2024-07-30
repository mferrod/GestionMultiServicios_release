package dev.marianof.gms2_app.Controller;

import dev.marianof.gms2_app.GmsClientesModiadd;
import dev.marianof.gms2_app.Model.Cliente;

public class GmsClientesModiaddController {
    private static volatile GmsClientesModiaddController controller;
    private static GmsClientesModiadd myActivity;
    private Cliente cliente;

    private GmsClientesModiaddController() {

    }

    public static GmsClientesModiaddController getSingleton() {
        if (controller == null)
            synchronized (GmsClientesModiaddController.class) {
                if (controller == null)
                    controller = new GmsClientesModiaddController();
            }
        return controller;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void makePetitionAdd(Cliente cliente) {
        Peticion p = new Peticion();
        p.addCliente(cliente);
    }

    public void makePetitionModify(Cliente cliente) {
        Peticion p = new Peticion();
        p.modifyCliente(cliente);
    }

    public void makePetitionDelete(Cliente cliente) {
        Peticion p = new Peticion();
        p.deleteCliente(cliente);
    }
}
