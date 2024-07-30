package dev.marianof.gestionmultiservicios.Controller.RecyclerViews;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.marianof.gestionmultiservicios.Controller.FacturacionMainController;
import dev.marianof.gestionmultiservicios.Controller.ModificarUserController;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.R;
import dev.marianof.gestionmultiservicios.View.FacturacionMainActivity;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    final UsersAdapter mAdapter;
    private TextView nombreUser;
    private User user;
    public UsersViewHolder(View itemView, UsersAdapter adapter) {
        super(itemView);
        nombreUser = itemView.findViewById(R.id.tvUsersName);
        user = null;
        this.mAdapter = adapter;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModificarUserController.getSingleton().setUser(user);
            }
        });
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser.setText(nombreUser);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
