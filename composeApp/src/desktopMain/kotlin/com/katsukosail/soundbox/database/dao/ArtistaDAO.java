package com.katsukosail.soundbox.database.dao;

import com.katsukosail.soundbox.database.DatabaseConnection;
import com.katsukosail.soundbox.database.model.Artista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaDAO{

    public void insert(Artista artista, Connection conn) throws SQLException {
        String sql = "INSERT INTO Artista (a_nombre, a_portada) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, artista.getNombre());
            pstmt.setBytes(2, artista.getPortada());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    artista.setIdArtista(generatedKeys.getInt(1));
                }
            }
        }
    }


    public void deleteById(int id, Connection conn) {
        String sql = "DELETE FROM Artista WHERE id_artista = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error eliminando artista: " + e.getMessage());
        }
    }

    public void update(Artista artista, Connection conn) {
        String sql = "UPDATE Artista SET a_nombre = ?, a_portada = ? WHERE id_artista = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, artista.getNombre());
            pstmt.setBytes(2, artista.getPortada());
            pstmt.setInt(3, artista.getIdArtista());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizando artista: " + e.getMessage());
        }
    }

    public Artista findById(int id) {
        String sql = "SELECT * FROM Artista WHERE id_artista = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Artista(
                            rs.getInt("id_artista"),
                            rs.getString("a_nombre"),
                            rs.getBytes("a_portada")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error buscando artista por ID: " + e.getMessage());
        }
        return null;
    }

    public Artista getById(int id) {
        String sql = "SELECT * FROM Artista WHERE id_artista = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Artista(
                        rs.getInt("id_artista"),
                        rs.getString("a_nombre"),
                        rs.getBytes("a_portada")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo artista: " + e.getMessage());
        }
        return null;
    }


    public List<Artista> getAll(Connection conn) {
        List<Artista> lista = new ArrayList<>();
        String sql = "SELECT * FROM Artista";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Artista(
                        rs.getInt("id_artista"),
                        rs.getString("a_nombre"),
                        rs.getBytes("a_portada")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo artistas: " + e.getMessage());
        }
        return lista;
    }

}
