package dev.marianof.gms2_app.Model;

public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer cantidad;
    private String idProveedor;
    private String nombreProveedor;

    public Producto(String id, String nombre, String descripcion, Double precio, Integer cantidad, String idProveedor, String nombreProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    @Override
    public String toString() {
        return this.nombre + "$" +
                this.descripcion + "$" +
                this.precio + "$" +
                this.cantidad + "$" +
                this.nombreProveedor;
    }
}
