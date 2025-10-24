package com.example.notas;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class VistaNotas extends AppCompatActivity {

    EditText editTitulo, editContenido;
    Button btnGuardar, btnExportar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_notas);

        editTitulo = findViewById(R.id.editTitulo);
        editContenido = findViewById(R.id.editContenido);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnExportar = findViewById(R.id.btnExportar);

        String titulo = getIntent().getStringExtra("titulo");
        String contenido = getIntent().getStringExtra("contenido");
        int posicion = getIntent().getIntExtra("posicion", -1);

        editTitulo.setText(titulo);
        editContenido.setText(contenido);

        // Pedir permiso para escribir en almacenamiento (solo si es necesario)
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoTitulo = editTitulo.getText().toString().trim();
                String nuevoContenido = editContenido.getText().toString().trim();

                if (nuevoTitulo.isEmpty()) {
                    Toast.makeText(VistaNotas.this, "El título no puede estar vacío", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Devolver los datos actualizados a MainActivity
                getIntent().putExtra("titulo", nuevoTitulo);
                getIntent().putExtra("contenido", nuevoContenido);
                getIntent().putExtra("posicion", posicion);
                setResult(RESULT_OK, getIntent());
                finish();

            }
        });

        //  Guardar archivo en Descargas
        btnExportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(VistaNotas.this);
                editText.setHint("¿Cómo quieres llamar a tu archivo?");

                new AlertDialog.Builder(VistaNotas.this)
                        .setTitle("Exportar archivo")
                        .setMessage("Introduce un nombre para la nota:")
                        .setView(editText)
                        .setNegativeButton("Cancelar", null)
                        .setPositiveButton("Guardar", (dialog, which) -> {
                            String nombre = editText.getText().toString().trim();
                            if (!nombre.isEmpty()) {
                                String nombreArchivo = nombre + ".txt";
                                String contenido = editContenido.getText().toString();

                                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                                    File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                                    File file = new File(dir, nombreArchivo);

                                    try {
                                        FileOutputStream fs = new FileOutputStream(file);
                                        fs.write(contenido.getBytes());
                                        fs.close();

                                        Toast.makeText(VistaNotas.this, "Archivo guardado en Descargas ✅", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Toast.makeText(VistaNotas.this, "Error al guardar el archivo ❌", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(VistaNotas.this, "El almacenamiento no está disponible", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(VistaNotas.this, "El título no puede estar vacío", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    // Respuesta al responder a la peticion de permiso
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido ✅", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso denegado ❌", Toast.LENGTH_SHORT).show();
            }
        }
    }
}