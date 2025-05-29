package com.katsukosail.soundbox.model

sealed class ItemType {
    data class SongItem(val song: Song) : ItemType()
    data class AlbumItem(val album: Album) : ItemType()
    data class ArtistItem(val artist: Artist) : ItemType()
    data class GenreItem(val genre: Genre) : ItemType()
    data class PlaylistItem(val playlist: Playlist) : ItemType()
}
