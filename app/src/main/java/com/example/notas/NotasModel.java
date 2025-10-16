package com.example.notas;

public class NotasModel {
    public String Titulo;
    public String Contenido;
    private boolean Segura;
    private String Contraseña;

    // Constructor
    public NotasModel(String titulo, String contenido) {
        Titulo = titulo;
        Contenido = contenido;
        Segura = false;
        Contraseña = "";
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
}