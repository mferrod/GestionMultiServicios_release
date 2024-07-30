package dev.marianof.gestionmultiservicios.View;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Controller.ClientesMainController;
import dev.marianof.gestionmultiservicios.Controller.ModificarClienteController;
import dev.marianof.gestionmultiservicios.Controller.ModificarUserController;
import dev.marianof.gestionmultiservicios.Controller.RecyclerViews.ClientesAdapter;
import dev.marianof.gestionmultiservicios.Controller.RecyclerViews.UsersAdapter;
import dev.marianof.gestionmultiservicios.Controller.UsersMainController;
import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.R;

public class ClientesMainActivity extends AppCompatActivity {

    private LinkedList<Cliente> mList;
    private RecyclerView mRecyclerView;  // Declaración del objeto RecyclerView
    private ClientesAdapter mAdapter;  // Declaración del adaptador para el RecyclerView
    private Button salirBtn;
    private Button crearUsuarioBtn;
    private Button modificarUsuarioBtn;
    private ClientesMainActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clientes_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainClientesActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        activity = this;
        ClientesMainController.getSingleton().setMyActivity(this);
        ClientesMainController.getSingleton().makePetition();
        salirBtn = (Button) findViewById(R.id.salirBtn);
        crearUsuarioBtn = (Button) findViewById(R.id.crearClientesBtn);
        modificarUsuarioBtn = (Button) findViewById(R.id.modificarClientesBtn);
        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClientesMainActivity.this, MainMenu_Activity.class));
            }
        });
        crearUsuarioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClientesMainActivity.this, CrearClienteActivity.class));
            }
        });
        modificarUsuarioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ModificarClienteController.getSingleton().getCliente() == null)
                    Toast.makeText(activity, "SELECCIONA UN0 DE LOS CLIENTES.", Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(ClientesMainActivity.this, ModificarClienteActivity.class));
            }
        });
    }

    public void setup() {
        if (mList != null)
            mList.clear();
        mList = ClientesMainController.getSingleton().getClientes();

        // Encuentra el RecyclerView en el diseño
        mRecyclerView = findViewById(R.id.rvClientes);

        // Configura el adaptador del RecyclerView con el contexto y la lista de eventos
        mAdapter = new ClientesAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);

        // Configura el administrador de diseño del RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
