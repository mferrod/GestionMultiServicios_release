package dev.marianof.gestionmultiservicios.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.marianof.gestionmultiservicios.Controller.LoginController;
import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.R;

public class LoginActivity extends AppCompatActivity {
    private LoginActivity myActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LoginController.getSingleton().setActivity(this);
        myActivity = this;
        Button b = findViewById(R.id.button);
        EditText user = findViewById(R.id.editTextText);
        EditText password = findViewById(R.id.editTextTextPassword);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(myActivity, "EL USUARIO Y LA CONTRASEÑA NO PUEDEN ESTAR VACÍOS", Toast.LENGTH_SHORT).show();
                    return;
                }
                LoginController.getSingleton().makePetition(
                        user.getText().toString(),
                        password.getText().toString()
                );
            }
        });
    }

    public void login() {
        User usuario = LoginController.getSingleton().getUsuario();
        if (!usuario.isLogged()) {
            Toast.makeText(this, "USUARIO O CONTRASEÑA INCORRECTOS", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "LOGIN CORRECTO", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, MainMenu_Activity.class));
    }
}