@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.group

import android.app.Notification
import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat

class NotificationsGroup @PublishedApi internal constructor(
    private val notifications: Map<Int, Notification>,
    private val summary: Pair<Int, Notification>
) {

    fun notify(notificationManager: NotificationManagerCompat) {
        notifications.forEach { (notificationId, notification) ->
            notificationManager.notify(notificationId, notification)
        }

        notificationManager.notify(summary.first, summary.second)
    }

    fun notify(notificationManager: NotificationManager) {
        notifications.forEach { (notificationId, notification) ->
            notificationManager.notify(notificationId, notification)
        }

        notificationManager.notify(summary.first, summary.second)
    }
}