package es.crp.chrono

import android.app.Application

class Chrono : Application() {
    companion object {
        var database: ChronoDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        database = ChronoDatabase.getDatabase(this)
    }

}