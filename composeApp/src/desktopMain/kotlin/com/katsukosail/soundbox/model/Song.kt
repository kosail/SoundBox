package com.katsukosail.soundbox.model

data class Song(
    val id: Int,
    val album: Album,
    val genre: Genre,
    val name: String,
    val duration: Int,
    val link: String
)

val songs: List<Song> = listOf(
    // Lamp
    Song(1, albums[0], genres[0], "Tokyo City Lights", 215, "https://example.com/tokyo-city-lights"),
    Song(2, albums[0], genres[0], "Summer Blue", 230, "https://example.com/summer-blue"),
    Song(3, albums[0], genres[1], "Yoru no Waltz", 208, "https://example.com/yoru-no-waltz"),
    Song(4, albums[0], genres[0], "Rain in June", 199, "https://example.com/rain-in-june"),
    Song(5, albums[0], genres[1], "Evening Glow", 210, "https://example.com/evening-glow"),

    Song(6, albums[1], genres[0], "Two, Three", 205, "https://example.com/two-three"),
    Song(7, albums[1], genres[0], "Love Story", 212, "https://example.com/love-story"),
    Song(8, albums[1], genres[0], "Lily", 198, "https://example.com/lily"),
    Song(9, albums[1], genres[1], "Twilight Hour", 234, "https://example.com/twilight-hour"),
    Song(10, albums[1], genres[1], "For Lovers", 226, "https://example.com/for-lovers"),

    // Laura Day Romance
    Song(11, albums[3], genres[1], "Cloud Kiss", 210, "https://example.com/cloud-kiss"),
    Song(12, albums[3], genres[1], "Snowglobe Heart", 205, "https://example.com/snowglobe-heart"),
    Song(13, albums[3], genres[0], "Secret Garden", 212, "https://example.com/secret-garden"),
    Song(14, albums[3], genres[2], "Romantic Day", 198, "https://example.com/romantic-day"),
    Song(15, albums[3], genres[1], "Love Rain", 240, "https://example.com/love-rain"),

    Song(16, albums[4], genres[1], "Night Picnic", 220, "https://example.com/night-picnic"),
    Song(17, albums[4], genres[1], "Window Moon", 203, "https://example.com/window-moon"),
    Song(18, albums[4], genres[0], "Sweet Dusk", 200, "https://example.com/sweet-dusk"),
    Song(19, albums[4], genres[2], "Garden of You", 218, "https://example.com/garden-of-you"),
    Song(20, albums[4], genres[1], "Midnight Garden", 209, "https://example.com/midnight-garden"),

    // Aimer
    Song(21, albums[5], genres[2], "Brave Shine", 228, "https://example.com/brave-shine"),
    Song(22, albums[5], genres[3], "Ninelie", 200, "https://example.com/ninelie"),
    Song(23, albums[5], genres[4], "Stars in the Rain", 242, "https://example.com/stars-in-the-rain"),
    Song(24, albums[5], genres[2], "Kataomoi", 251, "https://example.com/kataomoi"),
    Song(25, albums[5], genres[3], "Re:pray", 230, "https://example.com/repray"),

    Song(26, albums[6], genres[2], "Hana no Uta", 226, "https://example.com/hana-no-uta"),
    Song(27, albums[6], genres[3], "Black Bird", 223, "https://example.com/black-bird"),
    Song(28, albums[6], genres[3], "Stand By You", 215, "https://example.com/stand-by-you"),
    Song(29, albums[6], genres[2], "I beg you", 248, "https://example.com/i-beg-you"),
    Song(30, albums[6], genres[3], "Ref:rain", 235, "https://example.com/refrain"),

    // Back Number
    Song(31, albums[8], genres[4], "Christmas Song", 230, "https://example.com/christmas-song"),
    Song(32, albums[8], genres[4], "Heroine", 210, "https://example.com/heroine"),
    Song(33, albums[8], genres[4], "Mabataki", 204, "https://example.com/mabataki"),
    Song(34, albums[8], genres[4], "Happy End", 243, "https://example.com/happy-end"),
    Song(35, albums[8], genres[4], "SISTER", 213, "https://example.com/sister"),

    Song(36, albums[9], genres[4], "Haru", 232, "https://example.com/haru"),
    Song(37, albums[9], genres[4], "Clear", 226, "https://example.com/clear"),
    Song(38, albums[9], genres[5], "Fish", 207, "https://example.com/fish"),
    Song(39, albums[9], genres[4], "Old Fashion", 223, "https://example.com/old-fashion"),
    Song(40, albums[9], genres[4], "Encore", 218, "https://example.com/encore"),

    // YOASOBI
    Song(41, albums[10], genres[6], "Yoru ni Kakeru", 260, "https://example.com/yoru-ni-kakeru"),
    Song(42, albums[10], genres[6], "Haruka", 229, "https://example.com/haruka"),
    Song(43, albums[10], genres[6], "Tabun", 231, "https://example.com/tabun"),
    Song(44, albums[10], genres[6], "Encore", 218, "https://example.com/yoasobi-encore"),
    Song(45, albums[10], genres[6], "Ano Yume o Nazotte", 248, "https://example.com/ano-yume"),

    Song(46, albums[11], genres[6], "Sangenshoku", 210, "https://example.com/sangenshoku"),
    Song(47, albums[11], genres[6], "Kaibutsu", 233, "https://example.com/kaibutsu"),
    Song(48, albums[11], genres[6], "Yasashii Suisei", 240, "https://example.com/yasashii"),
    Song(49, albums[11], genres[6], "Taisho Roman", 215, "https://example.com/taisho-roman"),
    Song(50, albums[11], genres[6], "Loveletter", 222, "https://example.com/loveletter"),

    // Aimyon
    Song(51, albums[13], genres[5], "Marigold", 231, "https://example.com/marigold"),
    Song(52, albums[13], genres[5], "Haru no Hi", 243, "https://example.com/haru-no-hi"),
    Song(53, albums[13], genres[5], "Let the Night", 218, "https://example.com/let-the-night"),
    Song(54, albums[13], genres[5], "Dream Chaser", 210, "https://example.com/dream-chaser"),
    Song(55, albums[13], genres[5], "Heart Beat", 225, "https://example.com/heart-beat"),

    Song(56, albums[14], genres[5], "Ai wo Tsutaetai da toka", 228, "https://example.com/ai-wo-tsutaetai"),
    Song(57, albums[14], genres[5], "Naked Heart", 234, "https://example.com/naked-heart"),
    Song(58, albums[14], genres[5], "Till I Die", 219, "https://example.com/till-i-die"),
    Song(59, albums[14], genres[5], "Falling Eyes", 211, "https://example.com/falling-eyes"),
    Song(60, albums[14], genres[5], "Yoru wa Nemureru kai?", 229, "https://example.com/yoru-wa"),

    Song(61, albums[15], genres[5], "Goodbye Piano", 216, "https://example.com/goodbye-piano"),
    Song(62, albums[15], genres[5], "Tokyo", 224, "https://example.com/tokyo"),
    Song(63, albums[15], genres[5], "On a Rainy Day", 217, "https://example.com/on-a-rainy-day"),
    Song(64, albums[15], genres[5], "Morning View", 210, "https://example.com/morning-view"),
    Song(65, albums[15], genres[5], "Sweet Bitter", 232, "https://example.com/sweet-bitter")
)
