package com.kirich1409.androidnotificationdsl

import android.widget.RemoteViews

data class NotificationTicker(
    val text: CharSequence,
    val views: RemoteViews? = null
)

fun ticker(text: CharSequence, views: RemoteViews? = null): NotificationTicker {
    return NotificationTicker(text, views)
}
