package dev.marianof.gestionmultiservicios.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Controller.CrearFacturaController;
import dev.marianof.gestionmultiservicios.Controller.ProductosRecyclerController;
import dev.marianof.gestionmultiservicios.Controller.RecyclerViews.ProductosAdapter;
import dev.marianof.gestionmultiservicios.Controller.VerProductosFacturaController;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.Model.Producto;
import dev.marianof.gestionmultiservicios.R;

public class VerProductosFactura extends AppCompatActivity {
    private LinkedList<Producto> mList;
    private RecyclerView mRecyclerView;  // Declaración del objeto RecyclerView
    private ProductosAdapter mAdapter;  // Declaración del adaptador para el RecyclerView
    private LinkedList<Producto> productos;
    private VerProductosFactura verProductosFactura;
    private String tipoFactura;
    private Factura factura;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_factura_productos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainFacturaProductos), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        VerProductosFacturaController.getSingleton().setMiActivity(this);
        factura = VerProductosFacturaController.getSingleton().getFactura();
        productos = new LinkedList<>();
        try {
            if (factura.getListaProductos() != null)
                productos.addAll(factura.getListaProductos());
        } catch (Exception e) {
            System.out.println("No hay productos en la factura.");
        }
        VerProductosFacturaController.getSingleton().makePetition();
        tipoFactura = factura.getTipoFactura();
        verProductosFactura = this;
        updateRv2();
        Button btnAdd = (Button) findViewById(R.id.button1);
        Button btnRmv = (Button) findViewById(R.id.button2);
        Button btnSalir = (Button) findViewById(R.id.salirBtn);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto producto = ProductosRecyclerController.getSingleton().getProducto();
                Producto productoF = null;
                int index = 0;
                int indexP = 0;
                while (!mList.get(indexP).getId().equals(producto.getId()))
                    indexP++;
                if (!productos.isEmpty() && pInList(producto)) {
                    for (; productoF == null; index++)
                        if (producto.getId().equals(productos.get(index).getId())) {
                            productoF = productos.get(index);
                            index--;
                        }
                } else {
                    productos.add(
                            new Producto(
                                    producto.getId(),
                                    producto.getNombre(),
                                    producto.getDescripcion(),
                                    producto.getPrecio(),
                                    0,
                                    producto.getIdProveedor(),
                                    producto.getNombreProveedor()
                            )
                    );
                    while (!productos.get(index).getId().equals(producto.getId()))
                        index++;
                    productoF = productos.get(index);
                }
                if (productos.get(index).getId().equals(producto.getId())) {
                    if (tipoFactura.equals("Venta")) {
                        if (producto.getCantidad() > 0) {
                            mList.get(indexP).setCantidad(producto.getCantidad() - 1);
                            productos.get(index).setCantidad(productoF.getCantidad() + 1);
                        }
                        else
                            Toast.makeText(verProductosFactura, "NO HAY STOCK EN EL ALMACEN", Toast.LENGTH_SHORT);
                    } else {
                        productos.get(index).setCantidad(productoF.getCantidad() + 1);
                    }
                }
                updateRv1();
                updateRv2();
            }
        });
        btnRmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto producto = ProductosRecyclerController.getSingleton().getProducto();
                Producto productoF = null;
                int indexP = 0;
                int index = 0;
                while (!mList.get(indexP).getId().equals(producto.getId()))
                    indexP++;
                if (!productos.isEmpty() && pInList(producto)) {
                    for (; productoF == null; index++)
                        if (producto.getId().equals(productos.get(index).getId())) {
                            productoF = productos.get(index);
                            index--;
                        }
                    if (productoF.getCantidad() - 1 <= 0) {
                        productos.remove(index);
                        mList.get(indexP).setCantidad(producto.getCantidad() + 1);
                    }
                    else {
                        productos.get(index).setCantidad(productoF.getCantidad() - 1);
                        mList.get(indexP).setCantidad(producto.getCantidad() + 1);
                    }
                }
                updateRv1();
                updateRv2();
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Producto> productos1 = new ArrayList<>();
                productos1.addAll(productos);
                try {
                    if (factura.getIdFactura().equals(null))
                        factura = new Factura(
                                null,
                                factura.getIdUsuario(),
                                factura.getIdCliente(),
                                productos1,
                                -1,
                                factura.getTipoFactura(),
                                factura.getFechaCreacion(),
                                factura.getHoraCreacion()

                        );
                    else
                    factura = new Factura(
                            factura.getIdFactura(),
                            factura.getIdUsuario(),
                            factura.getIdCliente(),
                            productos1,
                            -1,
                            factura.getTipoFactura(),
                            factura.getFechaCreacion(),
                            factura.getHoraCreacion()

                    );
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
                Object o = VerProductosFacturaController.getSingleton().getActivityToChange();
                startActivity(new Intent(VerProductosFactura.this, o.getClass()));
                VerProductosFacturaController.getSingleton().makePetitionUploadProductos(mList);
                VerProductosFacturaController.getSingleton().setFactura(factura);
            }
        });
    }


    public void setup() {
        mList = VerProductosFacturaController.getSingleton().getProductos();
        this.updateRv1();
    }

    private void updateRv1() {
        // Encuentra el RecyclerView en el diseño
        mRecyclerView = findViewById(R.id.recyclerView1);

        // Configura el adaptador del RecyclerView con el contexto y la lista de eventos
        mAdapter = new ProductosAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);

        // Configura el administrador de diseño del RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void updateRv2() {
        // Encuentra el RecyclerView en el diseño
        mRecyclerView = findViewById(R.id.recyclerView2);

        // Configura el adaptador del RecyclerView con el contexto y la lista de eventos
        mAdapter = new ProductosAdapter(this, productos);
        mRecyclerView.setAdapter(mAdapter);

        // Configura el administrador de diseño del RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private boolean pInList(Producto producto) {
        if (productos.isEmpty())
            return false;
        for (Producto p: productos)
            if (p.getId().equals(producto.getId()))
                return true;
        return false;
    }
}
