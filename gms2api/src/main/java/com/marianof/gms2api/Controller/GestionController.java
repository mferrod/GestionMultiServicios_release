package com.marianof.gms2api.Controller;

import com.google.gson.Gson;
import com.marianof.gms2api.Model.Producto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/gestion")
public class GestionController {
    BDAdaptor bdAdaptor = new BDAdaptor();
    @GetMapping("/getProductos")
    private String getFacturas() {
        return bdAdaptor.getProductos();
    }
    @GetMapping("/getProveedores")
    private String getProveedores() {
        return bdAdaptor.getProveedores();
    }
    @PostMapping("/updateProducto")
    private String updateProducto(@RequestBody Producto producto) {
        return bdAdaptor.modifyProducto(producto);
    }
    @PostMapping("/makePedido")
    private String makePedido(@RequestBody ArrayList<Producto> productos) {
        for (Producto p: productos) bdAdaptor.modifyProducto(p);
        return "SE HA REALIZADO EL PEDIDO CORRECTAMENTE";
    }
    @PostMapping("/modifyProductos")
    private String modifyProdcutos(@RequestBody ArrayList<Producto> productos) {
        for (Producto p: productos) bdAdaptor.modifyProducto(p);
        return "SE HAN MODIFICADO LOS PEDIDOS CORRECTAMENTE.";
    }
}
