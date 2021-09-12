@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.bubble

import android.app.PendingIntent
import androidx.annotation.Dimension
import androidx.annotation.IntRange
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.kirich1409.androidnotificationdsl.internal.requiredNotificationProperty

/**
 * Builder of notification's bubble metadata
 */
@NotificationBubbleMetadataMarker
class BubbleMetadataBuilder @PublishedApi internal constructor(
    private val bubbleMetadata: NotificationCompat.BubbleMetadata.Builder
) {

    /**
     * If set and the app creating the bubble is in the foreground, the bubble will be
     * posted in its expanded state,
     * with the contents of [intent][NotificationCompat.BubbleMetadata.getIntent] in a floating window.
     *
     * If the app creating the bubble is not in the foreground this flag has no effect.
     *
     * Generally this flag should only be set if the user has performed an action to
     * request or create a bubble.
     */
    var autoExpandBubble: Boolean = false
        set(shouldExpand) {
            field = shouldExpand
            bubbleMetadata.setAutoExpandBubble(shouldExpand)
        }

    /**
     * Sets an optional intent to send when this bubble is explicitly removed by the user.
     */
    var deleteIntent: PendingIntent? = null
        set(deleteIntent) {
            field = deleteIntent
            bubbleMetadata.setDeleteIntent(deleteIntent)
        }

    /**
     * Sets the desired height in DPs for the app content defined by
     * [intent], this height may not be respected if there is not
     * enough space on the screen or if the provided height is too small to be useful.
     *
     * If [desiredHeight] was previously called on this builder, the
     * previous value set will be cleared after calling this method, and this value will
     * be used instead.
     */
    @Dimension(unit = Dimension.DP)
    @IntRange(from = 0)
    var desiredHeight: Int = 0
        set(height) {
            field = height
            bubbleMetadata.setDesiredHeight(height)
        }

    /**
     * Sets the icon that will represent the bubble when it is collapsed.
     *
     * An icon is required and should be representative of the content within the bubble.
     * If your app produces multiple bubbles, the image should be unique for each of them.
     *
     * The shape of a bubble icon is adaptive and can match the device theme.
     *
     * If your icon is bitmap-based, you should create it using [IconCompat.createWithAdaptiveBitmap],
     * otherwise this method will throw.
     *
     * If your icon is not bitmap-based, you should expect that the icon will be tinted.
     */
    var icon: IconCompat by requiredNotificationProperty("icon") {
        bubbleMetadata.setIcon(icon)
    }

    /**
     * Sets the intent that will be used when the bubble is expanded. This will display the
     * app content in a floating window over the existing foreground activity.
     */
    var intent: PendingIntent by requiredNotificationProperty("intent") {
        bubbleMetadata.setIntent(intent)
    }

    /**
     * If set and the app posting the bubble is in the foreground, the bubble will be
     * posted **without** the associated notification in the notification shade.
     *
     * If the app posting the bubble is not in the foreground this flag has no effect.
     *
     * Generally this flag should only be set if the user has performed an action to
     * request or create a bubble, or if the user has seen the content in the notification
     * and the notification is no longer relevant.
     */
    var suppressNotification: Boolean = false
        set(shouldSuppressNotification) {
            field = shouldSuppressNotification
            bubbleMetadata.setSuppressNotification(shouldSuppressNotification)
        }
}
