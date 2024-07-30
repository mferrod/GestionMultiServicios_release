package com.marianof.gms2api.Controller;

import com.google.gson.Gson;
import com.marianof.gms2api.Model.Factura;
import com.marianof.gms2api.Model.InfoFactura;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturacion")
public class FacturacionController {
    private final BDAdaptor bdAdaptor = new BDAdaptor();
    @GetMapping("/getFacturas")
    private String getFacturas() {
        return bdAdaptor.getFacturas();
    }
    @PostMapping("/addFacturas")
    private String addFacturas(@RequestBody Factura factura) {
        return bdAdaptor.addFactura(factura);
    }
    @PostMapping("/getInfoFactura")
    private String getInfoFactura(@RequestBody InfoFactura infoFactura) {
        return bdAdaptor.getInfoFactura(infoFactura);
    }
    @DeleteMapping("/deleteFactura")
    private String deleteFactura(@RequestBody Factura factura) {
        return bdAdaptor.deleteFactura(factura);
    }
    @PostMapping("/modifyFactura")
    private String modifyFactura(@RequestBody Factura factura) {
        return bdAdaptor.modifyFactura(factura);
    }
}
