package com.apps.nabungemas.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.apps.nabungemas.R
import com.apps.nabungemas.worker.WorkerConstant.CHANNEL_ID
import com.apps.nabungemas.worker.WorkerConstant.CONTENT_TITLE
import com.apps.nabungemas.worker.WorkerConstant.NOTIFICATION_CHANNEL_DESCRIPTION
import com.apps.nabungemas.worker.WorkerConstant.NOTIFICATION_CHANNEL_NAME

class WorkerUtils {
    fun statusNotification(message: String, context: Context) {

        // Make a channel if necessary
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            val name = NOTIFICATION_CHANNEL_NAME
            val descriptionText = NOTIFICATION_CHANNEL_DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }


            // Add the channel
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

            notificationManager?.createNotificationChannel(channel)
        }

        // Create the notification
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(CONTENT_TITLE)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        // Show the notification
        with(NotificationManagerCompat.from(context)){
            notify(1,builder)
        }
    }

}