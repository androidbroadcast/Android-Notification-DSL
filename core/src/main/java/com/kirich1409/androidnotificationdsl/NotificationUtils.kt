@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.internal.areNotificationsEnabled

inline fun notify(
    context: Context,
    notificationId: Int,
    channelId: String,
    @DrawableRes smallIcon: Int,
    body: Notification.() -> Unit
) {
    val notificationManager = NotificationManagerCompat.from(context)
    if (notificationManager.areNotificationsEnabled(channelId)) {
        val notification = notification(context, channelId, smallIcon, body)
        notificationManager.notify(notificationId, notification)
    }
}

fun notify(context: Context, notificationId: Int, channelId: String, @DrawableRes smallIcon: Int) {
    val notificationManager = NotificationManagerCompat.from(context)
    if (notificationManager.areNotificationsEnabled(channelId)) {
        val notification = notification(context, channelId, smallIcon)
        notificationManager.notify(notificationId, notification)
    }
}
