package dev.marianof.gestionmultiservicios.Controller.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.R;

public class FacturasAdapter extends RecyclerView.Adapter<FacturasViewHolder> {
    private final LinkedList<Factura> mList;
    private LayoutInflater mInflater;

    public FacturasAdapter(Context context,
                         LinkedList<Factura> list) {
        mInflater = LayoutInflater.from(context);
        this.mList = list;
    }


    @NonNull
    @Override
    public FacturasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recycler_get_facturas,
                parent, false);
        return new FacturasViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FacturasViewHolder holder, int position) {
        //TODO: fill data
        holder.setFactura(this.mList.get(position));
        holder.setIdFactura("#FACT-" + this.mList.get(position).getIdFactura() + this.mList.get(position).getFechaCreacion().replace("-", ""));
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
}
