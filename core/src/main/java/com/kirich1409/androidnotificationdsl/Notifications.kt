package com.kirich1409.androidnotificationdsl

import android.content.Context
import androidx.core.app.NotificationCompat
import android.app.Notification as AndroidNotification

/**
 * Create new notification for specified [channelId]
 *
 * @param channelId The constructed Notification will be posted on this NotificationChannel
 *
 * @return A new [Notification] object.
 */
fun Context.notification(channelId: String, builder: Notification.() -> Unit): AndroidNotification {
    return Notification(NotificationCompat.Builder(this, channelId))
        .apply(builder)
        .build()
}