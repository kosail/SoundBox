package com.katsukosail.soundbox.state

import com.katsukosail.soundbox.database.DatabaseConnection
import java.sql.Connection

// Database connection helper. This is needed due Compose's recompositions.
class DbDonnectionState {
    var db: Connection? = null
        private set

    private var initialized = false

    fun initializeDatabase(): Boolean {
        if (initialized) return db != null

        db = DatabaseConnection.connect()
        initialized = true
        return db != null
    }

    fun close() {
        db?.close()
        db = null
        initialized = false
    }
}