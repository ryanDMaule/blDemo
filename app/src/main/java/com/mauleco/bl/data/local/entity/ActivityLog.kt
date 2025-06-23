package com.mauleco.bl.data.local.entity

data class ActivityLog(
    val patientId: String,
    val sessionId: String,
    val activityLogId: String,
    val activityType: String,
    val timestamp: String,
    val durationInSeconds: Int,
    val completedRepCount: Int
)
