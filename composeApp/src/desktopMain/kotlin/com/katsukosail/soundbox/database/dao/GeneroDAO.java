package com.katsukosail.soundbox.database.dao;

import com.katsukosail.soundbox.database.DatabaseConnection;
import com.katsukosail.soundbox.database.model.Genero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {

    public void insert(Genero genero, Connection conn) throws SQLException {
        String sql = "INSERT INTO Genero (ge_desc) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, genero.getDescripcion());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    genero.setIdGenero(generatedKeys.getInt(1));
                }
            }
        }
    }
    public List<Genero> getAll(Connection conn) {
        List<Genero> lista = new ArrayList<>();
        String sql = "SELECT * FROM Genero";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Genero(
                        rs.getInt("id_genero"),
                        rs.getString("ge_desc")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo géneros: " + e.getMessage());
        }
        return lista;
    }
    public Genero findById(int id) {
        String sql = "SELECT * FROM Genero WHERE id_genero = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Genero(
                            rs.getInt("id_genero"),
                            rs.getString("ge_desc")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error buscando genero por ID: " + e.getMessage());
        }
        return null;
    }

    public void deleteById(int id, Connection conn) {
        String sql = "DELETE FROM Genero WHERE id_genero = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error eliminando género: " + e.getMessage());
        }
    }

    public void update(Genero genero, Connection conn) {
        String sql = "UPDATE Genero SET ge_desc = ? WHERE id_genero = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, genero.getDescripcion());
            pstmt.setInt(2, genero.getIdGenero());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizando género: " + e.getMessage());
        }
    }


}
