package com.kirich1409.androidnotificationdsl.internal

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.text.TextUtils
import androidx.core.app.NotificationManagerCompat

fun NotificationManagerCompat.areNotificationsEnabled(channelId: String): Boolean {
    if (!areNotificationsEnabled()) {
        return false
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && !TextUtils.isEmpty(channelId)) {
        val channel: NotificationChannel? = getNotificationChannel(channelId)
        return channel != null && channel.importance != NotificationManager.IMPORTANCE_NONE
    }

    return true
}