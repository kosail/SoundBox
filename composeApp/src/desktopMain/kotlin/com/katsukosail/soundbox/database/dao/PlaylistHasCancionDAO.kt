package com.katsukosail.soundbox.database.dao

import com.katsukosail.soundbox.database.DatabaseConnection.connect
import com.katsukosail.soundbox.database.model.PlaylistHasCancion
import java.sql.Connection
import java.sql.SQLException

class PlaylistHasCancionDAO {
    @Throws(SQLException::class)
    fun insert(phc: PlaylistHasCancion, conn: Connection) {
        val sql = "INSERT INTO Playlist_has_Cancion (id_playlist, id_cancion) VALUES (?, ?)"
        conn.prepareStatement(sql).use { pstmt ->
            pstmt.setInt(1, phc.idPlaylist)
            pstmt.setInt(2, phc.idCancion)
            pstmt.executeUpdate()
        }
    }

    fun getAll(conn: Connection): MutableList<PlaylistHasCancion?> {
        val lista: MutableList<PlaylistHasCancion?> = ArrayList<PlaylistHasCancion?>()
        val sql = "SELECT * FROM Playlist_has_Cancion"
        try {
            conn.createStatement().use { st ->
                st.executeQuery(sql).use { rs ->
                    while (rs.next()) {
                        lista.add(
                            PlaylistHasCancion(
                                rs.getInt("id_playlist"),
                                rs.getInt("id_cancion")
                            )
                        )
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error obteniendo relaciones playlist-cancion: " + e.message)
        }
        return lista
    }

    fun findById(idPlaylist: Int, idCancion: Int): PlaylistHasCancion? {
        val sql = "SELECT * FROM Playlist_has_Cancion WHERE id_playlist = ? AND id_cancion = ?"
        try {
            connect().use { conn ->
                conn!!.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, idPlaylist)
                    stmt.setInt(2, idCancion)
                    stmt.executeQuery().use { rs ->
                        if (rs.next()) {
                            return PlaylistHasCancion(
                                rs.getInt("id_playlist"),
                                rs.getInt("id_cancion")
                            )
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error buscando relación playlist-canción: " + e.message)
        }
        return null
    }

    fun deleteById(idPlaylist: Int, idCancion: Int, conn: Connection) {
        val sql = "DELETE FROM Playlist_has_Cancion WHERE id_playlist = ? AND id_cancion = ?"
        try {
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, idPlaylist)
                pstmt.setInt(2, idCancion)
                pstmt.executeUpdate()
            }
        } catch (e: SQLException) {
            println("Error eliminando relación playlist-canción: " + e.message)
        }
    }
}
