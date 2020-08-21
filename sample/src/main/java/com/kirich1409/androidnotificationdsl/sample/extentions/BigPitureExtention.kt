package com.kirich1409.androidnotificationdsl.sample.extentions

import android.content.Context
import com.kirich1409.androidnotificationdsl.expandable.bigPictureNotification
import com.kirich1409.androidnotificationdsl.sample.NOTIFICATION_CHANNEL_DEFAULT
import com.kirich1409.androidnotificationdsl.sample.R
import com.kirich1409.androidnotificationdsl.utils.asBitmap

fun bigPictureExtensionSample(context: Context) {
    bigPictureNotification(context, NOTIFICATION_CHANNEL_DEFAULT, R.drawable.ic_android_white_24dp) {
        title = "Collapsed"
        text = "Text in collapsed and expanded state"
        largeIcon = R.drawable.sea_collapsed.asBitmap(context.resources)

        expanded {
            bigPicture = R.drawable.sea_expanded_big.asBitmap(context.resources)
            // Remove large icon from the notification by default
            overrideLargeIcon(null)
            overrideTitle("Expanded")
        }

        // Allow to add addition information to the notification via Core Notification DSL
        extend {
            whenTime(System.currentTimeMillis())
        }
    }
}