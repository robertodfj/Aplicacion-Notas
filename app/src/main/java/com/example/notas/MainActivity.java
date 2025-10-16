package com.example.notas;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<NotasModel> notasList;
    NotasAdapter notasAdapter;

    Button btnAñadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnAñadir = findViewById(R.id.button3);

        notasList = new ArrayList<>();
        notasAdapter = new NotasAdapter(this, notasList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notasAdapter);

        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearNueva();
            }
        });
    }

    private void CrearNueva(){
        final EditText editText = new EditText(this);
        editText.setHint("Introduce el titulo");

        new AlertDialog.Builder(this)
                .setTitle("Nueva nota:")
                .setMessage("Introduce una nueva nota:")
                .setView(editText)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    String titulo = editText.getText().toString().trim();
                    if (!titulo.isEmpty()) {
                        notasList.add(new NotasModel(titulo, ""));
                        notasAdapter.notifyItemInserted(notasList.size() - 1);
                    } else {
                        Toast.makeText(this, "El título no puede estar vacío", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}