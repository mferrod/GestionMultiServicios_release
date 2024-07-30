package dev.marianof.gms2_app.Controller;

import com.squareup.okhttp.Response;
import dev.marianof.gms2_app.GmsGestionModificar;
import dev.marianof.gms2_app.Model.Producto;
import dev.marianof.gms2_app.Model.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class GmsGestionModificarController {
    private static GmsGestionModificarController controller;
    private static GmsGestionModificar myActivity;
    private Producto miProducto;
    ArrayList<Proveedor> arrayList = new ArrayList<>();

    private GmsGestionModificarController() {

    }

    public static GmsGestionModificarController getSingleton() {
        if (controller == null)
            controller = new GmsGestionModificarController();
        return controller;
    }
    public void setMyActivity(GmsGestionModificar activity) {
        myActivity = activity;
    }

    public void setProducto(Producto producto) {
        miProducto = producto;
    }

    public Producto getProducto() {
        return miProducto;
    }

    public void makePetition(Producto producto) {
        Peticion p = new Peticion();
        p.modifyProducto(producto);
    }

    public void makePetitionProveedores() {
        Peticion peticion = new Peticion();
        peticion.getProveedores(1);
    }

    public void setProveedoresData(String string) {
        Respuesta respuesta = new Respuesta();
        arrayList = respuesta.getProveedoresData(string);
        GmsGestionModificarController.myActivity.setProveedores();
    }
    public ArrayList<Proveedor> getProveedores() {
        return this.arrayList;
    }
}
