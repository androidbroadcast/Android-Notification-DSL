@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.sample.extentions

import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.NotificationDsl
import com.kirich1409.androidnotificationdsl.progress.progressNotification
import com.kirich1409.androidnotificationdsl.sample.CHANNEL_DEFAULT
import com.kirich1409.androidnotificationdsl.sample.MainActivity
import com.kirich1409.androidnotificationdsl.sample.R
import com.kirich1409.androidnotificationdsl.utils.activityPendingIntent
import java.util.Date

@OptIn(NotificationDsl::class)
fun progressExtensionSample(context: Context) =
    progressNotification(context, CHANNEL_DEFAULT, R.drawable.ic_android_white_24dp) {
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

class Message(
    val message: String,
    val from: String,
    val personUri: String,
    val date: Date,
    val personIconPath: String
)


fun sampleNotification(context: Context, message: Message) {
    val markAsReadIntent = context.activityPendingIntent(1, MainActivity::class)

    NotificationCompat.Builder(context, CHANNEL_DEFAULT)
        .setContentText(message.message)
        .addPerson(message.personUri)
        .setAutoCancel(true)
        .setContentTitle(message.from)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setDefaults(NotificationCompat.DEFAULT_ALL)
        .setOnlyAlertOnce(true)
        .setWhen(message.date.time)
        .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
        .setSmallIcon(R.drawable.ic_android_white_24dp)
        .setLargeIcon(BitmapFactory.decodeFile(message.personIconPath))
        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
        .addAction(
            NotificationCompat.Action.Builder(null, "Mark as Read", markAsReadIntent)
                .setContextual(false)
                .setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_MARK_AS_READ)
                .setShowsUserInterface(false)
                .build()
        )
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(message.message)
        )
        .extend(
            NotificationCompat.WearableExtender()
                .setContentIntentAvailableOffline(false)
        )
        .build()
}
