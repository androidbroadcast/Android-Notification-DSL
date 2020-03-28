@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.group

import android.app.Notification
import androidx.core.app.NotificationManagerCompat

class NotificationsGroup(
    private val notifications: Map<Int, Notification>,
    private val summary: Pair<Int, Notification>
) {

    fun notify(notificationManagerCompat: NotificationManagerCompat) {
        notifications.forEach { (notificationId, notification) ->
            notificationManagerCompat.notify(notificationId, notification)
        }

        notificationManagerCompat.notify(summary.first, summary.second)
    }
}