package dev.marianof.gestionmultiservicios.Controller.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Model.Producto;
import dev.marianof.gestionmultiservicios.R;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosViewHolder> {
    private final LinkedList<Producto> mList;
    private LayoutInflater mInflater;

    public ProductosAdapter(Context context,
                           LinkedList<Producto> list) {
        mInflater = LayoutInflater.from(context);
        this.mList = list;
    }


    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recycler_get_productos,
                parent, false);
        return new ProductosViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder holder, int position) {
        //TODO: fill data
        holder.setProducto(this.mList.get(position));
        holder.setTvNomProd(this.mList.get(position).getNombre());
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
}
