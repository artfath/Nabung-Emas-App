package com.apps.nabungemas.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Time {
    fun getTime(): String? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val timeNow = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            val time = timeNow.format(formatter).toString()

            return time

        } else {
            return null
        }
    }

    fun timestampDate(date:Long?):String{
        val inputFormatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        inputFormatter.timeZone = TimeZone.getTimeZone("GMT")
        val dateNow = Date(date!!)
        return inputFormatter.format(dateNow)
    }
}