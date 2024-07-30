package dev.marianof.gestionmultiservicios.View;

import android.app.Activity;
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

import dev.marianof.gestionmultiservicios.Controller.FacturacionMainController;
import dev.marianof.gestionmultiservicios.Controller.ModificarUserController;
import dev.marianof.gestionmultiservicios.Controller.RecyclerViews.FacturasAdapter;
import dev.marianof.gestionmultiservicios.Controller.RecyclerViews.UsersAdapter;
import dev.marianof.gestionmultiservicios.Controller.UsersMainController;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.R;

public class UserMainActivity extends AppCompatActivity {

    private LinkedList<User> mList;
    private RecyclerView mRecyclerView;  // Declaración del objeto RecyclerView
    private UsersAdapter mAdapter;  // Declaración del adaptador para el RecyclerView

    private Button salirBtn;
    private Button crearUsuarioBtn;
    private Button modificarUsuarioBtn;
    private UserMainActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_users_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainUsersActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        activity = this;
        UsersMainController.getSingleton().setMyActivity(this);
        UsersMainController.getSingleton().makePetition();
        salirBtn = (Button) findViewById(R.id.salirBtn);
        crearUsuarioBtn = (Button) findViewById(R.id.crearUsuarioBtn);
        modificarUsuarioBtn = (Button) findViewById(R.id.modificarUsuarioBtn);

        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMainActivity.this, MainMenu_Activity.class));
            }
        });

        crearUsuarioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMainActivity.this, CrearUsuarioActivity.class));
            }
        });

        modificarUsuarioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ModificarUserController.getSingleton().getUser() == null)
                    Toast.makeText(activity, "SELECCIONA UN0 DE LOS USUARIOS.", Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(UserMainActivity.this, ModificarUsuarioActivity.class));
            }
        });
    }

    public void setup() {
        if (mList != null)
            mList.clear();
        mList = UsersMainController.getSingleton().getUsers();

        // Encuentra el RecyclerView en el diseño
        mRecyclerView = findViewById(R.id.rvUsuarios);

        // Configura el adaptador del RecyclerView con el contexto y la lista de eventos
        mAdapter = new UsersAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);

        // Configura el administrador de diseño del RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
