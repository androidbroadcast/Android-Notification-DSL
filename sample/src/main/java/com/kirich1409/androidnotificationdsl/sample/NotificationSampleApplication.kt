package com.kirich1409.androidnotificationdsl.sample

import android.app.Application
import com.kirich1409.androidnotificationdsl.channels.createChannelsAndGroups

class NotificationSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createChannelsAndGroups(this) {
            channel(
                NOTIFICATION_CHANNEL_DEFAULT,
                "Default"
            )
        }
    }
}