package `fun`.picks.android_engineer_codecheck_sample.ui.util

import java.text.SimpleDateFormat
import java.util.*

fun unixTimeToString(unixTime: Long): String {
    val dateFormatter = SimpleDateFormat("yyyy年MM月dd日", Locale.JAPAN)
    val date = Date(unixTime * 1000)
    return dateFormatter.format(date)
}