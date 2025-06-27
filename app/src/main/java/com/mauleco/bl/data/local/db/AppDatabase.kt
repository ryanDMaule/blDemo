package com.mauleco.bl.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mauleco.bl.data.local.dao.ActivityLogDao
import com.mauleco.bl.data.local.entity.Patient
import com.mauleco.bl.data.local.dao.PatientDao
import com.mauleco.bl.data.local.entity.ActivityLog

@Database(entities = [Patient::class, ActivityLog::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
    abstract fun activityLogDao(): ActivityLogDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "therapy_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
