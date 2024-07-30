package dev.marianof.gms2_app.Model;

public class InfoFactura {
    private final int idCliente;
    private final String nombreCliente;
    private final int idUsuario;
    private final String nombreUsuario;

    public InfoFactura(int idCliente, String nombreCliente, int idUsuario, String nombreUsuario) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
}
