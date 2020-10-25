@file:Suppress("UNUSED_VARIABLE")

package com.kirich1409.androidnotificationdsl.sample

import android.app.Notification
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.contentText
import com.kirich1409.androidnotificationdsl.contentTitle
import com.kirich1409.androidnotificationdsl.group.NotificationsGroup
import com.kirich1409.androidnotificationdsl.group.notificationsGroup

fun buildNotificationsGroup(context: Context): NotificationsGroup {
    return notificationsGroup(context, groupKey = GROUP_KEY, channelId = NOTIFICATION_CHANNEL_DEFAULT) {
        summary(SUMMARY_NOTIFICATION_ID, smallIcon = R.drawable.ic_android_white_24dp) {
            contentTitle(R.string.notification_summary_title)
            contentText(R.string.notification_summary_text)
        }

        notification(
            NOTIFICATION_1_ID,
            smallIcon = R.drawable.ic_android_white_24dp,
            channelId = NOTIFICATION_CHANNEL_LOW
        ) {
            contentTitle(R.string.notification_1_title)
        }

        notification(
            NOTIFICATION_2_ID,
            smallIcon = R.drawable.ic_android_white_24dp,
            channelId = NOTIFICATION_CHANNEL_LOW
        ) {
            contentTitle(R.string.notification_2_title)
        }
    }
}

fun buildNotificationsGroupOld(context: Context): List<Notification> {
    val notifications = mutableListOf<Notification>()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        notifications += NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_DEFAULT)
            .setContentTitle(context.getText(R.string.notification_summary_title))
            .setContentText(context.getText(R.string.notification_summary_text))
            .setSmallIcon(R.drawable.ic_android_white_24dp)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
            .setGroup(GROUP_KEY)
            .setGroupSummary(true)
            .build()
        // Show summary
    }

    val notification1Builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_LOW)
        .setContentTitle(context.getText(R.string.notification_1_title))
        .setSmallIcon(R.drawable.ic_android_white_24dp)
        .setPriority(NotificationCompat.PRIORITY_LOW)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        notification1Builder.setGroup(GROUP_KEY)
    }
    notifications += notification1Builder.build()

    val notification2Builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_LOW)
        .setContentTitle(context.getText(R.string.notification_2_title))
        .setSmallIcon(R.drawable.ic_android_white_24dp)
        .setPriority(NotificationCompat.PRIORITY_LOW)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        notification2Builder.setGroup(GROUP_KEY)
    }
    notifications += notification2Builder.build()

    return notifications.toList()
}

private const val NOTIFICATION_2_ID = 3
private const val NOTIFICATION_1_ID = 2
private const val SUMMARY_NOTIFICATION_ID = 1
private const val GROUP_KEY = "group1"
