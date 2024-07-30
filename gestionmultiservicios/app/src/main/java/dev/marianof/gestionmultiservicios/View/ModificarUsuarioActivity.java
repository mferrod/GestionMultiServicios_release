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

import dev.marianof.gestionmultiservicios.Controller.ModificarUserController;
import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.R;

public class ModificarUsuarioActivity extends AppCompatActivity {
    private User user;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private Button salirBtn;
    private Button modificarBtn;
    private Button borrarBtn;
    private ModificarUsuarioActivity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainUsersActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        activity = this;
        ModificarUserController.getSingleton().setMyActivity(this);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        salirBtn = (Button) findViewById(R.id.salirBtn);
        borrarBtn = (Button) findViewById(R.id.borrarUsuarioBtn);
        modificarBtn = (Button) findViewById(R.id.modificarUsuarioBtn);
        user = ModificarUserController.getSingleton().getUser();
        editText1.setText(user.getUsername());
        editText2.setText(user.getNombre());
        editText3.setText(user.isAdmin() ? "Y" : "N");

        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModificarUserController.getSingleton().setUser(null);
                startActivity(new Intent(ModificarUsuarioActivity.this, UserMainActivity.class));
            }
        });
        borrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModificarUserController.getSingleton().makePetitionToDelete(user);
                startActivity(new Intent(ModificarUsuarioActivity.this, UserMainActivity.class));
            }
        });
        modificarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty() ||
                        editText3.getText().toString().isEmpty())
                    Toast.makeText(activity, "DEBES RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                else {
                    ModificarUserController.getSingleton().makePetitionToModify(
                            new User(
                                    user.getIdUser(),
                                    editText1.getText().toString(),
                                    null,
                                    editText2.getText().toString(),
                                    editText3.getText().toString().equals("Y"),
                                    false
                            )
                    );
                    startActivity(new Intent(ModificarUsuarioActivity.this, UserMainActivity.class));
                }
            }
        });
    }
}