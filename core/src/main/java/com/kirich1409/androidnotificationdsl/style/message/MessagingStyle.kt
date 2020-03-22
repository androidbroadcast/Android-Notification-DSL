@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.style.message

import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationMessagingStyleMarker
import com.kirich1409.androidnotificationdsl.style

@NotificationMessagingStyleMarker
inline class MessagingStyle(@PublishedApi internal val messagingStyle: NotificationCompat.MessagingStyle) {

    var conversationTitle: CharSequence?
        get() = messagingStyle.conversationTitle
        set(value) {
            messagingStyle.conversationTitle = value
        }

    var groupConversation: Boolean
        get() = messagingStyle.isGroupConversation
        set(value) {
            messagingStyle.isGroupConversation = value
        }

    val messages: List<NotificationCompat.MessagingStyle.Message>
        get() = messagingStyle.messages

    inline fun messages(body: Messages.() -> Unit) {
        Messages(messagingStyle).body()
    }

    val user: Person get() = messagingStyle.user
}

inline fun Notification.messagingStyle(
    person: Person,
    body: @NotificationMarker MessagingStyle.() -> Unit
) {
    style(NotificationCompat.MessagingStyle(person).also { MessagingStyle(it).body() })
}

