package com.katsukosail.soundbox.database.dao

import com.katsukosail.soundbox.database.DatabaseConnection.connect
import com.katsukosail.soundbox.database.model.Artista
import java.sql.Connection
import java.sql.SQLException
import java.sql.Statement

class ArtistaDAO {
    @Throws(SQLException::class)
    fun insert(artista: Artista, conn: Connection) {
        val sql = "INSERT INTO Artista (a_nombre, a_portada) VALUES (?, ?)"
        conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { pstmt ->
            pstmt.setString(1, artista.nombre)
            pstmt.setBytes(2, artista.portada)
            pstmt.executeUpdate()
            pstmt.getGeneratedKeys().use { generatedKeys ->
                if (generatedKeys.next()) {
                    artista.idArtista = generatedKeys.getInt(1)
                }
            }
        }
    }


    fun deleteById(id: Int, conn: Connection) {
        val sql = "DELETE FROM Artista WHERE id_artista = ?"
        try {
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, id)
                pstmt.executeUpdate()
            }
        } catch (e: SQLException) {
            println("Error eliminando artista: " + e.message)
        }
    }

    fun update(artista: Artista, conn: Connection) {
        val sql = "UPDATE Artista SET a_nombre = ?, a_portada = ? WHERE id_artista = ?"
        try {
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setString(1, artista.nombre)
                pstmt.setBytes(2, artista.portada)
                pstmt.setInt(3, artista.idArtista)
                pstmt.executeUpdate()
            }
        } catch (e: SQLException) {
            println("Error actualizando artista: " + e.message)
        }
    }

    fun findById(id: Int): Artista? {
        val sql = "SELECT * FROM Artista WHERE id_artista = ?"
        try {
            connect().use { conn ->
                conn!!.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    stmt.executeQuery().use { rs ->
                        if (rs.next()) {
                            return Artista(
                                rs.getInt("id_artista"),
                                rs.getString("a_nombre"),
                                rs.getBytes("a_portada")
                            )
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error buscando artista por ID: " + e.message)
        }
        return null
    }

    fun getById(id: Int): Artista? {
        val sql = "SELECT * FROM Artista WHERE id_artista = ?"
        try {
            connect().use { conn ->
                conn!!.prepareStatement(sql).use { ps ->
                    ps.setInt(1, id)
                    val rs = ps.executeQuery()
                    if (rs.next()) {
                        return Artista(
                            rs.getInt("id_artista"),
                            rs.getString("a_nombre"),
                            rs.getBytes("a_portada")
                        )
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error obteniendo artista: " + e.message)
        }
        return null
    }


    fun getAll(conn: Connection): MutableList<Artista?> {
        val lista: MutableList<Artista?> = ArrayList<Artista?>()
        val sql = "SELECT * FROM Artista"
        try {
            conn.createStatement().use { st ->
                st.executeQuery(sql).use { rs ->
                    while (rs.next()) {
                        lista.add(
                            Artista(
                                rs.getInt("id_artista"),
                                rs.getString("a_nombre"),
                                rs.getBytes("a_portada")
                            )
                        )
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error obteniendo artistas: " + e.message)
        }
        return lista
    }
}
