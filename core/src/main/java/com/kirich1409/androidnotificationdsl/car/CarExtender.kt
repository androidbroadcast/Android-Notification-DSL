@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.car

import android.graphics.Bitmap
import androidx.annotation.ColorInt
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.NotificationMarker

@NotificationCarExtenderMarker
class CarExtender @PublishedApi internal constructor(
    @PublishedApi internal val carExtender: NotificationCompat.CarExtender
) {

    var color: Int
        @ColorInt get() = carExtender.color
        set(@ColorInt value) {
            carExtender.color = value
        }

    var largeIcon: Bitmap?
        get() = carExtender.largeIcon
        set(value) {
            carExtender.largeIcon = value
        }

    var unreadConversation: NotificationCompat.CarExtender.UnreadConversation?
        get() = carExtender.unreadConversation
        set(unreadConversation) {
            carExtender.unreadConversation = unreadConversation
        }

    inline fun unreadConversation(
        name: String,
        body: @NotificationCarExtenderMarker CarExtenderUnreadConversation.() -> Unit
    ) {
        val builder = NotificationCompat.CarExtender.UnreadConversation.Builder(name)
        CarExtenderUnreadConversation(builder).apply(body)
        carExtender.unreadConversation = builder.build()
    }
}

inline fun Notification.car(body: @NotificationMarker CarExtender.() -> Unit) {
    val carExtender = NotificationCompat.CarExtender()
    CarExtender(carExtender).body()
    extend(carExtender)
}

