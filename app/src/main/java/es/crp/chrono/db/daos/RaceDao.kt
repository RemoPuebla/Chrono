package es.crp.chrono.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import es.crp.chrono.db.models.Race

@Dao
interface RaceDao {
    @Query("SELECT * FROM race")
    fun getAll(): LiveData<List<Race>>

    @Query("SELECT * FROM race WHERE uid == (:id)")
    fun getById(id: Int): Race

    @Insert
    fun insert(race: Race): Long

    @Insert
    fun insertAll(vararg races: Race): List<Long>

    @Delete
    fun delete(race: Race)
}