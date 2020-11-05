package com.kirich1409.androidnotificationdsl.sample.presentation

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import com.kirich1409.androidnotificationdsl.NotificationCategory
import com.kirich1409.androidnotificationdsl.NotificationPriority
import com.kirich1409.androidnotificationdsl.action.SemanticAction
import com.kirich1409.androidnotificationdsl.notification
import com.kirich1409.androidnotificationdsl.sample.CHANNEL_DEFAULT
import com.kirich1409.androidnotificationdsl.sample.R
import com.kirich1409.androidnotificationdsl.style.bigtext.bigTextStyle
import com.kirich1409.androidnotificationdsl.wearable.wearable
import java.util.Date

class Message(
    val message: String,
    val from: String,
    val date: Date,
    val personIconPath: String,
    val personUri: String
)

val markAsReadIntent: PendingIntent get() = TODO()

fun sampleDsl(context: Context, message: Message): Notification {
    return notification(context, CHANNEL_DEFAULT, R.drawable.ic_android_white_24dp) {
        contentText = message.message
        autoCancel = true
        contentTitle = message.from
        priority = NotificationPriority.HIGH
        whenTime = message.date.time
        largeIcon = BitmapFactory.decodeFile(message.personIconPath)
        category = NotificationCategory.MESSAGE
        persons += message.personUri
        onlyAlertOnce = true
        actions {
            action("Mark as Read", markAsReadIntent) {
                contextual = true
                semanticAction = SemanticAction.MARK_AS_READ
                showsUserInterface = false
            }
        }
        bigTextStyle {
            text = message.message
        }
        wearable {
            contentIntentAvailableOffline = false
        }
    }
}
