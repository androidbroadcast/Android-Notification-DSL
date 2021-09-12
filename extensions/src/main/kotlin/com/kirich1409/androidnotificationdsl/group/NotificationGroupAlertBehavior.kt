package com.kirich1409.androidnotificationdsl.group

import androidx.core.app.NotificationCompat

enum class NotificationGroupAlertBehavior(@NotificationCompat.GroupAlertBehavior val intValue: Int) {
    ALERT_ALL(NotificationCompat.GROUP_ALERT_ALL),
    ALERT_SUMMARY(NotificationCompat.GROUP_ALERT_SUMMARY),
    ALERT_CHILDREN(NotificationCompat.GROUP_ALERT_CHILDREN),
    ;

    companion object {

        fun fromIntValue(intValue: Int): NotificationGroupAlertBehavior {
            return values().find { behaviour ->
                behaviour.intValue == intValue
            } ?: throw IllegalArgumentException("Behavior wasn't found for $intValue")
        }
    }
}
