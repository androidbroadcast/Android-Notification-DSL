@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.car

import android.app.PendingIntent
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.car.annotations.NotificationCarExtenderUnreadConversationMarker
import com.kirich1409.androidnotificationdsl.remoteinput.RemoteInputBuilder
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import androidx.core.app.RemoteInput as AndroidRemoteInput

@NotificationCarExtenderUnreadConversationMarker
@Deprecated("UnreadConversation is no longer supported. Use MessagingStyle instead")
@Suppress("UndocumentedPublicClass")
class CarExtenderUnreadConversationBuilder @PublishedApi internal constructor(
    private val unreadConversation: NotificationCompat.CarExtender.UnreadConversation.Builder
) {
    /**
     * Sets the timestamp of the most recent message in an unread conversation.
     *
     * If a messaging notification has been posted by your application and has not yet been cancelled,
     * posting a later notification with the same id and tag but without a newer timestamp may result
     * in Android Auto not displaying a heads up notification for the later notification.
     *
     * @param timestamp The timestamp of the most recent message in the conversation.
     */
    fun latestTimestamp(timestamp: Long) {
        unreadConversation.setLatestTimestamp(timestamp)
    }

    /**
     * Sets the pending intent that will be sent once the messages in this notification are read.
     *
     * @param pendingIntent The pending intent to use.
     */
    fun readPendingIntent(pendingIntent: PendingIntent) {
        unreadConversation.setReadPendingIntent(pendingIntent)
    }

    /**
     * Sets the pending intent and remote input which will convey the reply to this notification.
     *
     * @param pendingIntent The pending intent which will be triggered on a reply.
     * @param resultKey the Bundle key that refers to this input when collected from the user
     */
    inline fun remoteInput(
        pendingIntent: PendingIntent,
        resultKey: String,
        body: @NotificationCarExtenderUnreadConversationMarker RemoteInputBuilder.() -> Unit
    ) {
        val remoteInput = AndroidRemoteInput.Builder(resultKey)
            .also { builder -> RemoteInputBuilder(builder)
                .body() }
            .build()
        remoteInput(pendingIntent, remoteInput)
    }

    /**
     * Sets the pending intent and remote input which will convey the reply to this notification.
     *
     * @param pendingIntent The pending intent which will be triggered on a reply.
     * @param remoteInput The remote input parcelable which will carry the reply.
     */
    fun remoteInput(pendingIntent: PendingIntent, remoteInput: AndroidRemoteInput) {
        unreadConversation.setReplyAction(pendingIntent, remoteInput)
    }
}

/**
 * Sets the timestamp of the most recent message in an unread conversation.
 *
 * If a messaging notification has been posted by your application and has not yet been cancelled,
 * posting a later notification with the same id and tag but without a newer timestamp may result
 * in Android Auto not displaying a heads up notification for the later notification.
 *
 * @param timestamp The timestamp of the most recent message in the conversation.
 */
@ExperimentalTime
fun CarExtenderUnreadConversationBuilder.latestTimestamp(timestamp: Duration) {
    return latestTimestamp(timestamp.toLongMilliseconds())
}
