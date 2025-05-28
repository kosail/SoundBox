package com.katsukosail.soundbox.database.dao;

import com.katsukosail.soundbox.database.DatabaseConnection;
import com.katsukosail.soundbox.database.model.PlaylistHasCancion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlaylistHasCancionDAO {
    public void insert(PlaylistHasCancion phc, Connection conn) throws SQLException {
        String sql = "INSERT INTO Playlist_has_Cancion (id_playlist, id_cancion) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, phc.getIdPlaylist());
            pstmt.setInt(2, phc.getIdCancion());
            pstmt.executeUpdate();
        }
    }
    public List<PlaylistHasCancion> getAll(Connection conn) {
        List<PlaylistHasCancion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Playlist_has_Cancion";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new PlaylistHasCancion(
                        rs.getInt("id_playlist"),
                        rs.getInt("id_cancion")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo relaciones playlist-cancion: " + e.getMessage());
        }
        return lista;
    }

    public PlaylistHasCancion findById(int idPlaylist, int idCancion) {
        String sql = "SELECT * FROM Playlist_has_Cancion WHERE id_playlist = ? AND id_cancion = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPlaylist);
            stmt.setInt(2, idCancion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PlaylistHasCancion(
                            rs.getInt("id_playlist"),
                            rs.getInt("id_cancion")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error buscando relación playlist-canción: " + e.getMessage());
        }
        return null;
    }

    public void deleteById(int idPlaylist, int idCancion, Connection conn) {
        String sql = "DELETE FROM Playlist_has_Cancion WHERE id_playlist = ? AND id_cancion = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPlaylist);
            pstmt.setInt(2, idCancion);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error eliminando relación playlist-canción: " + e.getMessage());
        }
    }


}
