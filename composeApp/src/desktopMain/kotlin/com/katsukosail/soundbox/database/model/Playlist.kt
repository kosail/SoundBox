package com.katsukosail.soundbox.database.model

import java.util.*

class Playlist {
    var idPlaylist: Int = 0
    var nombre: String?
    var fecha: Date?
    var descripcion: String?

    constructor(idPlaylist: Int, nombre: String?, fecha: Date?, descripcion: String?) {
        this.idPlaylist = idPlaylist
        this.nombre = nombre
        this.fecha = fecha
        this.descripcion = descripcion
    }

    constructor(nombre: String?, fecha: Date?, descripcion: String?) {
        this.nombre = nombre
        this.fecha = fecha
        this.descripcion = descripcion
    }

    override fun toString(): String {
        return "Playlist{" +
                "idPlaylist=" + idPlaylist +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                '}'
    }
}
