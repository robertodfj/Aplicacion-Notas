package com.example.notas;

public class NotasModel {
    public String Titulo;
    public String Descripcion;

    public NotasModel(String titulo, String descripcion) {
        Titulo = titulo;
        Descripcion = descripcion;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

}
