package com.marianof.gms2api.Controller;

import com.marianof.gms2api.Model.Cliente;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {
    private final BDAdaptor bdAdaptor = new BDAdaptor();

    @GetMapping("/getClientes")
    private String getClientes() {
        return bdAdaptor.getClientes();
    }
    @PostMapping("/modifyCliente")
    private String modifyUser(@RequestBody Cliente cliente) {
        return bdAdaptor.modifyCliente(cliente);
    }
    @PostMapping("/deleteCliente")
    private String deleteUser(@RequestBody Cliente cliente) {
        return bdAdaptor.deleteCliente(cliente);
    }
    @PostMapping("/addCliente")
    private String addUser(@RequestBody Cliente cliente) {
        return bdAdaptor.addCliente(cliente);
    }
}
