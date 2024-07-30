package dev.marianof.gestionmultiservicios.Controller.RecyclerViews;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.marianof.gestionmultiservicios.Controller.FacturacionMainController;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.R;
import dev.marianof.gestionmultiservicios.View.FacturacionMainActivity;

public class FacturasViewHolder extends RecyclerView.ViewHolder {
    final FacturasAdapter mAdapter;
    private TextView idFactura;
    private Factura factura;
    public FacturasViewHolder(View itemView, FacturasAdapter adapter) {
        super(itemView);
        idFactura = itemView.findViewById(R.id.tvIdFactura);
        factura = null;
        this.mAdapter = adapter;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FacturacionMainController.getSingleton().setFacturaOnController(factura);
            }
        });
    }

    public void setIdFactura(String idFactura) {
        this.idFactura.setText(idFactura);
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}
