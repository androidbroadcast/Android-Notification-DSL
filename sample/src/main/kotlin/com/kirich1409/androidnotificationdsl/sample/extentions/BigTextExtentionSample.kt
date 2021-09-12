@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.sample.extentions

import android.content.Context
import com.kirich1409.androidnotificationdsl.expandable.bigTextNotification
import com.kirich1409.androidnotificationdsl.sample.MainActivity
import com.kirich1409.androidnotificationdsl.sample.CHANNEL_DEFAULT
import com.kirich1409.androidnotificationdsl.sample.R
import com.kirich1409.androidnotificationdsl.utils.activityPendingIntent

fun bigTextExtensionSample(context: Context) =
    bigTextNotification(context, CHANNEL_DEFAULT, R.drawable.ic_android_white_24dp) {
        title = "Collapsed"
        text = context.getText(R.string.long_text)

        expanded {
            title = "Expanded"
            text = "Summary text"
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
    }

const val ACTION_EDIT_REQUEST_CODE = 100
const val ACTION_VIEW_REQUEST_CODE = 101
