package es.crp.chrono.db.repositories

import androidx.lifecycle.LiveData
import es.crp.chrono.db.daos.RaceDao
import es.crp.chrono.db.models.Race

class RaceRepository(private val raceDao: RaceDao) {

    val allRaces: LiveData<List<Race>> = raceDao.getAll()

    suspend fun insert(race: Race): Long {
        return raceDao.insert(race)
    }
}