package dev.marianof.gms2_app.Controller;

import dev.marianof.gms2_app.GmsLogin;
import dev.marianof.gms2_app.Model.User;
import javafx.event.ActionEvent;

public class GmsLoginController {
    private static GmsLoginController gmsLoginController;
    private static GmsLogin gmsLogin;
    private User user;

    private GmsLoginController() {

    }

    public static GmsLoginController getSingleton() {
        if (gmsLoginController == null)
            gmsLoginController = new GmsLoginController();
        return gmsLoginController;
    }

    public void getUser(String user, String pass, ActionEvent actionEvent) {
        Peticion p = new Peticion();
        p.getUser(user,pass, actionEvent);
    }

    public void setUserData(String body, ActionEvent actionEvent) {
        Respuesta r = new Respuesta();
        user = r.getUserData(body);
        gmsLogin.tryLogin(actionEvent);
    }

    public User getDataUser() {
        return this.user;
    }

    public void setActivity(GmsLogin gms) {
        gmsLogin = gms;
    }
}
