package com.mauleco.bl.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mauleco.bl.data.local.entity.MainUiState
import com.mauleco.bl.data.local.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }

                repository.preloadDataIfEmpty()

                val patient = repository.getPatientById("d82315d2-5bda-4e11-be41-1924395c7f6b")
                val logs = repository.getAllActivityLogs()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        patient = patient,
                        activityLogs = logs
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Unknown error occurred"
                    )
                }
            }
        }
    }

    fun setSelectedSessionType(type: String) {
        _uiState.update { it.copy(selectedSessionType = type) }
    }
}
