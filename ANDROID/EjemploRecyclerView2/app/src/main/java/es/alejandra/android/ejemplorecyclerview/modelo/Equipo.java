package es.alejandra.android.ejemplorecyclerview.modelo;

import android.graphics.drawable.Drawable;

public class Equipo {
    private String nombre;
    private Drawable escudo;
    private int puntos;
    private int numeroJugadores;

    public Equipo(String nombre, Drawable escudo, int puntos, int numeroJugadores) {
        this.nombre = nombre;
        this.escudo = escudo;
        this.puntos = puntos;
        this.numeroJugadores = numeroJugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Drawable getEscudo() {
        return escudo;
    }

    public void setEscudo(Drawable escudo) {
        this.escudo = escudo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", escudo=" + escudo +
                ", puntos=" + puntos +
                ", numeroJugadores=" + numeroJugadores +
                '}';
    }
}
