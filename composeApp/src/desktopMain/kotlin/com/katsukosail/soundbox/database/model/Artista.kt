package com.katsukosail.soundbox.database.model

class Artista {
    var idArtista: Int = 0
    var nombre: String?
    var portada: ByteArray?

    constructor(nombre: String?, portada: ByteArray?) {
        this.nombre = nombre
        this.portada = portada
    }

    constructor(idArtista: Int, nombre: String?, portada: ByteArray?) {
        this.idArtista = idArtista
        this.nombre = nombre
        this.portada = portada
    }

    override fun toString(): String {
        return "Artista{id=" + idArtista + ", nombre='" + nombre + "'}"
    }
}
