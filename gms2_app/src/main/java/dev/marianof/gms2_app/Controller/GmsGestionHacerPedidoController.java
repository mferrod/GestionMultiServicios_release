package dev.marianof.gms2_app.Controller;

import dev.marianof.gms2_app.GmsGestionHacerPedido;
import dev.marianof.gms2_app.Model.Producto;
import dev.marianof.gms2_app.Model.Proveedor;

import java.util.ArrayList;

public class GmsGestionHacerPedidoController {
    private static GmsGestionHacerPedidoController controller;
    private static GmsGestionHacerPedido myActivity;
    private ArrayList<Proveedor> arrayList;
    private ArrayList<Producto> arrayListP;


    private GmsGestionHacerPedidoController() {
        arrayList = new ArrayList<>();
        arrayListP = new ArrayList<>();
    }

    public static GmsGestionHacerPedidoController getSingleton() {
        if (controller == null)
            controller = new GmsGestionHacerPedidoController();
        return controller;
    }

    public void makePetition() {
        Peticion p = new Peticion();
        p.getProveedores(2);
        p.getProductos(2);
    }

    public void setProveedoresData(String string) {
        Respuesta respuesta = new Respuesta();
        arrayList = respuesta.getProveedoresData(string);
        GmsGestionHacerPedidoController.myActivity.setProveedores();
    }

    public void setProductosData(String string) {
        Respuesta respuesta = new Respuesta();
        arrayListP = respuesta.parseProducts(string);
        GmsGestionHacerPedidoController.myActivity.setProductoProveedor();
    }

    public ArrayList<Proveedor> getProveedores() {
        return this.arrayList;
    }

    public ArrayList<Producto> getProductos() {
        return this.arrayListP;
    }

    public void setActivity(GmsGestionHacerPedido gmsGestionHacerPedido) {
        myActivity = gmsGestionHacerPedido;
    }

    public void makePetitionPedido(ArrayList<Producto> productosPedido) {
        Peticion p = new Peticion();
        p.makePedido(productosPedido);
    }
}
