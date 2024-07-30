package com.marianof.gms2api.Controller;

import com.marianof.gms2api.Model.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private BDAdaptor bdAdaptor = new BDAdaptor();

    @PostMapping("/getUser")
    private String getUser(@RequestBody Usuario usuario) {
        return bdAdaptor.getUser(usuario);
    }
}
