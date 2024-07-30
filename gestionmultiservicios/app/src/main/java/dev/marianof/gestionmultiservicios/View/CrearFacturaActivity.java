package dev.marianof.gestionmultiservicios.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

import dev.marianof.gestionmultiservicios.Controller.CrearFacturaController;
import dev.marianof.gestionmultiservicios.Controller.FacturacionMainController;
import dev.marianof.gestionmultiservicios.Controller.LoginController;
import dev.marianof.gestionmultiservicios.Controller.RecyclerViews.FacturasAdapter;
import dev.marianof.gestionmultiservicios.Controller.RecyclerViews.ProductosAdapter;
import dev.marianof.gestionmultiservicios.Controller.RecyclerViews.ProductosViewHolder;
import dev.marianof.gestionmultiservicios.Controller.VerProductosFacturaController;
import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.Model.PosicionesFactura;
import dev.marianof.gestionmultiservicios.Model.Producto;
import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.R;

public class CrearFacturaActivity extends AppCompatActivity {
    private ArrayList<Producto> productos;
    private Spinner tipoFactuSpinner;
    private Spinner usuarioSpinner;
    private Spinner clienteSpinner;
    private TextView tvMontoSin;
    private TextView tvMontoCon;
    private User usuario;
    private ArrayList<Cliente> clientesM;
    private CrearFacturaActivity myActivty;
    private Factura factura;
    private PosicionesFactura posicionesFactura;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_factura);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainCrearFactu), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myActivty = this;
        CrearFacturaController.getSingleton().setMiActivity(this);
        Button btnSalir = (Button) findViewById(R.id.salirBtn);
        Button btnVerProd = (Button) findViewById(R.id.verProductosBtn);
        Button btnCrearFac = (Button) findViewById(R.id.crearFacturaBtn);
        tipoFactuSpinner = (Spinner) findViewById(R.id.tipoFactuCombo);
        usuarioSpinner = (Spinner) findViewById(R.id.usuarioCombo);
        clienteSpinner = (Spinner) findViewById(R.id.clienteCombo);
        tvMontoSin = (TextView) findViewById(R.id.montoSinIVALabel);
        tvMontoCon = (TextView) findViewById(R.id.montoConIVALabel);
        this.setupSpinners();
        factura = CrearFacturaController.getSingleton().getFacturaFromVerProd();
        if (factura != null)
        {
            productos = factura.getListaProductos();
            this.loadPrices();
            posicionesFactura = CrearFacturaController.getSingleton().getPosicionesFactura();
            tipoFactuSpinner.setSelection(posicionesFactura.getPos2());
            clienteSpinner.setSelection(posicionesFactura.getPos1());
            usuarioSpinner.setSelection(posicionesFactura.getPos3());
        }
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CrearFacturaActivity.this, FacturacionMainActivity.class));
            }
        });
        btnVerProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idCliente = String.valueOf(clienteSpinner.getSelectedItemPosition());
                String tipoFactura = tipoFactuSpinner.getSelectedItem().toString();
                posicionesFactura = new PosicionesFactura(
                        clienteSpinner.getSelectedItemPosition(),
                        tipoFactuSpinner.getSelectedItemPosition(),
                        usuarioSpinner.getSelectedItemPosition()
                );
                CrearFacturaController.getSingleton().setPosicionesFactura(posicionesFactura);
                if (factura != null)
                    VerProductosFacturaController.getSingleton().setFactura(factura);
                else
                    VerProductosFacturaController.getSingleton().setFactura(
                            new Factura(
                                    null,
                                    String.valueOf(usuario.getIdUser()),
                                    idCliente,
                                    null,
                                    -1,
                                    tipoFactura,
                                    null,
                                    null
                            )
                    );
                VerProductosFacturaController.getSingleton().setActivityToChange(myActivty);
                startActivity(new Intent(CrearFacturaActivity.this, VerProductosFactura.class));
            }
        });
        btnCrearFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (productos == null) {return;}
                } catch (Exception e) {
                    System.out.println("NO HAY ARTÍCULOS.");
                }
                LocalTime currentTime = LocalTime.now();
                CrearFacturaController.getSingleton().makePetitionToUploadFactura(
                        new Factura(
                                null,
                                factura.getIdUsuario(),
                                String.valueOf(Integer.valueOf(factura.getIdCliente()) + 1),
                                factura.getListaProductos(),
                                factura.getMontoFactura(),
                                factura.getTipoFactura(),
                                new Date(Date.valueOf(String.valueOf(LocalDate.now())).getTime()).toString(),
                                Time.valueOf(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault()))).toString()
                        ));
                startActivity(new Intent(CrearFacturaActivity.this, FacturacionMainActivity.class));
            }
        });
    }

    private void setupSpinners() {
        String[] nombre = new String[2];
        nombre[0] = "Pedido";
        nombre[1] = "Venta";
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombre);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.tipoFactuSpinner.setAdapter(arrayAdapter);
        usuario = LoginController.getSingleton().getUsuario();
        nombre = new String[1];
        nombre[0] = usuario.getNombre();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombre);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.usuarioSpinner.setAdapter(arrayAdapter);
        CrearFacturaController.getSingleton().makePetitionToGetClientes();
    }

    public void setupClientesSpinner() {
        clientesM = CrearFacturaController.getSingleton().getClientes();
        String[] nombre = new String[clientesM.size()];
        for (int i = 0; i < clientesM.size(); i++)
            nombre[i] = clientesM.get(i).getNombreCliente() + " " + clientesM.get(i).getApellidosCliente();
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombre);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clienteSpinner.setAdapter(arrayAdapter);
    }

    private void loadPrices() {
        Double precio = 0.0;
        for (int i = 0; i < productos.size(); i++)
            precio += productos.get(i).getPrecio();
        tvMontoSin.setText("Monto sin IVA: " + precio);
        tvMontoCon.setText("Monto con IVA: " + precio + (precio * 0.21));
        factura = new Factura(
                null,
                factura.getIdUsuario(),
                factura.getIdCliente(),
                factura.getListaProductos(),
                precio + (precio * 0.21),
                factura.getTipoFactura(),
                factura.getFechaCreacion(),
                factura.getHoraCreacion()
        );
    }
}
