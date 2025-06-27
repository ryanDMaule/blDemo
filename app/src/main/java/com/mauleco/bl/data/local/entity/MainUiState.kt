package com.mauleco.bl.data.local.entity

data class MainUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val patient: Patient? = null,
    val activityLogs: List<ActivityLog> = emptyList(),
    val selectedSessionType: String = "All"
)
