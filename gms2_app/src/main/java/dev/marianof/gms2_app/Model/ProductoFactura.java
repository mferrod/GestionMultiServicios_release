package dev.marianof.gms2_app.Model;

public class ProductoFactura {
    private final String idProducto;
    private final Integer cantidadProducto;

    public ProductoFactura(String idProducto, Integer cantidadProducto) {
        this.idProducto = idProducto;
        this.cantidadProducto = cantidadProducto;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }
}
