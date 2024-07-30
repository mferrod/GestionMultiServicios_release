package com.marianof.gms2api.Controller;

import com.marianof.gms2api.Model.Usuario;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    BDAdaptor bdAdaptor = new BDAdaptor();
    @GetMapping("/getUsers")
    private String getUsers() {
        return bdAdaptor.getUsers();
    }
    @PostMapping("/modifyUser")
    private String modifyUser(@RequestBody Usuario usuario) {
        return bdAdaptor.modifyUser(usuario);
    }
    @PostMapping("/deleteUser")
    private String deleteUser(@RequestBody Usuario usuario) {
        return bdAdaptor.deleteUser(usuario);
    }
    @PostMapping("/addUser")
    private String addUser(@RequestBody Usuario usuario) {
        String resultado = bdAdaptor.addUser(usuario);
        String resPass = bdAdaptor.getPassForMail(resultado);
        String mensaje = "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<body>\n" +
                "    <p><h1>¡HOLA!</h1></p>\n" +
                "    <p>ESTA ES TU CONTRASEÑA PARA QUE PUEDAS ACCEDER A TU CUENTA DE LA APLICACIÓN DE GESTIÓN MULTISERVICIO</p>\n" +
                "    <p>CONTRASEÑA: <strong>" + resPass + "</strong></p>\n" +
                "    <p>¡HASTA LA PRÓXIMA!</p>\n" +
                "    <p><h2>NO RESPONDA A ESTE E-MAIL\n</h2></p>" +
                "</body>\n" +
                "</html>";
        GestorEmail.getSingleton().enviarMensajeTexto(resultado,
                "CONTRASEÑA DE ACCESO A TU CUENTA DE RESERVAS",
                mensaje
        );
        return "Se ha creado el usuario con éxito";
    }
}
