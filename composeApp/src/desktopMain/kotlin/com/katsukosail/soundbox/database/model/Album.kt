package com.katsukosail.soundbox.database.model

import java.util.*

class Album {
    var idAlbum: Int = 0
    var idArtista: Int
    var nombre: String?
    var fecha: Date?
    var portada: ByteArray?

    constructor(idAlbum: Int, idArtista: Int, nombre: String?, fecha: Date?, portada: ByteArray?) {
        this.idAlbum = idAlbum
        this.idArtista = idArtista
        this.nombre = nombre
        this.fecha = fecha
        this.portada = portada
    }

    constructor(idArtista: Int, nombre: String?, fecha: Date?, portada: ByteArray?) {
        this.idArtista = idArtista
        this.nombre = nombre
        this.fecha = fecha
        this.portada = portada
    }

    override fun toString(): String {
        return "Album{" +
                "idAlbum=" + idAlbum +
                ", idArtista=" + idArtista +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", portada=" + portada.contentToString() +
                '}'
    }
}
