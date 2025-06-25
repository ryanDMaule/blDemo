package com.mauleco.bl.ui.theme.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mauleco.bl.R
import com.mauleco.bl.ui.theme.viewModel.MainViewModel


@Composable
fun DashboardScreen(vm: MainViewModel = viewModel()) {
    val selectedSession by vm.selectedSessionType
    val allLogs by vm.activityLogs
    val patient by vm.patient

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
