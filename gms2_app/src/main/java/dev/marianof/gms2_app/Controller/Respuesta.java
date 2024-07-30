package dev.marianof.gms2_app.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.marianof.gms2_app.Model.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Respuesta {
    public User getUserData(String body) {
        JsonElement jsonElement = JsonParser.parseString(body);
        JsonObject userJson = jsonElement.getAsJsonObject();
        return new User(
                userJson.get("idUser").getAsInt(),
                userJson.get("mail").getAsString(),
                userJson.get("admin").getAsString().equals("Y"),
                userJson.get("login").getAsString().equals("Y")
        );
    }

    public ArrayList<Producto> parseProducts(String json) {
        ArrayList<Producto> productos = new ArrayList<>();
        JsonElement head = JsonParser.parseString(json);
        JsonArray headArray = head.getAsJsonArray();
        for (int i=0; i < headArray.size(); i++) {
            JsonObject subHead = headArray.get(i).getAsJsonObject();
            productos.add(
                    new Producto(
                            subHead.get("id").getAsString(),
                            subHead.get("nombre").getAsString(),
                            subHead.get("descripcion").getAsString(),
                            subHead.get("precio").getAsDouble(),
                            subHead.get("cantidad").getAsInt(),
                            subHead.get("idProveedor").getAsString(),
                            subHead.get("nombreProveedor").getAsString()
                    )
            );
        }
        return productos;
    }

    public ArrayList<Proveedor> getProveedoresData(String json) {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        JsonElement head = JsonParser.parseString(json);
        JsonArray headArray = head.getAsJsonArray();
        for (int i=0; i < headArray.size(); i++) {
            JsonObject subHead = headArray.get(i).getAsJsonObject();
            proveedores.add(
                    new Proveedor(
                            subHead.get("id").getAsString(),
                            subHead.get("nombre").getAsString()
                    )
            );
        }
        return proveedores;
    }

    public Posicion parsePosition(String string) {
        Posicion posicion = null;
        JsonElement head = JsonParser.parseString(string);
        JsonObject subHead = head.getAsJsonObject();
        posicion = new Posicion(
                Double.parseDouble(subHead.get("latitudVeh").getAsString()),
                Double.parseDouble(subHead.get("longitudVeh").getAsString())
        );
        return posicion;
    }

    public ArrayList<Factura> parseFacturas(String string) {
        ArrayList<Factura> facturas = new ArrayList<>();
        JsonElement head = JsonParser.parseString(string);
        JsonArray arrayHead = head.getAsJsonArray();
        for (int i = 0; i < arrayHead.size(); i++) {
            ArrayList<Producto> productos = new ArrayList<>();
            JsonObject subHead = arrayHead.get(i).getAsJsonObject();
            String s = subHead
                    .getAsJsonArray("listaProductos").toString().replace("\\\"", "");
            JsonArray productosArray = JsonParser.parseString(s).getAsJsonArray();
            for (int j = 0; j < productosArray.size(); j++) {
                JsonObject productosSubHead = productosArray.get(j).getAsJsonObject();
                productos.add(
                        new Producto(
                                productosSubHead.get("id").getAsString(),
                                productosSubHead.get("nombre").getAsString(),
                                productosSubHead.get("descripcion").getAsString(),
                                productosSubHead.get("precio").getAsDouble(),
                                productosSubHead.get("cantidad").getAsInt(),
                                productosSubHead.get("idProveedor").getAsString(),
                                productosSubHead.get("nombreProveedor").getAsString()
                        )
                );
            }
            facturas.add(
                    new Factura(
                        subHead.get("idFactura").getAsString(),
                        subHead.get("idUsuario").getAsString(),
                        subHead.get("idCliente").getAsString(),
                        productos,
                        subHead.get("montoFactura").getAsFloat(),
                        subHead.get("tipoFactura").getAsString(),
                        subHead.get("fechaCreacion").getAsString(),
                        subHead.get("horaCreacion").getAsString()
                    )
            );
        }
        return facturas;
    }

    public InfoFactura parseInfoFactura(String string) {
        InfoFactura infoFactura = null;
        JsonElement head = JsonParser.parseString(string);
        JsonObject subHead = head.getAsJsonObject();
        infoFactura = new InfoFactura(
                subHead.get("idCliente").getAsInt(),
                subHead.get("nombreCliente").getAsString(),
                subHead.get("idUsuario").getAsInt(),
                subHead.get("nombreUsuario").getAsString()
        );
        return infoFactura;
    }

    public ArrayList<Cliente> parseClientes(String string) {
        ArrayList<Cliente> clientes = new ArrayList<>();

        JsonElement head = JsonParser.parseString(string);
        JsonArray headArr = head.getAsJsonArray();
        for (int i = 0; i < headArr.size(); i++) {
            JsonObject subHead = headArr.get(i).getAsJsonObject();
            clientes.add(
                    new Cliente(
                        subHead.get("idCliente").getAsInt(),
                            subHead.get("nombreCliente").getAsString(),
                            subHead.get("apellidosCliente").getAsString(),
                            subHead.get("emailCliente").getAsString(),
                            subHead.get("dniCliente").getAsString(),
                            subHead.get("direccionCliente").getAsString(),
                            subHead.get("telefonoCliente").getAsString()
                )
            );
        }
        return clientes;
    }
}
