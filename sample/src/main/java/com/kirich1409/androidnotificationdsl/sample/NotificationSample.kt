package com.kirich1409.androidnotificationdsl.sample

import android.app.Notification
import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.notification
import com.kirich1409.androidnotificationdsl.style.bigtext.bigTextStyle
import com.kirich1409.androidnotificationdsl.utils.activityPendingIntent
import com.kirich1409.androidnotificationdsl.wearable.wearable

fun buildNotification(context: Context) =
    notification(context, NOTIFICATION_CHANNEL_DEFAULT, R.drawable.ic_android_white_24dp) {
        contentTitle = "Notification title"
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
