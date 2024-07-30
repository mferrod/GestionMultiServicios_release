package dev.marianof.gms2_app.Controller;

import javafx.scene.control.Alert;

public class AlertController {
    private static AlertController controller;
    private static Object activity;

    private AlertController() {

    }

    public static AlertController getSingleton() {
        if (controller == null)
            controller = new AlertController();
        return controller;
    }

    public void getAlert(String message, String title, Alert.AlertType tipoDeAlerta) {
        Alert alert = new Alert(tipoDeAlerta);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.show();
    }
}
