package com.kirich1409.androidnotificationdsl.sample

import android.content.Context
import com.kirich1409.androidnotificationdsl.notification
import com.kirich1409.androidnotificationdsl.utils.activityPendingIntent

fun buildNotification(context: Context) =
    notification(context, NOTIFICATION_CHANNEL_DEFAULT, R.drawable.ic_android_white_24dp) {
        contentTitle("Notification title")
        actions {
            action(
                title = "Edit",
                intent = context.activityPendingIntent(ACTION_EDIT_REQUEST_CODE, MainActivity::class),
                icon = R.drawable.ic_baseline_edit_24
            )
            action(
                title = "View",
                intent = context.activityPendingIntent<MainActivity>(ACTION_VIEW_REQUEST_CODE),
                icon = R.drawable.ic_baseline_remove_red_eye_24
            )
        }
    }

const val ACTION_EDIT_REQUEST_CODE = 100
const val ACTION_VIEW_REQUEST_CODE = 101
