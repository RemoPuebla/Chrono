package es.crp.chrono

import android.app.Application
import androidx.room.Room

class Chrono : Application() {
    companion object {
        var database: ChronoDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            ChronoDatabase::class.java,
            "chrono-db"
        ).build()
    }
}