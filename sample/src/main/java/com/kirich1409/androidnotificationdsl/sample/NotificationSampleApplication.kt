package com.kirich1409.androidnotificationdsl.sample

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.channels.createNotificationChannels

class NotificationSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        createNotificationChannels(this) {
            channel(NOTIFICATION_CHANNEL_DEFAULT, "Default", importance = NotificationManagerCompat.IMPORTANCE_HIGH)
            channel(NOTIFICATION_CHANNEL_LOW, "Low", importance = NotificationManagerCompat.IMPORTANCE_LOW)
        }
    }
}
