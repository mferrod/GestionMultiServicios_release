package dev.marianof.gestionmultiservicios.Controller;

import java.util.ResourceBundle;

import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.View.LoginActivity;

public class LoginController {
    private static volatile LoginController controller;
    private static LoginActivity myActivity;
    private User usuario;

    private LoginController() {

    }

    public static LoginController getSingleton() {
        if (controller == null)
            synchronized (LoginController.class) {
                if (controller == null)
                    controller = new LoginController();
            }
        return controller;
    }

    public void setActivity(LoginActivity activity) {
        myActivity = activity;
    }

    public void makePetition(String user, String pass) {
        Peticion p = new Peticion();
        p.getLogin(user, pass);
    }

    public void handleResponse(String res) {
        Respuesta respuesta = new Respuesta();
        usuario = respuesta.parseUser(res);
        LoginController.myActivity.login();
    }

    public User getUsuario() {
        return usuario;
    }
}
