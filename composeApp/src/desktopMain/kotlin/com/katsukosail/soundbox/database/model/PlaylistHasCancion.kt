package com.katsukosail.soundbox.database.model

class PlaylistHasCancion(var idPlaylist: Int, var idCancion: Int) {
    override fun toString(): String {
        return "PlaylistHasCancion{" +
                "idPlaylist=" + idPlaylist +
                ", idCancion=" + idCancion +
                '}'
    }
}
