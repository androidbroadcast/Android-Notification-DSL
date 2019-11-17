package com.kirich1409.androidnotificationdsl.annotations

import androidx.annotation.IntDef
import androidx.core.app.NotificationCompat

@IntDef(
    NotificationCompat.VISIBILITY_PRIVATE,
    NotificationCompat.VISIBILITY_PUBLIC,
    NotificationCompat.VISIBILITY_SECRET
)
@Retention(AnnotationRetention.SOURCE)
annotation class NotificationVisibility