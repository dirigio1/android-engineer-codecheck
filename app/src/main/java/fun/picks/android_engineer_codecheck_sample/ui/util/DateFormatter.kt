package `fun`.picks.android_engineer_codecheck_sample.ui.util

import java.text.SimpleDateFormat
import java.util.*

fun unixTimeToString(unixTime: Long): String {
    val dateFormatter = SimpleDateFormat("yyyy年M月d日H時m分", Locale.JAPAN)
    val date = Date(unixTime * 1000)
    return dateFormatter.format(date)
}