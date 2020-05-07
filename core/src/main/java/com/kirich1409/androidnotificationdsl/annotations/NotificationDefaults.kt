package com.kirich1409.androidnotificationdsl.annotations

import androidx.annotation.IntDef
import androidx.core.app.NotificationCompat

@Suppress("UndocumentedPublicClass")
@IntDef(
    flag = true, value = [
        NotificationCompat.DEFAULT_ALL,
        NotificationCompat.DEFAULT_SOUND,
        NotificationCompat.DEFAULT_LIGHTS,
        NotificationCompat.DEFAULT_VIBRATE
    ]
)
@Retention(AnnotationRetention.SOURCE)
annotation class NotificationDefaults
