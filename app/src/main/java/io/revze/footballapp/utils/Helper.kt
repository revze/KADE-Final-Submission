package io.revze.footballapp.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class Helper(private val time: String) {

    private val TAG = Helper::javaClass.name

    private fun getCalendar(): Calendar {
        val calendar = Calendar.getInstance()

        try {
            val year = time.substring(0, 4).toInt()
            val month = if (time[5] == '0')
                time[6].toString().toInt() - 1
            else time.substring(5, 7).toInt() - 1
            val day = if (time[8] == '0')
                time[9].toString().toInt()
            else time.substring(8, 10).toInt()
            val hour = if (time[11] == '0')
                time[12].toString().toInt()
            else time.substring(11, 13).toInt()
            val minute = if (time[14] == '0')
                time[15].toString().toInt()
            else time.substring(14, 16).toInt()

            calendar.set(year, month, day, hour, minute)
            calendar.add(Calendar.HOUR_OF_DAY, 7)
        }
        catch (e: NumberFormatException) {
            Log.e(TAG, "Parsing time failed", e)
        }

        return calendar
    }

    private fun getTime(): Date = getCalendar().time

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