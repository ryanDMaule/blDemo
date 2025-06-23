package com.mauleco.bl.utils

import com.mauleco.bl.data.local.entity.ActivityLog

object GeneralUtils {

    fun calculateAverageTime(logs: List<ActivityLog>): String {
        if (logs.isEmpty()) return "0s"
        val totalSeconds = logs.sumOf { it.durationInSeconds }
        val avgSeconds = totalSeconds / logs.size
        return "${avgSeconds}s"
    }

    fun calculateAverageReps(logs: List<ActivityLog>): String {
        if (logs.isEmpty()) return "0"
        val totalReps = logs.sumOf { it.completedRepCount }
        return (totalReps / logs.size).toString()
    }

    private fun safeStringToInt(input: String): Int {
        val cleaned = input.trim().removeSuffix("s").filter { it.isDigit() }
        return cleaned.toIntOrNull() ?: 0
    }

    fun formatTimeToMinutesSeconds(timeStr: String): String {
        val timeInSeconds = safeStringToInt(timeStr)
        val minutes = timeInSeconds / 60
        val seconds = timeInSeconds % 60
        return String.format("%dm %02ds", minutes, seconds)
    }

}