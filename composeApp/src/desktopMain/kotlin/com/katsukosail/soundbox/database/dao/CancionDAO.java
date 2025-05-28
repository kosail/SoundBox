package com.katsukosail.soundbox.database.dao;

import com.katsukosail.soundbox.database.DatabaseConnection;
import com.katsukosail.soundbox.database.model.Cancion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CancionDAO {
    public void insert(Cancion cancion, Connection conn) throws SQLException {
        String sql = "INSERT INTO Cancion (id_album, id_genero, c_nombre, c_duracion, c_link) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, cancion.getIdAlbum());
            pstmt.setInt(2, cancion.getIdGenero());
            pstmt.setString(3, cancion.getNombre());
            pstmt.setInt(4, cancion.getDuracion());
            pstmt.setString(5, cancion.getLink());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cancion.setIdCancion(generatedKeys.getInt(1));
                }
            }
        }
    }
    public List<Cancion> getAll(Connection conn) {
        List<Cancion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cancion";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Cancion(
                        rs.getInt("id_cancion"),
                        rs.getInt("id_album"),
                        rs.getInt("id_genero"),
                        rs.getString("c_nombre"),
                        rs.getInt("c_duracion"),
                        rs.getString("c_link")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo canciones: " + e.getMessage());
        }
        return lista;
    }

    public Cancion findById(int id) {
        String sql = "SELECT * FROM Cancion WHERE id_cancion = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cancion(
                            rs.getInt("id_cancion"),
                            rs.getInt("id_album"),
                            rs.getInt("id_genero"),
                            rs.getString("c_nombre"),
                            rs.getInt("c_duracion"),
                            rs.getString("c_link")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error buscando cancion por ID: " + e.getMessage());
        }
        return null;
    }


    public void deleteById(int id, Connection conn) {
        String sql = "DELETE FROM Cancion WHERE id_cancion = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error eliminando canción: " + e.getMessage());
        }
    }

    public void update(Cancion cancion, Connection conn) {
        String sql = "UPDATE Cancion SET id_album = ?, id_genero = ?, c_nombre = ?, c_duracion = ?, c_link = ? WHERE id_cancion = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cancion.getIdAlbum());
            pstmt.setInt(2, cancion.getIdGenero());
            pstmt.setString(3, cancion.getNombre());
            pstmt.setInt(4, cancion.getDuracion());
            pstmt.setString(5, cancion.getLink());
            pstmt.setInt(6, cancion.getIdCancion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizando canción: " + e.getMessage());
        }
    }


}
