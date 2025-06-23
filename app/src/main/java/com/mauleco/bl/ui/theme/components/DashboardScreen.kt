package com.mauleco.bl.ui.theme.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mauleco.bl.R


@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Split content: patient details + stats
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
        ) {
            // Left: Patient details
            PatientDetails(
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxHeight()
            )

            // Vertical Divider
            Divider(
                color = colorResource(id = R.color.gray),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .padding(horizontal = 8.dp)
            )

            // Right: Stats section
            StatsSection(
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxHeight()
            )
        }
    }
}
