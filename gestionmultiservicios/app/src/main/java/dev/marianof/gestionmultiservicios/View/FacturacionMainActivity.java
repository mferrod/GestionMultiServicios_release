package dev.marianof.gestionmultiservicios.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Controller.CrearFacturaController;
import dev.marianof.gestionmultiservicios.Controller.FacturacionMainController;
import dev.marianof.gestionmultiservicios.Controller.ModificarFacturaController;
import dev.marianof.gestionmultiservicios.Controller.RecyclerViews.FacturasAdapter;
import dev.marianof.gestionmultiservicios.Controller.VerProductosFacturaController;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.R;

public class FacturacionMainActivity extends AppCompatActivity {
    private LinkedList<Factura> mList;
    private RecyclerView mRecyclerView;  // Declaración del objeto RecyclerView
    private FacturasAdapter mAdapter;  // Declaración del adaptador para el RecyclerView
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_facturacion_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainFactuMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        FacturacionMainController.getSingleton().setActivity(this);
        FacturacionMainController.getSingleton().makePetition();
        Button salirBtn = (Button) findViewById(R.id.btnSalirFactura);
        Button crearFactuBtn = (Button) findViewById(R.id.btnCrearFactura);
        Button verFactura = (Button) findViewById(R.id.btnEditarFactura);

        crearFactuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerProductosFacturaController.getSingleton().setFactura(null);
                startActivity(new Intent(FacturacionMainActivity.this, CrearFacturaActivity.class));
            }
        });
        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacturacionMainActivity.this,MainMenu_Activity.class));
            }
        });
        verFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModificarFacturaController.getSingleton().setFactura(FacturacionMainController.getSingleton().getFactura());
                startActivity(new Intent(FacturacionMainActivity.this, ModificarFacturaActivity.class));
            }
        });
    }

    public void setup() {
        if (mList != null)
            mList.clear();
        mList = FacturacionMainController.getSingleton().getFacturas();

        // Encuentra el RecyclerView en el diseño
        mRecyclerView = findViewById(R.id.rvFacturas);

        // Configura el adaptador del RecyclerView con el contexto y la lista de eventos
        mAdapter = new FacturasAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);

        // Configura el administrador de diseño del RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
