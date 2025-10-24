package com.example.notas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.ViewHolder> {

    ArrayList<NotasModel> notasList;
    Context context;

    public NotasAdapter(Context context, ArrayList<NotasModel> notasList) {
        this.context = context;
        this.notasList = notasList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotasModel nota = notasList.get(position);
        holder.textTitle.setText(nota.getTitulo());

        // Listener para "Ver nota"
        holder.buttonVerNota.setOnClickListener(v -> {
            Intent intent = new Intent(context, VistaNotas.class);
            intent.putExtra("titulo", nota.getTitulo());
            intent.putExtra("contenido", nota.getContenido());
            intent.putExtra("posicion", position);

            final EditText editText = new EditText(context);
            editText.setHint("Introduce la contraseña");

            if (!nota.isSegura()){
                ((Activity) context).startActivityForResult(intent, 100);
            } else {
                new AlertDialog.Builder(context)
                        .setTitle("Añade seguridad a tu nota")
                        .setMessage("Introduce una contraseña:")
                        .setView(editText)
                        .setNegativeButton("Cancelar", null)
                        .setPositiveButton("Guardar", (dialog, which) -> {
                            String pass = editText.getText().toString().trim();
                            if (!pass.isEmpty() && pass.equals(nota.getContraseña())) {
                                ((Activity) context).startActivityForResult(intent, 100);
                                Toast.makeText(context, "Acceso permitido", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

            }
        });

        // Listener para "Añadir seguridad"
        holder.buttonSeguridad.setOnClickListener(v -> {
            final EditText editText = new EditText(context);
            editText.setHint("Introduce la contraseña");

            if (!nota.isSegura()) {
                // Añadir seguridad
                new AlertDialog.Builder(context)
                        .setTitle("Añade seguridad a tu nota")
                        .setMessage("Introduce una contraseña:")
                        .setView(editText)
                        .setNegativeButton("Cancelar", null)
                        .setPositiveButton("Guardar", (dialog, which) -> {
                            String pass = editText.getText().toString().trim();
                            if (!pass.isEmpty()) {
                                nota.setSegura(true);        // activamos seguridad
                                nota.setContraseña(pass);    // guardamos contraseña
                                notifyItemChanged(position); // refrescamos la tarjeta
                                Toast.makeText(context, "Seguridad activada", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            } else {
                // Quitar seguridad
                new AlertDialog.Builder(context)
                        .setTitle("Desactiva la seguridad de tu nota")
                        .setMessage("Introduce la contraseña actual:")
                        .setView(editText)
                        .setNegativeButton("Cancelar", null)
                        .setPositiveButton("Guardar", (dialog, which) -> {
                            String pass = editText.getText().toString().trim();
                            if (pass.equals(nota.getContraseña())) {
                                nota.setSegura(false);
                                nota.setContraseña("");
                                notifyItemChanged(position);
                                Toast.makeText(context, "Seguridad desactivada", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        holder.colores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] colores = {
                        Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA
                };
                String[] nombresColores = {"Rojo", "Verde", "Azul", "Amarillo", "Magenta"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Selecciona un color");
                builder.setItems(nombresColores, (dialog, which) -> {
                    // Cambiar el fondo del View al color seleccionado
                    holder.colores.setBackgroundColor(colores[which]);

                    // Guardar el color en tu modelo de nota
                    nota.setColor(colores[which]);
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notasList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        Button buttonVerNota, buttonSeguridad;

        View colores;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            buttonVerNota = itemView.findViewById(R.id.button2); // este es tu botón "Ver nota"
            buttonSeguridad = itemView.findViewById(R.id.button4); // este es tu botón "Añadir seguridad"
            colores = itemView.findViewById(R.id.colorIndicator); // este es el view "colores"
        }
    }
}