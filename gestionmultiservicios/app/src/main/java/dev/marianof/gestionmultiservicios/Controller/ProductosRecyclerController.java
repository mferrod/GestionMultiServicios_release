package dev.marianof.gestionmultiservicios.Controller;

import dev.marianof.gestionmultiservicios.Model.Producto;

public class ProductosRecyclerController {
    private static ProductosRecyclerController controller;
    private static Producto producto;

    private ProductosRecyclerController() {

    }

    public static ProductosRecyclerController getSingleton() {
        if (controller == null)
            synchronized (ProductosRecyclerController.class) {
                if (controller == null)
                    controller = new ProductosRecyclerController();
            }
        return controller;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        ProductosRecyclerController.producto = producto;
    }
}
