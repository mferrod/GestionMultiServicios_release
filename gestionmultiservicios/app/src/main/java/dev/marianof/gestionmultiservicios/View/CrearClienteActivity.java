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
import dev.marianof.gestionmultiservicios.Controller.CrearFacturaController;
import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.R;

public class CrearClienteActivity extends AppCompatActivity {
    private Button salirBtn;
    private Button crearClienteBtn;
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private EditText et5;
    private EditText et6;
    private CrearClienteActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainClientesActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        activity = this;
        salirBtn = (Button) findViewById(R.id.salirBtn);
        crearClienteBtn = (Button) findViewById(R.id.crearClientesBtn);
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
        et6 = (EditText) findViewById(R.id.editText6);

        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CrearClienteActivity.this, ClientesMainActivity.class));
            }
        });

        crearClienteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() ||
                        et3.getText().toString().isEmpty() || et4.getText().toString().isEmpty() ||
                        et5.getText().toString().isEmpty() || et6.getText().toString().isEmpty())
                    Toast.makeText(activity, "TIENES QUE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                else {
                    CrearClientesController.getSingleton().makePetition(
                            new Cliente(
                                    -1,
                                    et1.getText().toString(),
                                    et2.getText().toString(),
                                    et3.getText().toString(),
                                    et4.getText().toString(),
                                    et5.getText().toString(),
                                    et6.getText().toString()
                            )
                    );
                    startActivity(new Intent(CrearClienteActivity.this, ClientesMainActivity.class));
                }
            }
        });
    }
}
