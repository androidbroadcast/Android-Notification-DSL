package com.kirich1409.androidnotificationdsl.sample

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.NotificationImportance
import com.kirich1409.androidnotificationdsl.channels.createNotificationChannels

class NotificationSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        createNotificationChannels(this) {
            channel(CHANNEL_DEFAULT, "Default", importance = NotificationImportance.HIGH)
            channel(NOTIFICATION_CHANNEL_LOW, "Low", importance = NotificationImportance.LOW)
        }
    }
}
