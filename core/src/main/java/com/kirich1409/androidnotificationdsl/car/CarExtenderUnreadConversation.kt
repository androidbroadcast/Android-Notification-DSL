@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.car

import android.app.PendingIntent
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.RemoteInput
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationCarExtenderUnreadConversationMarker
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import androidx.core.app.RemoteInput as AndroidRemoteInput

@NotificationCarExtenderUnreadConversationMarker
inline class CarExtenderUnreadConversation(
    private val builder: NotificationCompat.CarExtender.UnreadConversation.Builder
) {

    fun latestTimestamp(timestamp: Long) {
        builder.setLatestTimestamp(timestamp)
    }

    fun readPendingIntent(pendingIntent: PendingIntent) {
        builder.setReadPendingIntent(pendingIntent)
    }

    fun remoteInput(
        pendingIntent: PendingIntent,
        resultKey: String,
        body: @NotificationCarExtenderUnreadConversationMarker RemoteInput.() -> Unit
    ) {
        remoteInput(pendingIntent, RemoteInput(resultKey, body))
    }

    fun remoteInput(pendingIntent: PendingIntent, remoteInput: AndroidRemoteInput) {
        builder.setReplyAction(pendingIntent, remoteInput)
    }

    internal fun build(): NotificationCompat.CarExtender.UnreadConversation {
        return builder.build()
    }
}

@ExperimentalTime
fun CarExtenderUnreadConversation.latestTimestamp(timestamp: Duration) {
    return latestTimestamp(timestamp.toLongMilliseconds())
}
