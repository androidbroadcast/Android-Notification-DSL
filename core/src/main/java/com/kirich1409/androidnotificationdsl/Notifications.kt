package com.kirich1409.androidnotificationdsl

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import android.app.Notification as AndroidNotification

/**
 * Create new notification for specified [channelId]
 *
 * @param channelId The constructed Notification will be posted on this NotificationChannel
 *
 * @return A new [Notification] object.
 */
fun Context.notification(
    channelId: String,
    @DrawableRes smallIcon: Int,
    body: Notification.() -> Unit
): AndroidNotification {
    val builder = NotificationCompat.Builder(this, channelId).apply {
        setSmallIcon(smallIcon)
    }
    Notification(builder).apply(body)
    return builder.build()
}

fun Context.notification(channelId: String, @DrawableRes smallIcon: Int): AndroidNotification {
    val builder = NotificationCompat.Builder(this, channelId).apply {
        setSmallIcon(smallIcon)
    }
    return builder.build()
}