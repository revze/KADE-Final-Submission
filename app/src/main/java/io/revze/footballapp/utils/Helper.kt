package io.revze.footballapp.utils

import android.widget.TextView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class Helper(private val time: String) {

    fun getCalendar(): Calendar {
        val year = time.substring(0, 4).toInt()
        val month = if (time.substring(5, 6).equals("0"))
            time[6].toString().toInt() - 1
        else time.substring(5, 7).toInt() - 1
        val day = if (time.substring(8, 9).equals("0"))
            time[9].toString().toInt()
        else time.substring(8, 10).toInt()
        val hour = if (time.substring(11, 12).equals("0"))
            time[12].toString().toInt()
        else time.substring(11, 13).toInt()
        val minute = if (time.substring(14, 15).equals("0"))
            time[15].toString().toInt()
        else time.substring(14, 16).toInt()

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        calendar.add(Calendar.HOUR_OF_DAY, 7)

        return calendar
    }

    fun getTime(): Date = getCalendar().time

    fun getTimeInMillis(): Long = getCalendar().timeInMillis

    fun convertDate(): String {
        val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.US)

        return dateFormat.format(getTime())
    }

    fun convertTime(): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.US)

        return dateFormat.format(getTime())
    }
}