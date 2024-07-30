package dev.marianof.gestionmultiservicios.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.marianof.gestionmultiservicios.Controller.CrearClientesController;
import dev.marianof.gestionmultiservicios.Controller.CrearUsersController;
import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.R;

public class CrearUsuarioActivity extends AppCompatActivity {    private Button salirBtn;
    private Button crearClienteBtn;
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private CrearUsuarioActivity activity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainUsersActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        activity = this;
        salirBtn = (Button) findViewById(R.id.salirBtn);
        crearClienteBtn = (Button) findViewById(R.id.crearUsuarioBtn);
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);

        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CrearUsuarioActivity.this, UserMainActivity.class));
            }
        });

        crearClienteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() ||
                        et3.getText().toString().isEmpty())
                    Toast.makeText(activity, "TIENES QUE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                else {
                    CrearUsersController.getSingleton().makePetition(
                            new User(
                                    -1,
                                    et1.getText().toString(),
                                    null,
                                    et2.getText().toString(),
                                    et3.getText().toString().equals("Y"),
                                    false
                            )
                    );
                    startActivity(new Intent(CrearUsuarioActivity.this, UserMainActivity.class));
                }
            }
        });
    }
}
