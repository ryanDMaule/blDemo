import android.graphics.Color
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.mauleco.bl.R
import com.mauleco.bl.data.local.entity.ActivityLog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MPBarChart(
    activityLogs: List<ActivityLog>,
    modifier: Modifier = Modifier
) {
    if (activityLogs.isEmpty()) {
        return
    }

    // Prepare data grouped by day (MM/dd)
    val dateFormat = SimpleDateFormat("MM/dd", Locale.getDefault())

    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    val repsByDate = activityLogs
        .groupBy { log ->
            val date = inputFormat.parse(log.timestamp) ?: Date()  // parse to Date, fallback to current date if error
            dateFormat.format(date) // format to MM/dd
        }
        .mapValues { it.value.sumOf { log -> log.completedRepCount } }
        .toSortedMap { d1, d2 -> dateFormat.parse(d1).compareTo(dateFormat.parse(d2)) }


    val limitedData = repsByDate.entries.take(7)

    val dates = limitedData.map { it.key }
    val reps = limitedData.map { it.value }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            BarChart(context).apply {
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

                // No description text
                description.isEnabled = false

                // Disable zoom and pinch gestures
                setPinchZoom(false)
                setScaleEnabled(false)

                // Disable legend for simplicity
                legend.isEnabled = false

                // X axis config
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    granularity = 1f
                    labelCount = dates.size
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            val index = value.toInt()
                            return if (index >= 0 && index < dates.size) dates[index] else ""
                        }
                    }
                }

                axisLeft.apply {
                    axisMinimum = 0f
                    granularity = 1f
                    setDrawGridLines(true)
                }

                axisRight.isEnabled = false
            }
        },
        update = { barChart ->
            val entries = reps.mapIndexed { index, rep ->
                BarEntry(index.toFloat(), rep.toFloat())
            }

            val blueColorInt = ContextCompat.getColor(barChart.context, R.color.primary)
            val dataSet = BarDataSet(entries, "Reps per Day").apply {
                color = blueColorInt
                valueTextColor = Color.BLACK
                valueTextSize = 12f
            }

            val barData = BarData(dataSet).apply {
                barWidth = 0.3f
            }

            barChart.data = barData
            barChart.invalidate()
        }
    )
}
