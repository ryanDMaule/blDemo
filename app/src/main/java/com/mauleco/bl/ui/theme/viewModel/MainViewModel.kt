package com.mauleco.bl.ui.theme.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mauleco.bl.data.local.entity.ActivityLog
import com.mauleco.bl.data.local.entity.Patient
import com.mauleco.bl.data.local.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _patient = mutableStateOf<Patient?>(null)
    val patient: State<Patient?> = _patient

    private val _activityLogs = mutableStateOf<List<ActivityLog>>(emptyList())
    val activityLogs: State<List<ActivityLog>> get() = _activityLogs

    private val _selectedSessionType = mutableStateOf("All")
    val selectedSessionType: State<String> get() = _selectedSessionType

    init {
        viewModelScope.launch {
            // Preload JSON data if DB is empty
            repository.preloadDataIfEmpty()

            // Load patient
            val loadedPatient = repository.getPatientById("d82315d2-5bda-4e11-be41-1924395c7f6b")
            _patient.value = loadedPatient

            // Load activity logs
            val logs = repository.getAllActivityLogs()
            _activityLogs.value = logs
        }
    }

    fun setSelectedSessionType(type: String) {
        _selectedSessionType.value = type
    }
}
