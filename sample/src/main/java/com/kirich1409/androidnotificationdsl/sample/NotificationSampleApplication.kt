package com.kirich1409.androidnotificationdsl.sample

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.channels.createChannelsAndGroups

class NotificationSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        createChannelsAndGroups(this) {
            channel(NOTIFICATION_CHANNEL_DEFAULT, "Default", importance = NotificationManagerCompat.IMPORTANCE_HIGH)
        }
    }
}