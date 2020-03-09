@file:Suppress("unused", "MemberVisibilityCanBePrivate", "NOTHING_TO_INLINE")

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
    private val unreadConversation: NotificationCompat.CarExtender.UnreadConversation.Builder
) {

    fun latestTimestamp(timestamp: Long) {
        unreadConversation.setLatestTimestamp(timestamp)
    }

    fun readPendingIntent(pendingIntent: PendingIntent) {
        unreadConversation.setReadPendingIntent(pendingIntent)
    }

    inline fun remoteInput(
        pendingIntent: PendingIntent,
        resultKey: String,
        body: @NotificationCarExtenderUnreadConversationMarker RemoteInput.() -> Unit
    ) {
        val remoteInput = AndroidRemoteInput.Builder(resultKey)
            .also { builder -> RemoteInput(builder).body() }
            .build()
        remoteInput(pendingIntent, remoteInput)
    }

    fun remoteInput(pendingIntent: PendingIntent, remoteInput: AndroidRemoteInput) {
        unreadConversation.setReplyAction(pendingIntent, remoteInput)
    }

    @PublishedApi
    internal fun build(): NotificationCompat.CarExtender.UnreadConversation {
        return unreadConversation.build()
    }
}

@ExperimentalTime
inline fun CarExtenderUnreadConversation.latestTimestamp(timestamp: Duration) {
    return latestTimestamp(timestamp.toLongMilliseconds())
}
