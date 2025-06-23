package com.mauleco.bl.utils

import android.util.Log
import com.mauleco.bl.R
import com.mauleco.bl.data.local.entity.ActivityLog

object SessionTypeUtils {

    fun getIconForSession(sessionType: String): Int = when (sessionType) {
        "All" -> R.drawable.combined_icon
        "Assisted" -> R.drawable.auto_icon
        "Manual" -> R.drawable.manual_icon
        "Passive" -> R.drawable.passive_icon
        "Game" -> R.drawable.game_icon
        else -> R.drawable.combined_icon
    }

    fun getColorForSession(sessionType: String): Int = when (sessionType) {
        "All" -> R.color.gray
        "Assisted" -> R.color.pastel_green
        "Manual" -> R.color.pastel_purple
        "Passive" -> R.color.pastel_blue
        "Game" -> R.color.pastel_red
        else -> R.color.gray
    }

}
