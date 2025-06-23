package com.mauleco.bl.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients")
data class Patient(
    @PrimaryKey val patientId: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val dateCreated: String
) {
    val fullName: String
        get() = "$firstName $lastName"
}
