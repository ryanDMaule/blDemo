package com.mauleco.bl.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mauleco.bl.data.local.entity.ActivityLog

@Dao
interface ActivityLogDao {
    @Query("SELECT * FROM activity_logs")
    suspend fun getAll(): List<ActivityLog>

    @Insert
    suspend fun insert(activityLog: ActivityLog)

    @Query("SELECT COUNT(*) FROM activity_logs")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(logs: List<ActivityLog>)

}
