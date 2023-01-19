package com.apps.nabungemas.utils

import android.os.Build
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
}