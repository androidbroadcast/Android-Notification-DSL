@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.car

import android.graphics.Bitmap
import androidx.annotation.ColorInt
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.car.annotations.NotificationCarExtenderMarker

@NotificationCarExtenderMarker
@Suppress("UndocumentedPublicClass")
class CarExtender @PublishedApi internal constructor(
    @PublishedApi internal val carExtender: NotificationCompat.CarExtender
) {

    /**
     * The accent color
     */
    inline var color: Int
        @ColorInt get() = carExtender.color
        set(@ColorInt value) {
            carExtender.color = value
        }

    /**
     * The large icon used in this car notification, or null if no icon has been set.
     */
    inline var largeIcon: Bitmap?
        get() = carExtender.largeIcon
        set(value) {
            carExtender.largeIcon = value
        }

    /**
     * The unread conversation conveyed by this notification.
     */
    @Deprecated("UnreadConversation is no longer supported. Use MessagingStyle instead")
    inline var unreadConversation: NotificationCompat.CarExtender.UnreadConversation?
        get() = carExtender.unreadConversation
        set(unreadConversation) {
            carExtender.unreadConversation = unreadConversation
        }

    /**
     * Sets the unread conversation in a message notification.
     *
     * @param name The name of the other participant in the conversation.
     */
    @Deprecated("UnreadConversation is no longer supported. Use MessagingStyle instead")
    inline fun unreadConversation(
        name: String,
        body: @NotificationCarExtenderMarker CarExtenderUnreadConversation.() -> Unit
    ) {
        val builder = NotificationCompat.CarExtender.UnreadConversation.Builder(name)
        CarExtenderUnreadConversation(builder).apply(body)
        carExtender.unreadConversation = builder.build()
    }
}


/**
 * Apply an [CarExtender][NotificationCompat.CarExtender] to this notification.
 *
 * Extenders may be used to add metadata or change options on this builder.
 */
inline fun Notification.car(body: @NotificationMarker CarExtender.() -> Unit) {
    extend(carExtender(body))
}

/**
 * Create a [CarExtender][NotificationCompat.CarExtender].
 */
inline fun carExtender(body: @NotificationMarker CarExtender.() -> Unit): NotificationCompat.CarExtender {
    return NotificationCompat.CarExtender().apply { CarExtender(this).body() }
}

