@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.group

import android.app.Notification
import android.app.NotificationManager
import androidx.collection.SparseArrayCompat
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.utils.forEach

@Suppress("UndocumentedPublicClass")
class NotificationsGroup @PublishedApi internal constructor(
    private val notifications: SparseArrayCompat<Notification>,
    private val summary: Pair<Int, Notification>,
    private val groupAlertBehavior: NotificationGroupAlertBehavior
) {

    /**
     * Post notifications to be shown in the status bar, stream, etc.
     */
    fun notify(notificationManager: NotificationManagerCompat) {
        notificationManager.notify(summary.first, summary.second)
        notifications.forEach { notificationId, notification ->
            notificationManager.notify(notificationId, notification)
        }
    }

    /**
     * Post notifications to be shown in the status bar, stream, etc.
     */
    fun notify(notificationManager: NotificationManager) {
        notificationManager.notify(summary.first, summary.second)
        notifications.forEach { notificationId, notification ->
            notificationManager.notify(notificationId, notification)
        }
    }
}
