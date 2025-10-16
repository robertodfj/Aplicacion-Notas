package com.example.notas;

import android.app.Activity;
import android.content.Intent;
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

        holder.buttonVerNota.setOnClickListener(v -> {
            Intent intent = new Intent(context, VistaNotas.class);
            intent.putExtra("titulo", nota.getTitulo());
            intent.putExtra("contenido", nota.getContenido());
            intent.putExtra("posicion", position);

            ((Activity) context).startActivityForResult(intent, 100);
        });

        // Listener para "Añadir seguridad" (ya lo harías aparte)
        holder.buttonSeguridad.setOnClickListener(v -> {
            // tu código para seguridad
        });
    }

    @Override
    public int getItemCount() {
        return listaNotas.size(); // Cantidad de elementos que se mostrarán
    }

    public static class NotaViewHolder extends RecyclerView.ViewHolder {
        public View buttonSeguridad;
        TextView titulo, descripcion;

        public NotaViewHolder(@NonNull View itemView) {
                super(itemView);
                titulo = itemView.findViewById(R.id.textTitle);
                buttonVerNota = itemView.findViewById(R.id.button2);
                buttonSeguridad = itemView.findViewById(R.id.button4); 
        }
    }
}