package com.katsukosail.soundbox.database.dao;

import com.katsukosail.soundbox.database.DatabaseConnection;
import com.katsukosail.soundbox.database.model.Album;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AlbumDAO {
    public void insert(Album album, Connection conn) throws SQLException {
        String sql = "INSERT INTO Album (id_artista, al_nombre, al_fecha, al_portada) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, album.getIdArtista());
            pstmt.setString(2, album.getNombre());
            pstmt.setDate(3, (Date) album.getFecha());
            pstmt.setBytes(4, album.getPortada());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    album.setIdAlbum(generatedKeys.getInt(1));
                }
            }
        }
    }
    public List<Album> getAll(Connection conn) {
        List<Album> lista = new ArrayList<>();
        String sql = "SELECT * FROM Album";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Album(
                        rs.getInt("id_album"),
                        rs.getInt("id_artista"),
                        rs.getString("al_nombre"),
                        rs.getDate("al_fecha"),
                        rs.getBytes("al_portada")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo álbumes: " + e.getMessage());
        }
        return lista;
    }

    public Album findById(int id) {
        String sql = "SELECT * FROM Album WHERE id_album = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Album(
                            rs.getInt("id_album"),
                            rs.getInt("id_artista"),
                            rs.getString("al_nombre"),
                            rs.getDate("al_fecha"),
                            rs.getBytes("al_portada")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error buscando album por ID: " + e.getMessage());
        }
        return null;
    }

    public void deleteById(int id, Connection conn) {
        String sql = "DELETE FROM Album WHERE id_album = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error eliminando álbum: " + e.getMessage());
        }
    }

    public void update(Album album, Connection conn) {
        String sql = "UPDATE Album SET id_artista = ?, al_nombre = ?, al_fecha = ?, al_portada = ? WHERE id_album = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, album.getIdArtista());
            pstmt.setString(2, album.getNombre());
            pstmt.setDate(3, (Date) album.getFecha());
            pstmt.setBytes(4, album.getPortada());
            pstmt.setInt(5, album.getIdAlbum());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizando álbum: " + e.getMessage());
        }
    }


}
