package com.kirich1409.androidnotificationdsl.annotations

import androidx.annotation.IntDef
import androidx.core.app.NotificationCompat

@Suppress("UndocumentedPublicClass")
@IntDef(
    NotificationCompat.PRIORITY_MIN,
    NotificationCompat.PRIORITY_LOW,
    NotificationCompat.PRIORITY_DEFAULT,
    NotificationCompat.PRIORITY_HIGH,
    NotificationCompat.PRIORITY_MAX
)
@Retention(AnnotationRetention.SOURCE)
annotation class NotificationPriority
