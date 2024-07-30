package dev.marianof.gestionmultiservicios.Controller;

import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.View.UserMainActivity;

public class UsersMainController {
    private static volatile UsersMainController controller;
    private static UserMainActivity myActivity;
    private LinkedList<User> users;

    private UsersMainController() {

    }

    public static UsersMainController getSingleton() {
        if (controller == null)
            synchronized (UsersMainController.class) {
                if (controller == null)
                    controller = new UsersMainController();
            }
        return controller;
    }

    public void setMyActivity(UserMainActivity activity) {
        myActivity = activity;
    }

    public void makePetition() {
        Peticion p = new Peticion();
        p.getUsuarios();
    }

    public void handleUsuarios(String res) {
        Respuesta respuesta = new Respuesta();
        users = respuesta.parseUsers(res);
        UsersMainController.myActivity.setup();
    }

    public LinkedList<User> getUsers() {
        return users;
    }
}
