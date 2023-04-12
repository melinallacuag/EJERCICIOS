package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ViewUtils;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.ViewHolder>{
    private List<Usuario> usuarioList;
    private List<Usuario> elementos;
    private Context context;
    final UsuariosAdapter.OnItemClickListener listener;

    private String categoriaFiltro = "Todos";

    public interface  OnItemClickListener{
        void onItemClick(Usuario item);
    }

    ArrayList<Usuario> listaOriginal;

    public UsuariosAdapter(List<Usuario> usuarioList,Context context,UsuariosAdapter.OnItemClickListener listener){
        this.usuarioList = usuarioList;
        this.context     = context;
        this.listener    = listener;

        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(usuarioList);
    }

    public void setUser(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_usuarios,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Usuario usuario = usuarioList.get(position);
        holder.txtNombre.setText(usuarioList.get(position).getNombre());
        holder.txtEmail.setText(usuarioList.get(position).getEmail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(usuario);
            }
        });

    }


    @Override
    public int getItemCount() {
        return usuarioList.size();
    }


    public void filtrarPorCategoria(String nombre) {
        categoriaFiltro = nombre;
        if (nombre.equals("Todos")) {
            usuarioList = elementos;
        } else {
            usuarioList = new ArrayList<>();
            for (Usuario usuario : elementos) {
                if (usuario.getNombre().equals(nombre)) {
                    usuarioList.add(usuario);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            usuarioList.clear();
            usuarioList.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Usuario> collecion = usuarioList.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                usuarioList.clear();
                usuarioList.addAll(collecion);
            } else {
                for (Usuario c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        usuarioList.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        private TextView txtNombre;
        private TextView txtEmail;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            txtNombre = itemView.findViewById(R.id.txt_nombre);
            txtEmail = itemView.findViewById(R.id.txt_email);
        }

    }
}
