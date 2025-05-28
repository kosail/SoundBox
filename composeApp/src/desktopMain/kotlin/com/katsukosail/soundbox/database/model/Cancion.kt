package com.katsukosail.soundbox.database.model

class Cancion {
    var idCancion: Int = 0
    var idAlbum: Int
    var idGenero: Int
    var nombre: String?
    var duracion: Int // segundos
    var link: String?

    constructor(idCancion: Int, idAlbum: Int, idGenero: Int, nombre: String?, duracion: Int, link: String?) {
        this.idCancion = idCancion
        this.idAlbum = idAlbum
        this.idGenero = idGenero
        this.nombre = nombre
        this.duracion = duracion
        this.link = link
    }

    constructor(idAlbum: Int, idGenero: Int, nombre: String?, duracion: Int, link: String?) {
        this.idAlbum = idAlbum
        this.idGenero = idGenero
        this.nombre = nombre
        this.duracion = duracion
        this.link = link
    }

    override fun toString(): String {
        return "Cancion{" +
                "idCancion=" + idCancion +
                ", idAlbum=" + idAlbum +
                ", idGenero=" + idGenero +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", link='" + link + '\'' +
                '}'
    }
}

