package com.kirich1409.androidnotificationdsl.sample

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.kirich1409.androidnotificationdsl.notification

fun buildNotification(context: Context) =
    context.notification(NOTIFICATION_CHANNEL_DEFAULT, R.drawable.ic_android_white_24dp) {
        contentTitle("Notification title")
        actions {
            action(
                title = "Edit",
                intent = PendingIntent.getActivity(
                    context,
                    ACTION_EDIT_REQUEST_CODE,
                    Intent(),
                    PendingIntent.FLAG_CANCEL_CURRENT
                ),
                icon = R.drawable.ic_baseline_edit_24
            )
            action(
                title = "View",
                intent = PendingIntent.getActivity(
                    context,
                    ACTION_VIEW_REQUEST_CODE,
                    Intent(),
                    PendingIntent.FLAG_CANCEL_CURRENT
                ),
                icon = R.drawable.ic_baseline_remove_red_eye_24
            )
        }
    }

const val ACTION_EDIT_REQUEST_CODE = 100
const val ACTION_VIEW_REQUEST_CODE = 101