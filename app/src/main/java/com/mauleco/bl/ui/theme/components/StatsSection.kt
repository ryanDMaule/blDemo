package com.mauleco.bl.ui.theme.components

import MPBarChart
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mauleco.bl.data.local.entity.ActivityLog
import com.mauleco.bl.ui.theme.viewModel.MainViewModel
import com.mauleco.bl.utils.GeneralUtils.calculateAverageReps
import com.mauleco.bl.utils.GeneralUtils.calculateAverageTime
import com.mauleco.bl.utils.SessionTypeUtils.getColorForSession
import com.mauleco.bl.utils.SessionTypeUtils.getIconForSession

private val sessionTypeToActivityType = mapOf(
    "All" to null, // means no filtering
    "Assisted" to "ASSIST_MODE",
    "Manual" to "MANUAL_MODE",
    "Passive" to "PASSIVE_MODE",
    "Game" to "GAME_MODE"
)

@Composable
fun StatsSection(
    activityLogs: List<ActivityLog>,
    selectedSession: String,
    onSessionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val sessionTypeToActivityType = mapOf(
        "All" to null,
        "Assisted" to "ASSIST_MODE",
        "Manual" to "MANUAL_MODE",
        "Passive" to "PASSIVE_MODE",
        "Game" to "GAME_MODE"
    )

    val filteredLogs = if (selectedSession == "All") {
        activityLogs
    } else {
        val activityType = sessionTypeToActivityType[selectedSession]
        activityLogs.filter { it.activityType == activityType }
    }

    val avgTime = remember(filteredLogs) { calculateAverageTime(filteredLogs) }
    val avgReps = remember(filteredLogs) { calculateAverageReps(filteredLogs) }

    val cardHeight = 160.dp

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .padding(horizontal = 16.dp)
        ) {
            DetailedStatCard(
                icon = painterResource(id = getIconForSession(selectedSession)),
                label = "$selectedSession Sessions",
                value = filteredLogs.size.toString(),
                avgTime = avgTime,
                avgReps = avgReps,
                modifier = Modifier
                    .weight(4f)
                    .fillMaxHeight()
            )

            Spacer(modifier = Modifier.weight(1f))

            listOf("All", "Assisted", "Manual", "Passive", "Game").forEachIndexed { index, type ->
                SessionSelectorCard(
                    icon = painterResource(id = getIconForSession(type)),
                    label = type,
                    backgroundColorRes = getColorForSession(type),
                    isSelected = (selectedSession == type),
                    onClick = { onSessionSelected(type) },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )

                if (index != 4) {
                    Spacer(modifier = Modifier.weight(0.5f))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        MPBarChart(
            activityLogs = filteredLogs,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 8.dp)
        )
    }
}
