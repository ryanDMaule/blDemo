package com.mauleco.bl.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mauleco.bl.R
import com.mauleco.bl.utils.GeneralUtils.formatTimeToMinutesSeconds

@Composable
fun DetailedStatCard(
    icon: Painter,
    label: String,
    value: String,
    avgTime: String,
    avgReps: String,
    modifier: Modifier = Modifier
) {
    val borderColor = colorResource(id = R.color.gray)

    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color = colorResource(id = R.color.white), shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Center label and icon+value horizontally
    ) {
        // Title
        Text(
            text = label,
            color = colorResource(id = R.color.black),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Icon and value side by side, centered
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.white)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = value,
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Avg Time: ${formatTimeToMinutesSeconds(avgTime)}",
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Avg Reps: $avgReps",
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

