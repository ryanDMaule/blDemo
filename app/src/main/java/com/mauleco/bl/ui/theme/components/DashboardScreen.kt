package com.mauleco.bl.ui.theme.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mauleco.bl.R
import com.mauleco.bl.ui.theme.viewModel.MainViewModel
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun DashboardScreen(vm: MainViewModel = hiltViewModel()) {
    val selectedSession by vm.selectedSessionType
    val isLoading by vm.isLoading
    val errorMessage by vm.errorMessage
    val allLogs by vm.activityLogs
    val patient by vm.patient

    when {
        isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        errorMessage != null -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = errorMessage ?: "Unknown error",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)
                ) {
                    PatientDetails(
                        patient = patient,
                        modifier = Modifier
                            .weight(0.2f)
                            .fillMaxHeight()
                    )

                    Divider(
                        color = colorResource(id = R.color.gray),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .padding(horizontal = 8.dp)
                    )

                    StatsSection(
                        activityLogs = allLogs,
                        selectedSession = selectedSession,
                        onSessionSelected = { vm.setSelectedSessionType(it) },
                        modifier = Modifier
                            .weight(0.8f)
                            .fillMaxHeight()
                    )
                }
            }
        }
    }

}
