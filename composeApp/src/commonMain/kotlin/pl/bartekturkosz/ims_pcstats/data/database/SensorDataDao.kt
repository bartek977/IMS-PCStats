package pl.bartekturkosz.ims_pcstats.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.bartekturkosz.ims_pcstats.data.model.SensorDataEntity

@Dao
interface SensorDataDao {
    @Insert
    suspend fun insert(data: SensorDataEntity)

    @Query("SELECT * FROM sensor_data")
    fun getAllAsFlow(): Flow<List<SensorDataEntity>>
}