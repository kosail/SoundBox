package com.katsukosail.soundbox.database.model;

public class Artista {
    private int idArtista;
    private String nombre;
    private byte[] portada;

    public Artista(String nombre, byte[] portada) {
        this.nombre = nombre;
        this.portada = portada;
    }
    public Artista(int idArtista, String nombre, byte[] portada) {
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.portada = portada;
    }

    public int getIdArtista() {
        return idArtista;
    }
    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getPortada() {
        return portada;
    }
    public void setPortada(byte[] portada) {
        this.portada = portada;
    }

    @Override
    public String toString() {
        return "Artista{id=" + idArtista + ", nombre='" + nombre + "'}";
    }

}
