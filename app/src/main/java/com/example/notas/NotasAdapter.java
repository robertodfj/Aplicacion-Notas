package com.example.notas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.NotaViewHolder> {

    private ArrayList<NotasModel> listaNotas;

    // Constructor
    public NotasAdapter(ArrayList<NotasModel> listaNotas) {
        this.listaNotas = listaNotas;
    }

    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflamos el layout de cada ítem (el XML que creaste, por ejemplo item_nota.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notas, parent, false);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        // Obtenemos la nota actual
        NotasModel nota = listaNotas.get(position);

        // Asignamos los datos a las vistas
        holder.titulo.setText(nota.getTitulo());
    }

    @Override
    public int getItemCount() {
        return listaNotas.size(); // Cantidad de elementos que se mostrarán
    }

    public static class NotaViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion;

        public NotaViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textTitle);
        }
    }
}