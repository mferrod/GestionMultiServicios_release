package dev.marianof.gestionmultiservicios.Controller.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.R;


public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    private final LinkedList<User> mList;
    private LayoutInflater mInflater;

    public UsersAdapter(Context context,
                           LinkedList<User> list) {
        mInflater = LayoutInflater.from(context);
        this.mList = list;
    }


    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recycler_users,
                parent, false);
        return new UsersViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        //TODO: fill data
        holder.setUser(this.mList.get(position));
        holder.setNombreUser(this.mList.get(position).getUsername());
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
}
