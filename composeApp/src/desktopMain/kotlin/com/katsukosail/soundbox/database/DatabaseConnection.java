package com.katsukosail.soundbox.database;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:database/app.db";
    private static final Connection connection = null;

    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(URL);

            if (esBaseDeDatosNueva(conn)) {
                creaTablas(conn);
            }

            System.out.println("Conectado a la base de datos.");
            return conn;

        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }

    public static void salir() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    private static boolean esBaseDeDatosNueva(Connection conn) {
        // Una query para comprobar si existe alguna tabla llamada Artista. Con eso sabemos si es una BD vieja.
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='Artista'")
                ) {

            // .next devuelve un boolean que dice si hay o no un resultado en la cola de resultados
            // Si la base de datos ya existe, entonces que simplemente regrese. Si ! no, que prosiga.
            return ! rs.next(); // Si ya existe da true, asi que por eso lo negamos para que sea false. No es BD nueva.

        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error en la comprobacion de la existencia de la base de datos.\nSaliendo de la aplicacion...\n\n");
            salir();
            throw new RuntimeException(e);
        }
    }

    private static void creaTablas(Connection conn) throws SQLException {
        System.out.println("Creando tablas...");

        Statement stmt = conn.createStatement();

        // Tabla: Artista
        stmt.executeUpdate("""
        CREATE TABLE Artista (
            id_artista INTEGER PRIMARY KEY NOT NULL,
            a_nombre TEXT NOT NULL,
            a_portada BLOB
        );
    """);

        // Tabla: Genero
        stmt.executeUpdate("""
        CREATE TABLE Genero (
            id_genero INTEGER PRIMARY KEY NOT NULL,
            ge_desc TEXT NOT NULL
        );
    """);

        // Tabla: Album
        stmt.executeUpdate("""
        CREATE TABLE Album (
            id_album INTEGER PRIMARY KEY NOT NULL,
            id_artista INTEGER NOT NULL,
            al_nombre TEXT NOT NULL,
            al_fecha DATE,
            al_portada BLOB,
            FOREIGN KEY (id_artista) REFERENCES Artista(id_artista)
        );
    """);

        // Tabla: Cancion
        stmt.executeUpdate("""
        CREATE TABLE Cancion (
            id_cancion INTEGER PRIMARY KEY NOT NULL,
            id_album INTEGER NOT NULL,
            id_genero INTEGER NOT NULL,
            c_nombre TEXT NOT NULL,
            c_duracion INTEGER,
            c_link TEXT,
            FOREIGN KEY (id_album) REFERENCES Album(id_album),
            FOREIGN KEY (id_genero) REFERENCES Genero(id_genero)
        );
    """);

        // Tabla: Playlist
        stmt.executeUpdate("""
        CREATE TABLE Playlist (
            id_playlist INTEGER PRIMARY KEY NOT NULL,
            pl_nombre TEXT NOT NULL,
            pl_fecha DATE,
            pl_desc TEXT
        );
    """);

        // Tabla: Playlist_has_Cancion (relación N:M)
        stmt.executeUpdate("""
        CREATE TABLE Playlist_has_Cancion (
            id_playlist INTEGER NOT NULL,
            id_cancion INTEGER NOT NULL,
            PRIMARY KEY (id_playlist, id_cancion),
            FOREIGN KEY (id_playlist) REFERENCES Playlist(id_playlist),
            FOREIGN KEY (id_cancion) REFERENCES Cancion(id_cancion)
        );
    """);

        stmt.close();
        System.out.println("Tablas creadas correctamente!");
    }

}
