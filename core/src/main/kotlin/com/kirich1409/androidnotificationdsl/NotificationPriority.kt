@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import androidx.core.app.NotificationCompat

enum class NotificationPriority(val intValue: Int) {
    MAX(NotificationCompat.PRIORITY_MAX),
    HIGH(NotificationCompat.PRIORITY_HIGH),
    DEFAULT(NotificationCompat.PRIORITY_DEFAULT),
    LOW(NotificationCompat.PRIORITY_LOW),
    MIN(NotificationCompat.PRIORITY_MIN),
}
