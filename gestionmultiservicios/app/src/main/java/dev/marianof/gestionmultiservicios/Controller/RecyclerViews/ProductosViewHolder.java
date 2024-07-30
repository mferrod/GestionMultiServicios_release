package dev.marianof.gestionmultiservicios.Controller.RecyclerViews;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import dev.marianof.gestionmultiservicios.Controller.ProductosRecyclerController;
import dev.marianof.gestionmultiservicios.Model.Producto;
import dev.marianof.gestionmultiservicios.R;

public class ProductosViewHolder extends RecyclerView.ViewHolder {
    final ProductosAdapter mAdapter;
    private TextView tvNomProd;
    private Producto producto;
    public ProductosViewHolder(View itemView, ProductosAdapter adapter) {
        super(itemView);
        tvNomProd = itemView.findViewById(R.id.tvProductoNom);
        producto = null;
        this.mAdapter = adapter;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductosRecyclerController.getSingleton().setProducto(producto);
            }
        });

    }

    public void setTvNomProd(String nomProd) {
        this.tvNomProd.setText(nomProd + " - " + producto.getCantidad());
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
