package com.kirich1409.androidnotificationdsl

import android.app.PendingIntent
import android.content.Context
import android.content.Intent

private const val DEFAULT_CHANNEL_ID = "channel_id"

fun buildNotification(context: Context) = context.notification(DEFAULT_CHANNEL_ID) {
    actions {
        action(
            title = "Sample",
            intent = PendingIntent.getActivity(context, 100, Intent(), PendingIntent.FLAG_CANCEL_CURRENT)
        ) {
        }
    }
    contentTitle("")
}