package com.katsukosail.soundbox.database.model;

import java.util.Date;

public class Playlist {
    private int idPlaylist;
    private String nombre;
    private Date fecha;
    private String descripcion;

    public Playlist(int idPlaylist, String nombre, Date fecha, String descripcion) {
        this.idPlaylist = idPlaylist;
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Playlist(String nombre, Date fecha, String descripcion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public int getIdPlaylist() { return idPlaylist; }
    public void setIdPlaylist(int idPlaylist) { this.idPlaylist = idPlaylist; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return "Playlist{" +
                "idPlaylist=" + idPlaylist +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
