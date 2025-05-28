package com.katsukosail.soundbox.database.model;

public class Cancion {
    private int idCancion;
    private int idAlbum;
    private int idGenero;
    private String nombre;
    private int duracion; // segundos
    private String link;

    public Cancion(int idCancion, int idAlbum, int idGenero, String nombre, int duracion, String link) {
        this.idCancion = idCancion;
        this.idAlbum = idAlbum;
        this.idGenero = idGenero;
        this.nombre = nombre;
        this.duracion = duracion;
        this.link = link;
    }

    public Cancion(int idAlbum, int idGenero, String nombre, int duracion, String link) {
        this.idAlbum = idAlbum;
        this.idGenero = idGenero;
        this.nombre = nombre;
        this.duracion = duracion;
        this.link = link;
    }

    public int getIdCancion() { return idCancion; }
    public void setIdCancion(int idCancion) { this.idCancion = idCancion; }

    public int getIdAlbum() { return idAlbum; }
    public void setIdAlbum(int idAlbum) { this.idAlbum = idAlbum; }

    public int getIdGenero() { return idGenero; }
    public void setIdGenero(int idGenero) { this.idGenero = idGenero; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    @Override
    public String toString() {
        return "Cancion{" +
                "idCancion=" + idCancion +
                ", idAlbum=" + idAlbum +
                ", idGenero=" + idGenero +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", link='" + link + '\'' +
                '}';
    }
}

