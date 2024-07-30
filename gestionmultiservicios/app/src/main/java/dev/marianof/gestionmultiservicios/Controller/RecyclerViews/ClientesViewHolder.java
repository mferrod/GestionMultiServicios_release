package dev.marianof.gestionmultiservicios.Controller.RecyclerViews;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.marianof.gestionmultiservicios.Controller.FacturacionMainController;
import dev.marianof.gestionmultiservicios.Controller.ModificarClienteController;
import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.R;
import dev.marianof.gestionmultiservicios.View.ClientesMainActivity;
import dev.marianof.gestionmultiservicios.View.FacturacionMainActivity;

public class ClientesViewHolder extends RecyclerView.ViewHolder {
    final ClientesAdapter mAdapter;
    private TextView nombreCliente;
    private Cliente cliente;
    public ClientesViewHolder(View itemView, ClientesAdapter adapter) {
        super(itemView);
        nombreCliente = itemView.findViewById(R.id.tvClientesName);
        cliente = null;
        this.mAdapter = adapter;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModificarClienteController.getSingleton().setCliente(cliente);
            }
        });
    }

    public void setNombreCliente(String idFactura) {
        this.nombreCliente.setText(idFactura);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
