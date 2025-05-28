package com.katsukosail.soundbox.database.model;

import java.util.Arrays;
import java.util.Date;

public class Album {
    private int idAlbum;
    private int idArtista;
    private String nombre;
    private Date fecha;
    private byte[] portada;

    public Album(int idAlbum, int idArtista, String nombre, Date fecha, byte[] portada) {
        this.idAlbum = idAlbum;
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.fecha = fecha;
        this.portada = portada;
    }

    public Album(int idArtista, String nombre, Date fecha, byte[] portada) {
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.fecha = fecha;
        this.portada = portada;
    }

    public int getIdAlbum() {
        return idAlbum;
    }
    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
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

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public byte[] getPortada() {
        return portada;
    }

    public void setPortada(byte[] portada) {
        this.portada = portada;
    }

    @Override
    public String toString() {
        return "Album{" +
                "idAlbum=" + idAlbum +
                ", idArtista=" + idArtista +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", portada=" + Arrays.toString(portada) +
                '}';
    }
}
