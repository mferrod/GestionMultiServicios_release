package dev.marianof.gestionmultiservicios.View;

import android.app.Activity;
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

import java.util.ArrayList;

import dev.marianof.gestionmultiservicios.Controller.CrearFacturaController;
import dev.marianof.gestionmultiservicios.Controller.LoginController;
import dev.marianof.gestionmultiservicios.Controller.ModificarFacturaController;
import dev.marianof.gestionmultiservicios.Controller.VerProductosFacturaController;
import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.Model.PosicionesFactura;
import dev.marianof.gestionmultiservicios.Model.Producto;
import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.R;

public class ModificarFacturaActivity extends AppCompatActivity {
    private Factura factura;
    private ArrayList<Producto> productos;
    private Spinner tipoFactuSpinner;
    private Spinner usuarioSpinner;
    private Spinner clienteSpinner;
    private TextView tvMontoSin;
    private TextView tvMontoCon;
    private User usuario;
    private ArrayList<Cliente> clientesM;
    private ModificarFacturaActivity myActivty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_factura);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainCrearFactu), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ModificarFacturaController.getSingleton().setMyActivity(this);
        myActivty = this;
        factura = ModificarFacturaController.getSingleton().getFactura();
        try {
            if (ModificarFacturaController.getSingleton().isChanged())
                factura = VerProductosFacturaController.getSingleton().getFactura();
            ModificarFacturaController.getSingleton().setChanged(false);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        productos = factura.getListaProductos();
        Button btnSalir = (Button) findViewById(R.id.salirBtn);
        Button btnVerProd = (Button) findViewById(R.id.verProductosBtn);
        Button btnModificarFac = (Button) findViewById(R.id.modificarFacturaBtn);
        Button btnBorrarFac = (Button) findViewById(R.id.delFacturaBtn);
        tipoFactuSpinner = (Spinner) findViewById(R.id.tipoFactuCombo);
        usuarioSpinner = (Spinner) findViewById(R.id.usuarioCombo);
        clienteSpinner = (Spinner) findViewById(R.id.clienteCombo);
        tvMontoSin = (TextView) findViewById(R.id.montoSinIVALabel);
        tvMontoCon = (TextView) findViewById(R.id.montoConIVALabel);
        this.setupSpinners();
        this.loadPrices();
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModificarFacturaActivity.this, FacturacionMainActivity.class));
            }
        });
        btnModificarFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModificarFacturaController.getSingleton().makePetitionModifyFactura(factura);
                startActivity(new Intent(ModificarFacturaActivity.this, FacturacionMainActivity.class));
            }
        });
        btnVerProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerProductosFacturaController.getSingleton().setFactura(factura);
                VerProductosFacturaController.getSingleton().setActivityToChange(myActivty);
                ModificarFacturaController.getSingleton().setChanged(true);
                startActivity(new Intent(ModificarFacturaActivity.this, VerProductosFactura.class));
            }
        });
        btnBorrarFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModificarFacturaController.getSingleton().makePetitionDeleteFactura(factura);
                startActivity(new Intent(ModificarFacturaActivity.this, FacturacionMainActivity.class));
            }
        });
    }

    private void setupSpinners() {
        String[] nombre = new String[1];
        nombre[0] = factura.getTipoFactura();
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombre);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.tipoFactuSpinner.setAdapter(arrayAdapter);
        usuario = LoginController.getSingleton().getUsuario();
        nombre = new String[1];
        nombre[0] = usuario.getNombre();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombre);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.usuarioSpinner.setAdapter(arrayAdapter);
        ModificarFacturaController.getSingleton().makePetitionToGetClientes();
    }

    public void setupClientesSpinner() {
        clientesM = ModificarFacturaController.getSingleton().getClientes();
        String[] nombre = new String[1];
        for (int i = 0; i < clientesM.size(); i++)
            if (clientesM.get(i).getIdCliente() == Integer.valueOf(factura.getIdCliente()))
                nombre[0] = clientesM.get(i).getNombreCliente() + " " + clientesM.get(i).getApellidosCliente();
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombre);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clienteSpinner.setAdapter(arrayAdapter);
        usuarioSpinner.setEnabled(false);
        clienteSpinner.setEnabled(false);
        tipoFactuSpinner.setEnabled(false);
    }

    private void loadPrices() {
        Double precio = 0.0;
        for (int i = 0; i < productos.size(); i++)
            precio += productos.get(i).getPrecio();
        tvMontoSin.setText("Monto sin IVA: " + precio);
        precio = precio + (precio * .21);
        tvMontoCon.setText("Monto con IVA: " + precio);
        factura = new Factura(
                factura.getIdFactura(),
                factura.getIdUsuario(),
                factura.getIdCliente(),
                factura.getListaProductos(),
                precio,
                factura.getTipoFactura(),
                factura.getFechaCreacion(),
                factura.getHoraCreacion()
        );
    }
}