package com.kirich1409.androidnotificationdsl.annotations

import androidx.annotation.IntDef
import androidx.core.app.NotificationManagerCompat

@IntDef(
    NotificationManagerCompat.IMPORTANCE_DEFAULT,
    NotificationManagerCompat.IMPORTANCE_HIGH,
    NotificationManagerCompat.IMPORTANCE_LOW,
    NotificationManagerCompat.IMPORTANCE_MAX,
    NotificationManagerCompat.IMPORTANCE_MIN,
    NotificationManagerCompat.IMPORTANCE_NONE,
    NotificationManagerCompat.IMPORTANCE_UNSPECIFIED
)
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class NotificationImportance