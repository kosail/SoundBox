//package com.katsukosail.soundbox.database
//
//import androidx.compose.runtime.mutableStateListOf
//import com.katsukosail.soundbox.database.dao.*
//import com.katsukosail.soundbox.model.*
//import soundbox.composeapp.generated.resources.Res
//import soundbox.composeapp.generated.resources.artists
//
///* * The purpose of this is to have an abstraction layer between the raw DatabaseConnection and Compose UI
//   * Data will be load into ram from the DB and kept there. Any change will directly change the in-memory data
//   * while uploading the changes to the DB in background.
//   *
//   * Compose will be listening for changes on the in-memory data and thus, on any change, the UI will be recomposed.
//   *
//   * There will be one list per category/table and those list WILL NOT UPDATE on selects or filtering done by the UI.
//   * The only way those lists will reload data from the DB is on any Create, Update or Delete performed on any table.
//   ? If the lists don't behave like this, will be inconsistency across lists + between lists and the DB
//*/
//
//// ! This files import all DAOs and handle them inside there
//
///* TODO: This helper needs to handle the following FOR EACH table.
//    - Create
//    - Read / SELECT
//    - Update
//    - Delete
//
//   TODO ADDITIONAL: There must be a SELECT * function
//*/
//
//object DatabaseHelper {
//    // In-memory caches
//    private val _albums = mutableStateListOf<Album>()
//    private val _artists = mutableStateListOf<Artist>()
//    private val _songs = mutableStateListOf<Song>()
//    private val _genres = mutableStateListOf<Genre>()
//    private val _playlists = mutableStateListOf<Playlist>()
//
//    val albums: List<Album> = _albums
//    val artist: List<Artist> = _artists
//    val songs: List<Song> = _songs
//    val genres: List<Genre> = _genres
//    val playlists: List<Playlist> = _playlists
//
//    // DAO instances
//    private val albumDao = AlbumDAO()
//    private val artistDao = ArtistaDAO()
//    private val songDao = CancionDAO()
//    private val genreDao = GeneroDAO()
//    private val playlistDao = PlaylistDAO()
//
//    init {
//        loadAllData()
//    }
//
//    private fun loadAllData() {
//        DatabaseConnection.connect()?.use { conn ->
//            // Clear las lists from non-updated data
//            _albums.clear()
//            _artists.clear()
//            _songs.clear()
//            _genres.clear()
//            _playlists.clear()
//
//            // Load artists first since albums depend on them
//            _artists.addAll(albumDao.getAll(conn).mapNotNull { dbArtist ->
//                dbArtist?.toUiModel()
//            })
//
//            // Then load genres since songs depend on them
//            _genres.addAll(genreDao.getAll(conn).mapNotNull { dbGenre ->
//                dbGenre?.toUiModel()
//            })
//
//            // Then load albums (which depend on artists)
//            _albums.addAll(albumDao.getAll(conn).mapNotNull { dbAlbum ->
//                dbAlbum?.toUiModel(
//                    artist = _artists.firstOrNull { it.id == dbAlbum.idArtista }
//                        ?: Artist(0, "Unknown", Res.drawable.artists)
//                )
//            })
//
//            // Then load songs (which depend on albums and genres)
//            _songs.addAll(songDao.getAll(conn).mapNotNull { dbSong ->
//                dbSong?.toUiModel(
//                    album = _albums.firstOrNull { it.id == dbSong.idAlbum }
//                        ?: Album(0, Artist(0, "Unknown", Res.drawable.artists),
//                        "Unknown", "", Res.drawable.artists),
//                    genre = _genres.firstOrNull { it.id == dbSong.idGenero }
//                        ?: Genre(0, "Unknown")
//                )
//            })
//
//            // Finally load playlists and their songs
//            _playlists.addAll(playlistDao.getAll(conn).mapNotNull { dbPlaylist ->
//                dbPlaylist?.toUiModel(
//                    songs = songDao.getAll(conn)
//                        .filter { it.idPlaylist == dbPlaylist.idPlaylist }
//                        .mapNotNull { phc ->
//                            _songs.firstOrNull { it.id == phc.idCancion }
//                        }
//                )
//            })
//        }
//    }
//
//    // Add CRUD operations for each entity as needed
//    // Example for adding a song:
//    fun addSong(song: Song): Boolean {
//        return DatabaseConnection.connect()?.use { conn ->
//            val dbSong = song.toDbModel()
//            songDao.insert(dbSong, conn)
//            loadAllData() // Refresh all data to maintain consistency
//            true
//        } ?: false
//    }
//}
//
//// Extension functions for model conversion
//private fun com.katsukosail.soundbox.database.model.Artista.toUiModel(): Artist {
//    return Artist(
//        id = this.idArtista,
//        name = this.nombre ?: "Unknown Artist",
//        image = Res.drawable.artists // You might want to handle the actual image bytes here
//    )
//}
//
//private fun com.katsukosail.soundbox.database.model.Genero.toUiModel(): Genre {
//    return Genre(
//        id = this.idGenero,
//        name = this.descripcion ?: "Unknown Genre"
//    )
//}
//
//private fun com.katsukosail.soundbox.database.model.Album.toUiModel(artist: Artist): Album {
//    return Album(
//        id = this.idAlbum,
//        artist = artist,
//        name = this.nombre ?: "Unknown Album",
//        date = this.fecha?.toString() ?: "",
//        image = Res.drawable.artists // You might want to handle the actual image bytes here
//    )
//}
//
//private fun com.katsukosail.soundbox.database.model.Cancion.toUiModel(
//    album: Album,
//    genre: Genre
//): Song {
//    return Song(
//        id = this.idCancion,
//        album = album,
//        genre = genre,
//        name = this.nombre ?: "Unknown Song",
//        duration = this.duracion,
//        link = this.link ?: ""
//    )
//}
//
//private fun com.katsukosail.soundbox.database.model.Playlist.toUiModel(
//    songs: List<Song>
//): Playlist {
//    return Playlist(
//        id = this.idPlaylist,
//        name = this.nombre ?: "Unknown Playlist",
//        date = this.fecha?.toString() ?: "",
//        description = this.descripcion ?: "",
//    )
//}
//
//// Reverse conversions (for saving to DB)
//private fun Song.toDbModel(): com.katsukosail.soundbox.database.model.Cancion {
//    return com.katsukosail.soundbox.database.model.Cancion(
//        idAlbum = this.album.id,
//        idGenero = this.genre.id,
//        nombre = this.name,
//        duracion = this.duration,
//        link = this.link
//    )
//}
