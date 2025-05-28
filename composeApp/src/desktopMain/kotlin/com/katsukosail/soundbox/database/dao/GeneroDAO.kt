package com.katsukosail.soundbox.database.dao

import com.katsukosail.soundbox.database.DatabaseConnection.connect
import com.katsukosail.soundbox.database.model.Genero
import java.sql.Connection
import java.sql.SQLException
import java.sql.Statement

class GeneroDAO {
    @Throws(SQLException::class)
    fun insert(genero: Genero, conn: Connection) {
        val sql = "INSERT INTO Genero (ge_desc) VALUES (?)"
        conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { pstmt ->
            pstmt.setString(1, genero.descripcion)
            pstmt.executeUpdate()
            pstmt.getGeneratedKeys().use { generatedKeys ->
                if (generatedKeys.next()) {
                    genero.idGenero = generatedKeys.getInt(1)
                }
            }
        }
    }

    fun getAll(conn: Connection): MutableList<Genero?> {
        val lista: MutableList<Genero?> = ArrayList<Genero?>()
        val sql = "SELECT * FROM Genero"
        try {
            conn.createStatement().use { st ->
                st.executeQuery(sql).use { rs ->
                    while (rs.next()) {
                        lista.add(
                            Genero(
                                rs.getInt("id_genero"),
                                rs.getString("ge_desc")
                            )
                        )
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error obteniendo géneros: " + e.message)
        }
        return lista
    }

    fun findById(id: Int): Genero? {
        val sql = "SELECT * FROM Genero WHERE id_genero = ?"
        try {
            connect().use { conn ->
                conn!!.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    stmt.executeQuery().use { rs ->
                        if (rs.next()) {
                            return Genero(
                                rs.getInt("id_genero"),
                                rs.getString("ge_desc")
                            )
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            println("Error buscando genero por ID: " + e.message)
        }
        return null
    }

    fun deleteById(id: Int, conn: Connection) {
        val sql = "DELETE FROM Genero WHERE id_genero = ?"
        try {
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, id)
                pstmt.executeUpdate()
            }
        } catch (e: SQLException) {
            println("Error eliminando género: " + e.message)
        }
    }

    fun update(genero: Genero, conn: Connection) {
        val sql = "UPDATE Genero SET ge_desc = ? WHERE id_genero = ?"
        try {
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setString(1, genero.descripcion)
                pstmt.setInt(2, genero.idGenero)
                pstmt.executeUpdate()
            }
        } catch (e: SQLException) {
            println("Error actualizando género: " + e.message)
        }
    }
}
