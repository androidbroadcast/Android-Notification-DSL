@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.style.message

import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationMessagingStyleMarker

@NotificationMessagingStyleMarker
class MessagingStyle internal constructor(private val style: NotificationCompat.MessagingStyle) {

    var conversationTitle: CharSequence?
        get() = style.conversationTitle
        set(value) {
            style.conversationTitle = value
        }

    var groupConversation: Boolean
        get() = style.isGroupConversation
        set(value) {
            style.isGroupConversation = value
        }

    val messages: List<NotificationCompat.MessagingStyle.Message>
        get() = style.messages

    fun messages(body: Messages.() -> Unit) {
        Messages(style).body()
    }

    val user: Person get() = style.user
}

