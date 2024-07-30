package com.marianof.gms2api.Controller;

import com.marianof.gms2api.Model.Gps.InfoVeh;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/api/gps")
public class GpsController {
    private final LinkedList<InfoVeh> arrayList = new LinkedList<>();

    @GetMapping("/getPosition")
    public String get_position() {
        try {
            return arrayList.getFirst().toString();
        } catch (Exception ex) {
            return "No se ha podido recuperar ningún dato.";
        }
    }

    @PostMapping("/postPosition")
    public String post_position(@RequestBody InfoVeh clase) {
        try {
            arrayList.clear();
            arrayList.add(clase);
        } catch (Exception ex) {
            return ex.toString();
        }
        return "Se ha realizado correctamente el post.";
    }
}
