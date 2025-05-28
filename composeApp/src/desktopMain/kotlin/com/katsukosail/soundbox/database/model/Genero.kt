package com.katsukosail.soundbox.database.model

class Genero {
    var idGenero: Int = 0
    var descripcion: String?

    constructor(idGenero: Int, descripcion: String?) {
        this.idGenero = idGenero
        this.descripcion = descripcion
    }

    constructor(descripcion: String?) {
        this.descripcion = descripcion
    }

    override fun toString(): String {
        return "Genero{" +
                "idGenero=" + idGenero +
                ", descripcion='" + descripcion + '\'' +
                '}'
    }
}
