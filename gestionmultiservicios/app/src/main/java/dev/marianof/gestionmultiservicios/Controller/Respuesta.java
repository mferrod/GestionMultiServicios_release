package dev.marianof.gestionmultiservicios.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.Coordenadas;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.Model.Producto;
import dev.marianof.gestionmultiservicios.Model.User;

public class Respuesta {
    public User parseUser(String json) {
        JsonElement head = JsonParser.parseString(json);
        JsonObject subHead = head.getAsJsonObject();
        return new User(
                subHead.get("idUser").getAsInt(),
                subHead.get("mail").getAsString(),
                null,
                subHead.get("nombre").getAsString(),
                subHead.get("admin").getAsString().equals("Y"),
                subHead.get("login").getAsString().equals("Y")
        );
    }

    public Coordenadas parsePosition(String json) {
        JsonElement head = JsonParser.parseString(json);
        JsonObject subHead = head.getAsJsonObject();
        return new Coordenadas(
                subHead.get("latitudVeh").getAsDouble(),
                subHead.get("longitudVeh").getAsDouble(),
                subHead.get("nombreVeh").getAsString()
        );
    }

    public LinkedList<Factura> parseFacturas(String string) {
        LinkedList<Factura> facturas = new LinkedList<>();
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

    public LinkedList<Producto> parseProductos(String json) {
        LinkedList<Producto> productos = new LinkedList<>();
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

    public LinkedList<User> parseUsers(String json) {
        JsonElement head = JsonParser.parseString(json);
        JsonArray subHead = head.getAsJsonArray();
        LinkedList<User> linkedList = new LinkedList<>();
        for (int i = 0; i < subHead.size(); i++) {
            JsonObject object = subHead.get(i).getAsJsonObject();
            linkedList.add(
                    new User(
                    object.get("idUser").getAsInt(),
                    object.get("mail").getAsString(),
                    null,
                    object.get("nombre").getAsString(),
                    object.get("admin").getAsString().equals("Y"),
                    object.get("login").getAsString().equals("Y")
            ));
        }
        return linkedList;
    }
}
