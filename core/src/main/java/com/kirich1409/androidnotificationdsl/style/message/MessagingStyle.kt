@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.style.message

import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.style

@NotificationMessagingStyleMarker
class MessagingStyle @PublishedApi internal constructor(
    @PublishedApi internal val messagingStyle: NotificationCompat.MessagingStyle
) {

    /**
     * The title to be displayed on this conversation.
     */
    inline var conversationTitle: CharSequence?
        get() = messagingStyle.conversationTitle
        set(value) {
            messagingStyle.conversationTitle = value
        }

    inline var groupConversation: Boolean
        get() = messagingStyle.isGroupConversation
        set(value) {
            messagingStyle.isGroupConversation = value
        }

    /**
     * Gets the list of [Message][NotificationCompat.MessagingStyle.Message] objects that represent the notification
     */
    inline val messages: List<NotificationCompat.MessagingStyle.Message>
        get() = messagingStyle.messages

    /**
     * Returns the person to be used for any replies sent by the user.
     */
    inline val user: Person get() = messagingStyle.user

    inline fun messages(body: Messages.() -> Unit) {
        Messages(messagingStyle).body()
    }
}

inline fun Notification.messagingStyle(
    person: Person,
    body: @NotificationMarker MessagingStyle.() -> Unit
): NotificationCompat.MessagingStyle {
    val messagingStyle = NotificationCompat.MessagingStyle(person)
    style(messagingStyle.also { MessagingStyle(it).body() })
    return messagingStyle
}

