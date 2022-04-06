package `fun`.picks.android_engineer_codecheck_sample.ui.util

import java.text.SimpleDateFormat
import java.util.*

fun unixTimeToString(
    dateFormat: String,
    unixTime: Long
): String {
    val dateFormatter = SimpleDateFormat(dateFormat, Locale.JAPAN)
    val date = Date(unixTime * 1000)
    return dateFormatter.format(date)
}