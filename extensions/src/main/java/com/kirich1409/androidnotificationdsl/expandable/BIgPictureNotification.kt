package com.kirich1409.androidnotificationdsl.expandable

import android.app.Notification
import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import com.kirich1409.androidnotificationdsl.action.Actions
import com.kirich1409.androidnotificationdsl.expandable.annotations.BigPictureNotificationBuilderMarker
import com.kirich1409.androidnotificationdsl.notification
import com.kirich1409.androidnotificationdsl.style.bigpicture.bigPictureStyle
import com.kirich1409.androidnotificationdsl.utils.internal.NOTHING
import com.kirich1409.androidnotificationdsl.utils.internal.requiredNotificationProperty

/**
 * Create new notification with displaying big picture in expanded state
 */
fun bigPictureNotification(
    context: Context,
    channelId: String,
    @DrawableRes smallIcon: Int,
    body: BigPictureNotificationBuilder.() -> Unit
): Notification {
    val builder = BigPictureNotificationBuilder().apply(body)
    return notification(context, channelId, smallIcon) {
        builder.title?.let(::contentTitle)
        builder.text?.let(::contentText)
        builder.largeIcon?.let(::largeIcon)
        bigPictureStyle {
            picture(requireNotNull(builder.expanded.bigPicture))
            largeIcon(builder.expanded.largeIcon)

            val title = builder.expanded.title
            if (title is CharSequence?) {
                contentTitle(title)
            }

            builder.expanded.buildActions?.let(this@notification::actions)
        }
        builder.extender?.invoke(this)
    }
}

@BigPictureNotificationBuilderMarker
class BigPictureNotificationBuilder internal constructor() {

    /**
     * Notification title
     */
    var title: CharSequence? = null

    /**
     * Notification text
     */
    var text: CharSequence? = null

    /**
     * Notification large icon
     */
    var largeIcon: Bitmap? = null

    internal val expanded = Expanded()

    internal var extender: (com.kirich1409.androidnotificationdsl.Notification.() -> Unit)? = null

    fun expanded(body: @BigPictureNotificationBuilderMarker Expanded.() -> Unit) {
        expanded.body()
    }

    fun extend(body: com.kirich1409.androidnotificationdsl.Notification.() -> Unit) {
        extender = body
    }

    class Expanded internal constructor() {

        /**
         * Picture displayed in notification expanded state
         */
        var bigPicture: Bitmap by requiredNotificationProperty("bigPicture")

        internal var largeIcon: Bitmap? = null

        internal var title: Any? = NOTHING

        internal var buildActions: (Actions.() -> Unit)? = null

        /**
         * Replace large icon in expanded state. By default will be null and hide large icon
         */
        fun overrideLargeIcon(largeIcon: Bitmap?) {
            this.largeIcon = largeIcon
        }

        /**
         * Replace notification title in expanded state
         */
        fun overrideTitle(title: CharSequence?) {
            this.title = title
        }

        fun actions(body: Actions.() -> Unit) {
            buildActions = body
        }
    }
}

