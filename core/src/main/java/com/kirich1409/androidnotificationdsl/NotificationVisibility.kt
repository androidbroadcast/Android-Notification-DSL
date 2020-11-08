package com.kirich1409.androidnotificationdsl

import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.annotations.NotificationVisibilityDef

/**
 * The level of detail visible in the notification from the lock screen
 */
enum class NotificationVisibility(@NotificationVisibilityDef val intValue: Int) {

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

        fun from(notificationVisibility: Int): NotificationVisibility {
            return values().find { visibility ->
                visibility.intValue == notificationVisibility
            } ?: throw IllegalArgumentException("Unknown notificationVisibility argument value $notificationVisibility")
        }
    }
}
