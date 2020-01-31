package es.crp.chrono

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.crp.chrono.db.Converters
import es.crp.chrono.db.daos.RaceDao
import es.crp.chrono.db.models.Race

@Database(entities = [Race::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ChronoDatabase : RoomDatabase() {
    abstract fun raceDao(): RaceDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: ChronoDatabase? = null

        fun getDatabase(context: Context): ChronoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChronoDatabase::class.java,
                    "chrono-db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}