package dev.marianof.gms2_app.Controller;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import dev.marianof.gms2_app.GmsFacturacionMain;
import dev.marianof.gms2_app.Model.Cliente;
import dev.marianof.gms2_app.Model.Factura;
import dev.marianof.gms2_app.Model.Producto;
import dev.marianof.gms2_app.Model.User;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GmsPDFController {
    private static GmsPDFController controller;

    private GmsPDFController() {

    }

    public static GmsPDFController getSingleton() {
        if (controller == null)
            controller = new GmsPDFController();
        return controller;
    }

    public void crearPDF(Factura factura, User dataUser, ArrayList<Cliente> clientes) {
        // Crear un JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Factura");
        Cliente cliente = null;

        // Mostrar el diálogo de guardar
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Asegurarse de que el archivo tenga la extensión .pdf
            String dest = fileToSave.getAbsolutePath();
            if (!dest.toLowerCase().endsWith(".pdf"))
                dest += ".pdf";

            for (Cliente c : clientes) {
                if (c.idCliente() == Integer.parseInt(factura.getIdCliente())) {
                    cliente = c;
                    break;
                }
            }

            try {
                // Crear un PdfWriter
                PdfWriter writer = new PdfWriter(dest);

                // Crear un PdfDocument
                PdfDocument pdfDoc = new PdfDocument(writer);

                // Crear un Document
                Document document = new Document(pdfDoc);

                // Agregar logotipo
                String imgPath = String.valueOf(GmsFacturacionMain.class.getResource("logo_peque.png"));
                ImageData data = ImageDataFactory.create(imgPath);
                Image img = new Image(data);
                document.add(img);

                // Agregar encabezado
                document.add(new Paragraph("FACTURA")
                        .setFontSize(20)
                        .setBold()
                        .setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 179, 71)))
                        .setTextAlignment(TextAlignment.CENTER));

                document.add(
                        new Paragraph("ID FACTURA: #FACT-"
                                + factura.getIdFactura()
                                + factura.getFechaCreacion().replace("-","")
                        ).setTextAlignment(TextAlignment.RIGHT).setBold()
                );

                // Información de la empresa
                document.add(
                        new Paragraph(
                                "Patricio Fernández Gonzalez" +
                                        "\nCalle Hilario Marco n24" +
                                        "\nTeléfono: 953720508 - 953720381" +
                                        "\nEmail: pafergonsl@gmail.com")
                                .setMarginBottom(20));

                document.add(
                        new Paragraph(
                                "FECHA DE LA FACTURACIÓN: " + factura.getFechaCreacion() +
                                        "\nHORA DE LA FACTURACIÓN: " + factura.getHoraCreacion()
                        ).setTextAlignment(TextAlignment.RIGHT)
                );

                // Información del cliente
                document.add(
                        new Paragraph(
                                "Factura para:" +
                                        "\nNombre: "  + cliente.nombreCliente() + " " + cliente.apellidosCliente() +
                                        "\nDirección: " + cliente.direccionCliente() +
                                        "\nTeléfono: " + cliente.telefonoCliente() +
                                        "\nEmail: " + cliente.emailCliente())
                                .setMarginBottom(20));

                // Crear una tabla para los detalles de la factura
                float[] columnWidths = {1, 5, 2, 2, 2};
                Table table = new Table(UnitValue.createPercentArray(columnWidths));
                table.setWidth(UnitValue.createPercentValue(100));

                // Agregar cabeceras a la tabla
                table.addHeaderCell(new Paragraph("Nombre").setBackgroundColor(new DeviceRgb(255, 179, 71)));
                table.addHeaderCell(new Paragraph("Descripción").setBackgroundColor(new DeviceRgb(255, 179, 71)));
                table.addHeaderCell(new Paragraph("Cantidad").setBackgroundColor(new DeviceRgb(255, 179, 71)));
                table.addHeaderCell(new Paragraph("Precio Unitario").setBackgroundColor(new DeviceRgb(255, 179, 71)));
                table.addHeaderCell(new Paragraph("Total").setBackgroundColor(new DeviceRgb(255, 179, 71)));

                // Agregar detalles de los productos/servicios
                ArrayList<Producto> productos = factura.getListaProductos();
                for (Producto p: productos)
                    addInvoiceItem(
                            table,
                            p.getNombre(),
                            p.getDescripcion(),
                            String.valueOf(p.getCantidad()),
                            String.valueOf(p.getPrecio()),
                            String.valueOf(p.getCantidad() * p.getPrecio())
                    );

                // Agregar la tabla al documento
                document.add(table);

                // Total
                document.add(new Paragraph("Total a pagar: " + String.format("%.2f", factura.getMontoFactura()) + "€")
                        .setFontSize(14)
                        .setBold()
                        .setTextAlignment(TextAlignment.RIGHT)
                        .setBackgroundColor(new DeviceRgb(255, 179, 71))
                        .setMarginTop(20));

                // Banda de color naranja hasta el final
                document.add(new Paragraph().setBackgroundColor(new DeviceRgb(255, 179, 71)).setHeight(10));

                // Cerrar el documento
                document.close();

                System.out.println("La factura en PDF ha sido creada con éxito!");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addInvoiceItem(Table table, String nombre, String description, String quantity, String unitPrice, String total) {
        table.addCell(new Paragraph(nombre).setBackgroundColor(new DeviceRgb(255, 255, 255)));
        table.addCell(new Paragraph(description).setBackgroundColor(new DeviceRgb(255, 255, 255)));
        table.addCell(new Paragraph(quantity).setBackgroundColor(new DeviceRgb(255, 255, 255)));
        table.addCell(new Paragraph(unitPrice).setBackgroundColor(new DeviceRgb(255, 255, 255)));
        table.addCell(new Paragraph(total).setBackgroundColor(new DeviceRgb(255, 255, 255)));
    }
}
