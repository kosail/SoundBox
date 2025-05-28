package com.katsukosail.soundbox.database.dao

import com.katsukosail.soundbox.database.DatabaseConnection.connect
import com.katsukosail.soundbox.database.model.Playlist
import java.sql.Connection
import java.sql.Date
import java.sql.SQLException
import java.sql.Statement

class PlaylistDAO {
    @Throws(SQLException::class)
    fun insert(playlist: Playlist, conn: Connection) {
        val sql = "INSERT INTO Playlist (pl_nombre, pl_fecha, pl_desc) VALUES (?, ?, ?)"
        conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { pstmt ->
            pstmt.setString(1, playlist.nombre)
            pstmt.setDate(2, playlist.fecha as Date?)
            pstmt.setString(3, playlist.descripcion)
            pstmt.executeUpdate()
            pstmt.getGeneratedKeys().use { generatedKeys ->
                if (generatedKeys.next()) {
                    playlist.idPlaylist = generatedKeys.getInt(1)
                }
            }
        }
    }

    fun getAll(conn: Connection): MutableList<Playlist?> {
        val lista: MutableList<Playlist?> = ArrayList<Playlist?>()
        val sql = "SELECT * FROM Playlist"
        try {
            conn.createStatement().use { st ->
                st.executeQuery(sql).use { rs ->
                    while (rs.next()) {
                        lista.add(
                            Playlist(
                                rs.getInt("id_playlist"),
                                rs.getString("pl_nombre"),
                                rs.getDate("pl_fecha"),
                                rs.getString("pl_desc")
                            )
                        )
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error obteniendo playlists: " + e.message)
        }
        return lista
    }

    fun findById(id: Int): Playlist? {
        val sql = "SELECT * FROM Playlist WHERE id_playlist = ?"
        try {
            connect().use { conn ->
                conn!!.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    stmt.executeQuery().use { rs ->
                        if (rs.next()) {
                            return Playlist(
                                rs.getInt("id_playlist"),
                                rs.getString("pl_nombre"),
                                rs.getDate("pl_fecha"),
                                rs.getString("pl_desc")
                            )
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error buscando playlist por ID: " + e.message)
        }
        return null
    }


    fun deleteById(id: Int, conn: Connection) {
        val sql = "DELETE FROM Playlist WHERE id_playlist = ?"
        try {
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, id)
                pstmt.executeUpdate()
            }
        } catch (e: SQLException) {
            println("Error eliminando playlist: " + e.message)
        }
    }

    fun update(playlist: Playlist, conn: Connection) {
        val sql = "UPDATE Playlist SET p_nombre = ?, pl_fecha = ?, pl_desc = ? WHERE id_playlist = ?"
        try {
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setString(1, playlist.nombre)
                pstmt.setDate(2, playlist.fecha as Date?)
                pstmt.setString(3, playlist.descripcion)
                pstmt.setInt(4, playlist.idPlaylist)
                pstmt.executeUpdate()
            }
        } catch (e: SQLException) {
            println("Error actualizando playlist: " + e.message)
        }
    }
}
