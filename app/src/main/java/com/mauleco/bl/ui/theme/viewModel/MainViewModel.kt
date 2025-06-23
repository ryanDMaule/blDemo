package com.mauleco.bl.ui.theme.viewModel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mauleco.bl.data.local.db.AppDatabase
import com.mauleco.bl.data.local.entity.ActivityLog
import com.mauleco.bl.data.local.entity.Patient
import com.mauleco.bl.utils.loadActivityLogsFromAssets
import com.mauleco.bl.utils.loadPatientFromAssets
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)

    private val _patient = mutableStateOf<Patient?>(null)
    val patient: State<Patient?> = _patient

    private val _activityLogs = mutableStateOf<List<ActivityLog>>(emptyList())
    val activityLogs: State<List<ActivityLog>> get() = _activityLogs

    private val _selectedSessionType = mutableStateOf("All")
    val selectedSessionType: State<String> get() = _selectedSessionType

    init {
        viewModelScope.launch {
            loadPatientFromAssets(application, db)
            val loadedPatient = withContext(Dispatchers.IO) {
                db.patientDao().getPatientById("d82315d2-5bda-4e11-be41-1924395c7f6b")
            }
            _patient.value = loadedPatient

            val logs = loadActivityLogsFromAssets(getApplication())
            _activityLogs.value = logs
        }
    }

    fun setSelectedSessionType(type: String) {
        _selectedSessionType.value = type
    }
}
