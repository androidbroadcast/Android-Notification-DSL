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

        fun from(@NotificationVisibilityDef notificationVisibility: Int): com.kirich1409.androidnotificationdsl.NotificationVisibility = when (notificationVisibility) {
            NotificationCompat.VISIBILITY_PRIVATE -> PRIVATE
            NotificationCompat.VISIBILITY_PUBLIC -> PUBLIC
            NotificationCompat.VISIBILITY_SECRET -> SECRET
            else -> throw IllegalArgumentException(
                "Unknown notificationVisibility argument value = $notificationVisibility"
            )
        }
    }
}
