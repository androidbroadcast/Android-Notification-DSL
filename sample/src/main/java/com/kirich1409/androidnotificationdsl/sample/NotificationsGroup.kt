package com.kirich1409.androidnotificationdsl.sample

import android.content.Context
import com.kirich1409.androidnotificationdsl.contentText
import com.kirich1409.androidnotificationdsl.contentTitle
import com.kirich1409.androidnotificationdsl.group.NotificationsGroup
import com.kirich1409.androidnotificationdsl.group.notificationsGroup

fun buildNotificationsGroup(context: Context): NotificationsGroup {
    return notificationsGroup(context, groupKey = GROUP_KEY, channelId = CHANNEL) {
        summary(SUMMARY_NOTIFICATION_ID, smallIcon = R.drawable.ic_android_white_24dp) {
            contentTitle(R.string.notification_summary_title)
            contentText(R.string.notification_summary_text)
        }

        notifications {
            notification(NOTIFICATION_1_ID, smallIcon = R.drawable.ic_android_white_24dp) {
                contentTitle(R.string.notification_1_title)
            }

            notification(NOTIFICATION_2_ID, smallIcon = R.drawable.ic_android_white_24dp) {
                contentTitle(R.string.notification_2_title)
            }
        }
    }
}

private const val NOTIFICATION_2_ID = 3
private const val NOTIFICATION_1_ID = 2
private const val SUMMARY_NOTIFICATION_ID = 1
private const val GROUP_KEY = "group1"
private const val CHANNEL = NOTIFICATION_CHANNEL_DEFAULT