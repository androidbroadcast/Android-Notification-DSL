@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.style.message

import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.style.message.annotations.NotificationMessagingStyleMarker

@NotificationMessagingStyleMarker
@Suppress("UndocumentedPublicClass")
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

    /**
     * Whether this conversation notification represents a group
     */
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

    /**
     * Adds [Message][NotificationCompat.MessagingStyle.Message]s for display in this notification.
     */
    inline fun messages(body: Messages.() -> Unit) {
        Messages(messagingStyle).body()
    }
}

/**
 * Creates a new [MessagingStyle] instance and set is as style of the notification.
 * Note that [Person] must have a non-empty name.
 *
 * @param user This [Person]'s name will be shown when this app's notification is being replied to.
 * It's used temporarily so the app has time to process the send request and repost the notification
 * with updates to the conversation.
 */
inline fun Notification.messagingStyle(
    user: Person,
    body: @NotificationMarker MessagingStyle.() -> Unit
) {
    style(NotificationCompat.MessagingStyle(user).also { MessagingStyle(it).body() })
}

/**
 * Creates a new [MessagingStyle] instance. Note that [Person] must have a non-empty name.
 *
 * @param user This [Person]'s name will be shown when this app's notification is being replied to.
 * It's used temporarily so the app has time to process the send request and repost the notification
 * with updates to the conversation.
 */
inline fun messagingStyle(
    user: Person,
    body: @NotificationMarker MessagingStyle.() -> Unit
): NotificationCompat.MessagingStyle {
    return NotificationCompat.MessagingStyle(user).also { MessagingStyle(it).body() }
}

