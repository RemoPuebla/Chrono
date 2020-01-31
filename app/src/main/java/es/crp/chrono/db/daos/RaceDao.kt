package es.crp.chrono.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import es.crp.chrono.db.models.Race

@Dao
interface RaceDao {
    @Query("SELECT * FROM race")
    fun getAll(): List<Race>

    @Query("SELECT * FROM race WHERE uid == (:id)")
    fun getById(id: Int): Race

    @Insert
    fun insertAll(vararg users: Race)

    @Delete
    fun delete(user: Race)
}