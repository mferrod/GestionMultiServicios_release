package dev.marianof.gms2_app.Controller;

import dev.marianof.gms2_app.GmsGestionMain;
import dev.marianof.gms2_app.Model.Producto;
import dev.marianof.gms2_app.Model.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class GmsGestionMainController {
    private static GmsGestionMainController controller;
    private static GmsGestionMain myActivity;
    private ObservableList<Producto> lista = FXCollections.observableArrayList();

    private GmsGestionMainController() {
    }

    public static GmsGestionMainController getSingleton() {
        if (controller == null)
            controller = new GmsGestionMainController();
        return controller;
    }

    public void setActivity(GmsGestionMain activity) {
        myActivity = activity;
    }

    public void makePetition() {
        Peticion p = new Peticion();
        p.getProductos(1);
    }

    public void setProductsData(String json) {
        Respuesta respuesta = new Respuesta();
        lista.clear();
        lista.addAll(respuesta.parseProducts(json));
        myActivity.setDataToList(lista);
    }
}
