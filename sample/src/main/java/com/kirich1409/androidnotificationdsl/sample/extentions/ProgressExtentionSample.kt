package com.kirich1409.androidnotificationdsl.sample.extentions

import android.content.Context
import com.kirich1409.androidnotificationdsl.progress.progressNotification
import com.kirich1409.androidnotificationdsl.sample.MainActivity
import com.kirich1409.androidnotificationdsl.sample.NOTIFICATION_CHANNEL_DEFAULT
import com.kirich1409.androidnotificationdsl.sample.R
import com.kirich1409.androidnotificationdsl.utils.activityPendingIntent

fun progressExtensionSample(context: Context) =
    progressNotification(context, NOTIFICATION_CHANNEL_DEFAULT, R.drawable.ic_android_white_24dp) {
        title = "Downloading..."
        progressText = "6 seconds left"
        indeterminated = false

        progress {
            current = 4
            max = 10
        }

        actions {
            action(
                title = "Cancel",
                intent = context.activityPendingIntent(1, MainActivity::class)
            )
        }
    }
