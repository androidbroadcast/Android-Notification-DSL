package com.kirich1409.androidnotificationdsl.sample.extentions

import android.content.Context
import com.kirich1409.androidnotificationdsl.expandable.bigPictureNotification
import com.kirich1409.androidnotificationdsl.sample.CHANNEL_DEFAULT
import com.kirich1409.androidnotificationdsl.sample.R
import com.kirich1409.androidnotificationdsl.utils.asBitmap

fun bigPictureExtensionSample(context: Context) =
    bigPictureNotification(context, CHANNEL_DEFAULT, R.drawable.ic_android_white_24dp) {
        title = "Collapsed"
        text = "Sample notification"
        largeIcon = R.drawable.sea_collapsed.asBitmap(context.resources)

        expanded {
            bigPicture = R.drawable.sea_expanded_big.asBitmap(context.resources)
            // Remove large icon from the notification by default
            largeIcon = null
            title = "Expanded"
            text = "Summary text"
        }

        // Allow to add addition information to the notification via Core Notification DSL
        extend {
            whenTime = System.currentTimeMillis()
        }
    }
