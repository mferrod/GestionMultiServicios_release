package dev.marianof.gestionmultiservicios.Controller;

import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.View.ModificarUsuarioActivity;

public class ModificarUserController {
    private static volatile ModificarUserController controller;
    private static ModificarUsuarioActivity myActivity;

    private User user;

    private ModificarUserController() {

    }

    public static ModificarUserController getSingleton() {
        if (controller == null)
            synchronized (ModificarUserController.class) {
                if (controller == null)
                    controller = new ModificarUserController();
            }
        return controller;
    }

    public void setMyActivity(ModificarUsuarioActivity activity) {
        myActivity = activity;
    }

    public void makePetitionToModify(User user) {
        Peticion peticion = new Peticion();
        peticion.modifyUser(user);
    }

    public void makePetitionToDelete(User user) {
        Peticion peticion = new Peticion();
        peticion.deleteUser(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
