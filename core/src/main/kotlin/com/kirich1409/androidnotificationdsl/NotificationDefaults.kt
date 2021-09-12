@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import androidx.core.app.NotificationCompat

enum class NotificationDefaults(internal val intValue: Int) {
    ALL(NotificationCompat.DEFAULT_ALL),
    LIGHTS(NotificationCompat.DEFAULT_LIGHTS),
    SOUND(NotificationCompat.DEFAULT_SOUND),
    VIBRATE(NotificationCompat.DEFAULT_VIBRATE),
    ;

    companion object {

        fun fromInt(intValue: Int): NotificationDefaults {
            return values().find { defaults -> defaults.intValue == intValue }
                ?: throw IllegalArgumentException("Can't find Defaults for $intValue")
        }
    }
}
