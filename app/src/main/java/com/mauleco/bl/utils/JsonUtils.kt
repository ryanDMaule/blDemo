package com.mauleco.bl.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mauleco.bl.data.local.db.AppDatabase
import com.mauleco.bl.data.local.entity.ActivityLog
import com.mauleco.bl.data.local.entity.Patient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader

suspend fun loadPatientFromAssets(context: Context, db: AppDatabase) {
    withContext(Dispatchers.IO) {
        val inputStream = context.assets.open("patientDetails.json")
        val reader = InputStreamReader(inputStream)
        val patient = Gson().fromJson(reader, Patient::class.java)
        db.patientDao().insertPatient(patient)
    }
}

suspend fun loadActivityLogsFromAssets(context: Context): List<ActivityLog> {
    return withContext(Dispatchers.IO) {
        val inputStream = context.assets.open("activityLogs.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val gson = Gson()
        val type = object : TypeToken<List<ActivityLog>>() {}.type
        gson.fromJson<List<ActivityLog>>(jsonString, type)
    }
}
