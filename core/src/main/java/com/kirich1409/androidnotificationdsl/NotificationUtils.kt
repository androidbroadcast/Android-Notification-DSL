@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import android.app.NotificationChannel
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.internal.areNotificationsEnabled

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
    body: Notification.() -> Unit
): android.app.Notification? {
    val notificationManager = NotificationManagerCompat.from(context)
    return notificationManager.notify(context, notificationId, channelId, smallIcon, body)
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
inline fun NotificationManagerCompat.notify(
    context: Context,
    notificationId: Int,
    channelId: String,
    @DrawableRes smallIcon: Int,
    body: Notification.() -> Unit
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
