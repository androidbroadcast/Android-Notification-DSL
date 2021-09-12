@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import androidx.core.app.NotificationCompat

/**
 * The level of detail visible in the notification from the lock screen
 */
enum class NotificationVisibility(val intValue: Int) {

    /**
     * Doesn't show any part of this notification on the lock screen.
     */
    SECRET(NotificationCompat.VISIBILITY_SECRET),

    /**
     * Shows basic information, such as the notification's icon and the content title,
     * but hides the notification's full content.
     */
    PRIVATE(NotificationCompat.VISIBILITY_PRIVATE),

    /**
     * Shows the notification's full content
     */
    PUBLIC(NotificationCompat.VISIBILITY_PUBLIC),
    ;

    companion object {

        fun fromInt(intValue: Int): NotificationVisibility {
            return values().find { visibility ->
                visibility.intValue == intValue
            } ?: throw IllegalArgumentException(
                "Unknown notificationVisibility argument value $intValue"
            )
        }
    }
}
