package com.katsukosail.soundbox.database.dao

import com.katsukosail.soundbox.database.DatabaseConnection.connect
import com.katsukosail.soundbox.database.model.Cancion
import java.sql.Connection
import java.sql.SQLException
import java.sql.Statement

class CancionDAO {
    @Throws(SQLException::class)
    fun insert(cancion: Cancion, conn: Connection) {
        val sql = "INSERT INTO Cancion (id_album, id_genero, c_nombre, c_duracion, c_link) VALUES (?, ?, ?, ?, ?)"
        conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { pstmt ->
            pstmt.setInt(1, cancion.idAlbum)
            pstmt.setInt(2, cancion.idGenero)
            pstmt.setString(3, cancion.nombre)
            pstmt.setInt(4, cancion.duracion)
            pstmt.setString(5, cancion.link)
            pstmt.executeUpdate()
            pstmt.getGeneratedKeys().use { generatedKeys ->
                if (generatedKeys.next()) {
                    cancion.idCancion = generatedKeys.getInt(1)
                }
            }
        }
    }

    fun getAll(conn: Connection): MutableList<Cancion?> {
        val lista: MutableList<Cancion?> = ArrayList<Cancion?>()
        val sql = "SELECT * FROM Cancion"
        try {
            conn.createStatement().use { st ->
                st.executeQuery(sql).use { rs ->
                    while (rs.next()) {
                        lista.add(
                            Cancion(
                                rs.getInt("id_cancion"),
                                rs.getInt("id_album"),
                                rs.getInt("id_genero"),
                                rs.getString("c_nombre"),
                                rs.getInt("c_duracion"),
                                rs.getString("c_link")
                            )
                        )
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error obteniendo canciones: " + e.message)
        }
        return lista
    }

    fun findById(id: Int): Cancion? {
        val sql = "SELECT * FROM Cancion WHERE id_cancion = ?"
        try {
            connect().use { conn ->
                conn!!.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    stmt.executeQuery().use { rs ->
                        if (rs.next()) {
                            return Cancion(
                                rs.getInt("id_cancion"),
                                rs.getInt("id_album"),
                                rs.getInt("id_genero"),
                                rs.getString("c_nombre"),
                                rs.getInt("c_duracion"),
                                rs.getString("c_link")
                            )
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error buscando cancion por ID: " + e.message)
        }
        return null
    }


    fun deleteById(id: Int, conn: Connection) {
        val sql = "DELETE FROM Cancion WHERE id_cancion = ?"
        try {
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, id)
                pstmt.executeUpdate()
            }
        } catch (e: SQLException) {
            println("Error eliminando canción: " + e.message)
        }
    }

    fun update(cancion: Cancion, conn: Connection) {
        val sql =
            "UPDATE Cancion SET id_album = ?, id_genero = ?, c_nombre = ?, c_duracion = ?, c_link = ? WHERE id_cancion = ?"
        try {
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, cancion.idAlbum)
                pstmt.setInt(2, cancion.idGenero)
                pstmt.setString(3, cancion.nombre)
                pstmt.setInt(4, cancion.duracion)
                pstmt.setString(5, cancion.link)
                pstmt.setInt(6, cancion.idCancion)
                pstmt.executeUpdate()
            }
        } catch (e: SQLException) {
            println("Error actualizando canción: " + e.message)
        }
    }
}
