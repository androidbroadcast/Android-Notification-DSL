package com.kirich1409.androidnotificationdsl

import androidx.core.app.NotificationCompat

enum class NotificationDefaults(val intValue: Int) {
    ALL(NotificationCompat.DEFAULT_ALL),
    LIGHTS(NotificationCompat.DEFAULT_LIGHTS),
    SOUND(NotificationCompat.DEFAULT_SOUND),
    VIBRATE(NotificationCompat.DEFAULT_VIBRATE)
}
