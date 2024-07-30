package dev.marianof.gestionmultiservicios.View;

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

import dev.marianof.gestionmultiservicios.Controller.ClientesMainController;
import dev.marianof.gestionmultiservicios.Controller.CrearClientesController;
import dev.marianof.gestionmultiservicios.Controller.ModificarClienteController;
import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.R;

public class ModificarClienteActivity extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private EditText et5;
    private EditText et6;
    private Button salirBtn;
    private Button modificarBtn;
    private Button deleteBtn;
    private ModificarClienteActivity activity;
    private Cliente cliente;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainClientesActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        activity = this;
        salirBtn = (Button) findViewById(R.id.salirBtn);
        modificarBtn = (Button) findViewById(R.id.modificarClientesBtn);
        deleteBtn = (Button) findViewById(R.id.borrarClientesBtn);
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
        et6 = (EditText) findViewById(R.id.editText6);
        cliente = ModificarClienteController.getSingleton().getCliente();
        et1.setText(cliente.getNombreCliente());
        et2.setText(cliente.getApellidosCliente());
        et3.setText(cliente.getEmailCliente());
        et4.setText(cliente.getDniCliente());
        et5.setText(cliente.getDireccionCliente());
        et6.setText(cliente.getTelefonoCliente());
        modificarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() ||
                        et3.getText().toString().isEmpty() || et4.getText().toString().isEmpty() ||
                        et5.getText().toString().isEmpty() || et6.getText().toString().isEmpty())
                    Toast.makeText(activity, "TIENES QUE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                else {
                    ModificarClienteController.getSingleton().makePetitionToModify(
                            new Cliente(
                                    cliente.getIdCliente(),
                                    et1.getText().toString(),
                                    et2.getText().toString(),
                                    et3.getText().toString(),
                                    et4.getText().toString(),
                                    et5.getText().toString(),
                                    et6.getText().toString()
                            )
                    );
                    ModificarClienteController.getSingleton().setCliente(null);
                    startActivity(new Intent(ModificarClienteActivity.this, ClientesMainActivity.class));
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModificarClienteController.getSingleton().makePetitionToDelete(cliente);
                ModificarClienteController.getSingleton().setCliente(null);
                startActivity(new Intent(ModificarClienteActivity.this, ClientesMainActivity.class));
            }
        });

        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModificarClienteActivity.this, ClientesMainActivity.class));
            }
        });
    }
}