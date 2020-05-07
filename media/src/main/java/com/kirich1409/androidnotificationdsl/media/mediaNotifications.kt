@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.media

import androidx.media.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.NotificationMarker

/**
 * Create an instance of [MediaStyle] and set it as style of the [notification][this]
 */
inline fun Notification.mediaStyle(body: @NotificationMarker MediaStyle.() -> Unit) {
    style(NotificationCompat.MediaStyle().also { style -> MediaStyle(style).body() })
}

/**
 * Create an instance of [MediaStyle][NotificationCompat.MediaStyle]
 */
inline fun mediaStyle(body: @NotificationMarker MediaStyle.() -> Unit): NotificationCompat.MediaStyle {
    return NotificationCompat.MediaStyle().also { style -> MediaStyle(style).body() }
}

/**
 * Create an instance of [DecoratedMediaCustomViewStyle][NotificationCompat.DecoratedMediaCustomViewStyle]
 * and set it as style of the [notification][this]
 */
fun Notification.decoratedMediaCustomViewStyle() {
    style(NotificationCompat.DecoratedMediaCustomViewStyle())
}

/**
 * Create an instance of [DecoratedMediaCustomViewStyle][NotificationCompat.DecoratedMediaCustomViewStyle]
 */
fun decoratedMediaCustomViewStyle(): NotificationCompat.DecoratedMediaCustomViewStyle {
    return NotificationCompat.DecoratedMediaCustomViewStyle()
}
