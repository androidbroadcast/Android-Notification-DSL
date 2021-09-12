@file:Suppress("unused")
@file:JvmName("NotificationUtils")

package com.kirich1409.androidnotificationdsl

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationManagerCompat

/**
 * Build new notification and post it. If notifications aren't enabled for the channel
 * than the notification will not be shown.
 *
 * @param context application context
 * @param notificationId the ID of the notification
 * @param channelId The constructed Notification will be posted on this [NotificationChannel].
 * @param smallIcon A resource id in the app's package of the drawable to use as Notification icon in status bar
 *
 * @return Created notification or null if it hasn't been created
 */
inline fun notify(
    context: Context,
    notificationId: Int,
    channelId: String,
    @DrawableRes smallIcon: Int,
    body: NotificationBuilder.() -> Unit
): android.app.Notification? {
    val notificationManager = NotificationManagerCompat.from(context)
    return notificationManager.notify(context, notificationId, channelId, smallIcon, body)
}

/**
 * Build new notification and post it. If notifications aren't enabled for the channel
 * that the notification belongs to, than the notification will not be shown and [body] will not be
 * called.
 *
 * @param context application context
 * @param notificationId the ID of the notification
 * @param channelId The constructed Notification will be posted on this [NotificationChannel].
 * @param smallIcon A resource id in the app's package of the drawable to use as Notification icon in status bar
 *
 * @return Created notification or null if it hasn't been created
 */
inline fun NotificationManagerCompat.notify(
    context: Context,
    notificationId: Int,
    channelId: String,
    @DrawableRes smallIcon: Int,
    body: NotificationBuilder.() -> Unit
): android.app.Notification? {
    if (areNotificationsEnabled(channelId)) {
        val notification = notification(context, channelId, smallIcon, body)
        notify(notificationId, notification)
        return notification
    }
    return null
}

/**
 * Build new notification and post it. If notifications aren't enabled for the channel
 * than the notification will not be shown.
 *
 * @param context application context
 * @param notificationId the ID of the notification
 * @param channelId The constructed Notification will be posted on this [NotificationChannel].
 * @param smallIcon A resource id in the app's package of the drawable to use as Notification icon in status bar
 *
 * @return Created notification or null if it hasn't been created
 */
fun notify(
    context: Context,
    notificationId: Int,
    channelId: String,
    @DrawableRes smallIcon: Int
): android.app.Notification? {
    val notificationManager = NotificationManagerCompat.from(context)
    if (notificationManager.areNotificationsEnabled(channelId)) {
        val notification = notification(context, channelId, smallIcon)
        notificationManager.notify(notificationId, notification)
        return notification
    }
    return null
}

/**
 * Build new notification and post it. If notifications aren't enabled for the channel
 * than the notification will not be shown.
 *
 * @param context application context
 * @param notificationId the ID of the notification
 * @param channelId The constructed Notification will be posted on this [NotificationChannel].
 * @param smallIcon A resource id in the app's package of the drawable to use as Notification icon in status bar
 *
 * @return Created notification or null if it hasn't been created
 */
fun NotificationManagerCompat.notify(
    context: Context,
    notificationId: Int,
    channelId: String,
    @DrawableRes smallIcon: Int
): android.app.Notification? {
    if (areNotificationsEnabled(channelId)) {
        val notification = notification(context, channelId, smallIcon)
        notify(notificationId, notification)
        return notification
    }
    return null
}

/**
 * Check that notifications for the app and in channel with [channelId] are available.
 * For Pre-O (8.0) devices check only [NotificationManagerCompat.areNotificationsEnabled]
 *
 * @param channelId Id of the channel
 *
 * return If notifications for the app and the channel are available.
 */
@Suppress("ReturnCount")
fun NotificationManagerCompat.areNotificationsEnabled(channelId: String): Boolean {
    // Check that notifications isn't disabled for the app
    if (!areNotificationsEnabled()) return false

    // Check notification channels
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Check that notification channel isn't disabled
        val channel = getNotificationChannel(channelId) ?: return true

        if (channel.importance == NotificationManager.IMPORTANCE_NONE) return false

        // Check that notification channel group isn't blocked
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val channelGroup = channel.group?.let(::getNotificationChannelGroup)
            if (channelGroup != null && channelGroup.isBlocked) return false
        }
    }

    return true
}
