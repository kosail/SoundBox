package com.katsukosail.soundbox.database.dao;

import com.katsukosail.soundbox.database.DatabaseConnection;
import com.katsukosail.soundbox.database.model.Playlist;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlaylistDAO {
    public void insert(Playlist playlist, Connection conn) throws SQLException {
        String sql = "INSERT INTO Playlist (pl_nombre, pl_fecha, pl_desc) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, playlist.getNombre());
            pstmt.setDate(2, (Date) playlist.getFecha());
            pstmt.setString(3, playlist.getDescripcion());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    playlist.setIdPlaylist(generatedKeys.getInt(1));
                }
            }
        }
    }
    public List<Playlist> getAll(Connection conn) {
        List<Playlist> lista = new ArrayList<>();
        String sql = "SELECT * FROM Playlist";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Playlist(
                        rs.getInt("id_playlist"),
                        rs.getString("pl_nombre"),
                        rs.getDate("pl_fecha"),
                        rs.getString("pl_desc")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo playlists: " + e.getMessage());
        }
        return lista;
    }

    public Playlist findById(int id) {
        String sql = "SELECT * FROM Playlist WHERE id_playlist = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Playlist(
                            rs.getInt("id_playlist"),
                            rs.getString("pl_nombre"),
                            rs.getDate("pl_fecha"),
                            rs.getString("pl_desc")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error buscando playlist por ID: " + e.getMessage());
        }
        return null;
    }


    public void deleteById(int id, Connection conn) {
        String sql = "DELETE FROM Playlist WHERE id_playlist = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error eliminando playlist: " + e.getMessage());
        }
    }

    public void update(Playlist playlist, Connection conn) {
        String sql = "UPDATE Playlist SET p_nombre = ?, pl_fecha = ?, pl_desc = ? WHERE id_playlist = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, playlist.getNombre());
            pstmt.setDate(2, (Date)playlist.getFecha());
            pstmt.setString(3, playlist.getDescripcion());
            pstmt.setInt(4, playlist.getIdPlaylist());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizando playlist: " + e.getMessage());
        }
    }


}
