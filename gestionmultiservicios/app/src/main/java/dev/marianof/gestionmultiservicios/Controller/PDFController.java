package dev.marianof.gestionmultiservicios.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.Model.User;

public class PDFController {
    private static PDFController controller;

    private PDFController() {

    }

    public static PDFController getSingleton() {
        if (controller == null)
            controller = new PDFController();
        return controller;
    }
}
