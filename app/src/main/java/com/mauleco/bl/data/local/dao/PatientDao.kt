package com.mauleco.bl.data.local.dao

import androidx.room.*
import com.mauleco.bl.data.local.entity.Patient

@Dao
interface PatientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patient: Patient)

    @Query("SELECT * FROM patients WHERE patientId = :id")
    suspend fun getPatientById(id: String): Patient?
}
