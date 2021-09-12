@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import androidx.core.app.NotificationCompat

enum class NotificationCategory(internal val stringValue: String) {
    ALARM(NotificationCompat.CATEGORY_ALARM),
    CALL(NotificationCompat.CATEGORY_CALL),
    EMAIL(NotificationCompat.CATEGORY_EMAIL),
    ERROR(NotificationCompat.CATEGORY_ERROR),
    EVENT(NotificationCompat.CATEGORY_EVENT),
    MESSAGE(NotificationCompat.CATEGORY_MESSAGE),
    NAVIGATION(NotificationCompat.CATEGORY_NAVIGATION),
    PROGRESS(NotificationCompat.CATEGORY_PROGRESS),
    PROMO(NotificationCompat.CATEGORY_PROMO),
    RECOMMENDATION(NotificationCompat.CATEGORY_RECOMMENDATION),
    REMINDER(NotificationCompat.CATEGORY_REMINDER),
    SERVICE(NotificationCompat.CATEGORY_SERVICE),
    SOCIAL(NotificationCompat.CATEGORY_SOCIAL),
    STATUS(NotificationCompat.CATEGORY_STATUS),
    SYSTEM(NotificationCompat.CATEGORY_SYSTEM),
    TRANSPORT(NotificationCompat.CATEGORY_TRANSPORT),
    ;

    companion object {

        fun fromString(stringValue: String): NotificationCategory {
            return values().find { category -> category.stringValue == stringValue }
                ?: throw IllegalArgumentException("Cant find value for \"$stringValue\"")
        }
    }
}
