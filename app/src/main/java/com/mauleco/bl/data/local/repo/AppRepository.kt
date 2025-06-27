package com.mauleco.bl.data.local.repo

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mauleco.bl.data.local.db.AppDatabase
import com.mauleco.bl.data.local.entity.Patient
import com.mauleco.bl.data.local.entity.ActivityLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class AppRepository(
    private val db: AppDatabase,
    private val context: Context
) {

    suspend fun getPatientById(id: String): Patient? = withContext(Dispatchers.IO) {
        db.patientDao().getPatientById(id)
    }

    suspend fun getAllActivityLogs(): List<ActivityLog> = withContext(Dispatchers.IO) {
        db.activityLogDao().getAll()
    }

    // 👇 Add preload logic here
    suspend fun preloadDataIfEmpty() = withContext(Dispatchers.IO) {
        if (db.patientDao().count() == 0) {
            preloadPatient()
        }

        if (db.activityLogDao().count() == 0) {
            preloadActivityLogs()
        }
    }

    private suspend fun preloadPatient() {
        val inputStream = context.assets.open("patientDetails.json")
        val reader = inputStream.bufferedReader()
        val patient = Gson().fromJson(reader, Patient::class.java)
        db.patientDao().insertPatient(patient)
    }

    private suspend fun preloadActivityLogs() {
        val inputStream = context.assets.open("activityLogs.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<ActivityLog>>() {}.type
        val logs: List<ActivityLog> = Gson().fromJson(jsonString, type)
        db.activityLogDao().insertAll(logs)
    }
}
