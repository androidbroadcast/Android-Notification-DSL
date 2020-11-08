@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.car

import android.graphics.Bitmap
import androidx.annotation.ColorInt
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.annotations.NotificationMarker
import com.kirich1409.androidnotificationdsl.extend

@NotificationCarExtenderMarker
@Suppress("UndocumentedPublicClass")
class CarExtenderBuilder @PublishedApi internal constructor(
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
}


/**
 * Apply an [CarExtender][NotificationCompat.CarExtender] to this notification.
 *
 * Extenders may be used to add metadata or change options on this builder.
 */
inline fun NotificationBuilder.car(body: @NotificationMarker CarExtenderBuilder.() -> Unit) {
    extend(carExtender(body))
}

/**
 * Create a [CarExtender][NotificationCompat.CarExtender].
 */
inline fun carExtender(body: @NotificationMarker CarExtenderBuilder.() -> Unit): NotificationCompat.CarExtender {
    return NotificationCompat.CarExtender().apply { CarExtenderBuilder(this).body() }
}

