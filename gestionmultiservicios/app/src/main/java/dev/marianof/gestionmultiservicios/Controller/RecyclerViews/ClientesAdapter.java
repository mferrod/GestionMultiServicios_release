package dev.marianof.gestionmultiservicios.Controller.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.R;

public class ClientesAdapter extends RecyclerView.Adapter<ClientesViewHolder> {
    private final LinkedList<Cliente> mList;
    private LayoutInflater mInflater;

    public ClientesAdapter(Context context,
                         LinkedList<Cliente> list) {
        mInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    @NonNull
    @Override
    public ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recycler_clientes,
                parent, false);
        return new ClientesViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientesViewHolder holder, int position) {
        //TODO: fill data
        holder.setCliente(this.mList.get(position));
        holder.setNombreCliente(this.mList.get(position).getNombreCliente() + " " + this.mList.get(position).getApellidosCliente());
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
}
