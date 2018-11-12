package io.revze.footballapp.utils

import android.widget.TextView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class Helper {
    fun timeConverter(time: String): String {
        return "12"
    }

    fun dateConverter(date: String): String {
//        val year = date.substring(0, 4).toInt()
//        val month = if (date.substring(5, 0).equals("0")) {date.substring(6, 0).toInt() - 1} else {date.substring(5, 1).toInt() - 1}
//        val day = if (date.substring(9, 9).equals("0")) {date.substring(8, 8).toInt()} else {date.substring(8, 9).toInt()}
//        val gregorianCalendar = GregorianCalendar(year, month, day)
//
//        gregorianCalendar.add(Calendar.HOUR_OF_DAY, 24)
//
//        return gregorianCalendar.time.toString()

//        2018-11-24
//        0123456789

//        try {
//            val sourceDF = SimpleDateFormat("yyyy-MM-dd", Locale.US)
//            val parseSrcDate = sourceDF.parse(date)
//            val finalDF = SimpleDateFormat("EEE, dd MMM yyyy", Locale.US)
//
//            return finalDF.format(parseSrcDate)
//        }
//        catch (e: ParseException) {
//            return date
//        }

        return "12"
    }
}