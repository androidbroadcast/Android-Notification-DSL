@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import androidx.core.app.NotificationManagerCompat

enum class NotificationImportance(val intValue: Int) {
    DEFAULT(NotificationManagerCompat.IMPORTANCE_DEFAULT),
    HIGH(NotificationManagerCompat.IMPORTANCE_HIGH),
    LOW(NotificationManagerCompat.IMPORTANCE_LOW),
    MAX(NotificationManagerCompat.IMPORTANCE_MAX),
    MIN(NotificationManagerCompat.IMPORTANCE_MIN),
    NONE(NotificationManagerCompat.IMPORTANCE_NONE),
    UNSPECIFIED(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED),
    ;

    companion object {

        fun fromInt(intValue: Int): NotificationImportance {
            return values().find { importance -> importance.intValue == intValue }
                ?: throw IllegalArgumentException(
                    "NotificationImportance for value $intValue wasn't found"
                )
        }
    }
}
