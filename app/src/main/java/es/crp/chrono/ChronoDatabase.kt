package es.crp.chrono

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.crp.chrono.db.Converters
import es.crp.chrono.db.daos.RaceDao
import es.crp.chrono.db.models.Race

@Database(entities = [Race::class], version = 1)
@TypeConverters(Converters::class)
abstract class ChronoDatabase : RoomDatabase() {
    abstract fun raceDao(): RaceDao
}