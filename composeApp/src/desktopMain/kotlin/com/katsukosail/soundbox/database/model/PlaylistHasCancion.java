package com.katsukosail.soundbox.database.model;

public class PlaylistHasCancion {
    private int idPlaylist;
    private int idCancion;

    public PlaylistHasCancion(int idPlaylist, int idCancion) {
        this.idPlaylist = idPlaylist;
        this.idCancion = idCancion;
    }

    public int getIdPlaylist() {
        return idPlaylist;
    }
    public void setIdPlaylist(int idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public int getIdCancion() {
        return idCancion;
    }
    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    @Override
    public String toString() {
        return "PlaylistHasCancion{" +
                "idPlaylist=" + idPlaylist +
                ", idCancion=" + idCancion +
                '}';
    }
}
