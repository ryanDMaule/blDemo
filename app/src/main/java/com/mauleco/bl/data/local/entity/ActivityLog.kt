package com.mauleco.bl.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity_logs")
data class ActivityLog(
    @PrimaryKey val activityLogId: String,
    val patientId: String,
    val sessionId: String,
    val activityType: String,
    val timestamp: String,
    val durationInSeconds: Int,
    val completedRepCount: Int
)
