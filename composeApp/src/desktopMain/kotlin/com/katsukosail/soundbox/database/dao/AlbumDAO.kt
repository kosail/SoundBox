package com.katsukosail.soundbox.database.dao

import com.katsukosail.soundbox.database.DatabaseConnection.connect
import com.katsukosail.soundbox.database.model.Album
import java.sql.Connection
import java.sql.Date
import java.sql.SQLException
import java.sql.Statement

class AlbumDAO {
    @Throws(SQLException::class)
    fun insert(album: Album, conn: Connection) {
        val sql = "INSERT INTO Album (id_artista, al_nombre, al_fecha, al_portada) VALUES (?, ?, ?, ?)"
        conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { pstmt ->
            pstmt.setInt(1, album.idArtista)
            pstmt.setString(2, album.nombre)
            pstmt.setDate(3, album.fecha as Date?)
            pstmt.setBytes(4, album.portada)
            pstmt.executeUpdate()
            pstmt.getGeneratedKeys().use { generatedKeys ->
                if (generatedKeys.next()) {
                    album.idAlbum = generatedKeys.getInt(1)
                }
            }
        }
    }

    fun getAll(conn: Connection): MutableList<Album?> {
        val lista: MutableList<Album?> = ArrayList<Album?>()
        val sql = "SELECT * FROM Album"
        try {
            conn.createStatement().use { st ->
                st.executeQuery(sql).use { rs ->
                    while (rs.next()) {
                        lista.add(
                            Album(
                                rs.getInt("id_album"),
                                rs.getInt("id_artista"),
                                rs.getString("al_nombre"),
                                rs.getDate("al_fecha"),
                                rs.getBytes("al_portada")
                            )
                        )
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error obteniendo álbumes: " + e.message)
        }
        return lista
    }

    fun findById(id: Int): Album? {
        val sql = "SELECT * FROM Album WHERE id_album = ?"
        try {
            connect().use { conn ->
                conn!!.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    stmt.executeQuery().use { rs ->
                        if (rs.next()) {
                            return Album(
                                rs.getInt("id_album"),
                                rs.getInt("id_artista"),
                                rs.getString("al_nombre"),
                                rs.getDate("al_fecha"),
                                rs.getBytes("al_portada")
                            )
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error buscando album por ID: " + e.message)
        }
        return null
    }

    fun deleteById(id: Int, conn: Connection) {
        val sql = "DELETE FROM Album WHERE id_album = ?"
        try {
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, id)
                pstmt.executeUpdate()
            }
        } catch (e: SQLException) {
            println("Error eliminando álbum: " + e.message)
        }
    }

    fun update(album: Album, conn: Connection) {
        val sql = "UPDATE Album SET id_artista = ?, al_nombre = ?, al_fecha = ?, al_portada = ? WHERE id_album = ?"
        try {
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, album.idArtista)
                pstmt.setString(2, album.nombre)
                pstmt.setDate(3, album.fecha as Date?)
                pstmt.setBytes(4, album.portada)
                pstmt.setInt(5, album.idAlbum)
                pstmt.executeUpdate()
            }
        } catch (e: SQLException) {
            println("Error actualizando álbum: " + e.message)
        }
    }
}
