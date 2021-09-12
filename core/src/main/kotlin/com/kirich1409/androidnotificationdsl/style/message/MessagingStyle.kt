@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.style.message

import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.NotificationMarker

@NotificationMessagingStyleMarker
@Suppress("UndocumentedPublicClass")
class MessagingStyle @PublishedApi internal constructor(
    @PublishedApi internal val messagingStyle: NotificationCompat.MessagingStyle
) {

    /**
     * The title to be displayed on this conversation.
     */
    public inline var conversationTitle: CharSequence?
        get() = messagingStyle.conversationTitle
        set(value) {
            messagingStyle.conversationTitle = value
        }

    /**
     * Whether this conversation notification represents a group
     */
    public inline var groupConversation: Boolean
        get() = messagingStyle.isGroupConversation
        set(value) {
            messagingStyle.isGroupConversation = value
        }

    /**
     * Returns the person to be used for any replies sent by the user.
     */
    public inline val user: Person get() = messagingStyle.user

    public val messages = Messages(messagingStyle)

    public inline fun messages(body: Messages.() -> Unit) {
        messages.apply(body)
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
inline fun NotificationBuilder.messagingStyle(
    user: Person,
    body: @NotificationMarker MessagingStyle.() -> Unit
) {
    style = NotificationCompat.MessagingStyle(user).also { MessagingStyle(it).body() }
}

/**
 * Creates a new [MessagingStyle] instance. Note that [Person] must have a non-empty name.
 *
 * @param user This [Person]'s name will be shown when this app's notification is being replied to.
 * It's used temporarily so the app has time to process the send request and repost the notification
 * with updates to the conversation.
 */
inline fun newMessagingStyle(
    user: Person,
    body: @NotificationMarker MessagingStyle.() -> Unit
): NotificationCompat.MessagingStyle {
    return NotificationCompat.MessagingStyle(user)
        .also { style -> MessagingStyle(style).body() }
}

