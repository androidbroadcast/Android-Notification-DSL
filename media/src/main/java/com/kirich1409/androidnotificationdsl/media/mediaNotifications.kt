@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.media

import androidx.media.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.annotations.NotificationMarker

/**
 * Create an instance of [MediaStyle] and set it as style of the [notification][this]
 */
inline fun NotificationBuilder.mediaStyle(body: @NotificationMarker MediaStyle.() -> Unit) {
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
fun NotificationBuilder.decoratedMediaCustomViewStyle() {
    style(NotificationCompat.DecoratedMediaCustomViewStyle())
}

/**
 * Create an instance of [DecoratedMediaCustomViewStyle][NotificationCompat.DecoratedMediaCustomViewStyle]
 */
fun decoratedMediaCustomViewStyle(): NotificationCompat.DecoratedMediaCustomViewStyle {
    return NotificationCompat.DecoratedMediaCustomViewStyle()
}
