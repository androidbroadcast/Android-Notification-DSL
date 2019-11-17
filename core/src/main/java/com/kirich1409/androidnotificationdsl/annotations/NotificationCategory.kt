package com.kirich1409.androidnotificationdsl.annotations

import androidx.annotation.StringDef
import androidx.core.app.NotificationCompat

@StringDef(
    NotificationCompat.CATEGORY_ALARM,
    NotificationCompat.CATEGORY_CALL,
    NotificationCompat.CATEGORY_EMAIL,
    NotificationCompat.CATEGORY_ERROR,
    NotificationCompat.CATEGORY_EVENT,
    NotificationCompat.CATEGORY_MESSAGE,
    NotificationCompat.CATEGORY_NAVIGATION,
    NotificationCompat.CATEGORY_PROGRESS,
    NotificationCompat.CATEGORY_PROMO,
    NotificationCompat.CATEGORY_RECOMMENDATION,
    NotificationCompat.CATEGORY_REMINDER,
    NotificationCompat.CATEGORY_SERVICE,
    NotificationCompat.CATEGORY_SOCIAL,
    NotificationCompat.CATEGORY_STATUS,
    NotificationCompat.CATEGORY_SYSTEM
)
@Retention(AnnotationRetention.SOURCE)
annotation class NotificationCategory