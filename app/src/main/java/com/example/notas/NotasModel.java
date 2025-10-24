package com.example.notas;

import android.graphics.Color;

public class NotasModel {
    public String Titulo;
    public String Contenido;
    private boolean Segura;
    private String Contraseña;

    private int Color;

    // Constructor
    public NotasModel(String titulo, String contenido, int color) {
        Titulo = titulo;
        Contenido = contenido;
        Segura = false;
        Contraseña = "";
        Color = color;
    }

    // Getters y setters para Titulo y Contenido
    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        Contenido = contenido;
    }

    // Getters y setters para seguridad
    public boolean isSegura() {
        return Segura;
    }

    public void setSegura(boolean segura) {
        Segura = segura;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }
}