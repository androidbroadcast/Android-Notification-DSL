@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.media

import androidx.media.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.style

fun Notification.mediaStyle(body: @NotificationMarker MediaStyle.() -> Unit) {
    val mediaStyle = NotificationCompat.MediaStyle()
    MediaStyle(mediaStyle).body()
    style(mediaStyle)
}

fun Notification.decoratedMediaCustomViewStyle() {
    style(NotificationCompat.DecoratedMediaCustomViewStyle())
}