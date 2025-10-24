package com.example.notas;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<NotasModel> notasList;
    NotasAdapter notasAdapter;

    Button btnAñadir, btnTema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Aplicar tema antes de setContentView
        boolean temaOscuro = getSharedPreferences("config", MODE_PRIVATE)
                .getBoolean("temaOscuro", false);
        if (temaOscuro) {
            setTheme(R.style.Theme_Notas);
        } else {
            setTheme(R.style.Theme_Notas_Oscuro);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnAñadir = findViewById(R.id.button3);
        btnTema = findViewById(R.id.tema);

        notasList = new ArrayList<>();
        notasAdapter = new NotasAdapter(this, notasList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notasAdapter);

        btnAñadir.setOnClickListener(v -> CrearNueva());
        btnTema.setOnClickListener(v -> CambiarTema());
    }

    // Método para recibir datos editados de VistaNotas
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            int posicion = data.getIntExtra("posicion", -1);
            if (posicion != -1) {
                String nuevoTitulo = data.getStringExtra("titulo");
                String nuevoContenido = data.getStringExtra("contenido");
                NotasModel nota = notasList.get(posicion);
                nota.setTitulo(nuevoTitulo);
                nota.setContenido(nuevoContenido);
                notasAdapter.notifyItemChanged(posicion);
            }
        }
    }

    // Crear nueva nota
    private void CrearNueva() {
        final EditText editText = new EditText(this);
        editText.setHint("Introduce el título");

        new AlertDialog.Builder(this)
                .setTitle("Nueva nota")
                .setMessage("Introduce un título para la nota:")
                .setView(editText)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    String titulo = editText.getText().toString().trim();
                    if (!titulo.isEmpty()) {
                        // Color por defecto: negro
                        notasList.add(new NotasModel(titulo, "", Color.BLACK));
                        notasAdapter.notifyItemInserted(notasList.size() - 1);
                    } else {
                        Toast.makeText(MainActivity.this, "El título no puede estar vacío", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    // Cambiar tema claro/oscuro
    private void CambiarTema() {
        boolean temaOscuro = getSharedPreferences("config", MODE_PRIVATE)
                .getBoolean("temaOscuro", false);

        temaOscuro = !temaOscuro; // alternar tema

        // Guardar preferencia
        getSharedPreferences("config", MODE_PRIVATE).edit()
                .putBoolean("temaOscuro", temaOscuro)
                .apply();

        // Reiniciar actividad para aplicar tema
        recreate();
    }
}