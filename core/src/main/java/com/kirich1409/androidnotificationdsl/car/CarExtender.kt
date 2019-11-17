@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.car

import android.graphics.Bitmap
import androidx.annotation.ColorInt
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationCarExtenderMarker

@NotificationCarExtenderMarker
inline class CarExtender(private val extender: NotificationCompat.CarExtender) {

    var color: Int
        @ColorInt get() = extender.color
        set(@ColorInt value) {
            extender.color = value
        }

    var largeIcon: Bitmap?
        get() = extender.largeIcon
        set(value) {
            extender.largeIcon = value
        }

    var unreadConversation: NotificationCompat.CarExtender.UnreadConversation?
        get() = extender.unreadConversation
        set(unreadConversation) {
            extender.unreadConversation = unreadConversation
        }

    fun unreadConversation(
        name: String,
        body: @NotificationCarExtenderMarker CarExtenderUnreadConversation.() -> Unit
    ) {
        val builder = NotificationCompat.CarExtender.UnreadConversation.Builder(name)
        CarExtenderUnreadConversation(builder).apply(body)
        extender.unreadConversation = builder.build()
    }
}

