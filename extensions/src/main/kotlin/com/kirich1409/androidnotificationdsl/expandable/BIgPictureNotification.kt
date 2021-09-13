@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.expandable

import android.app.Notification
import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.NotificationDsl
import com.kirich1409.androidnotificationdsl.action.Actions
import com.kirich1409.androidnotificationdsl.internal.requiredNotificationProperty
import com.kirich1409.androidnotificationdsl.notification
import com.kirich1409.androidnotificationdsl.style.bigpicture.bigPictureStyle
import com.kirich1409.androidnotificationdsl.utils.internal.NOTHING

/**
 * Create new notification with displaying big picture in expanded state
 */
@NotificationDsl
fun bigPictureNotification(
    context: Context,
    channelId: String,
    @DrawableRes smallIcon: Int,
    body: BigPictureNotificationBuilder.() -> Unit
): Notification {
    val builder = BigPictureNotificationBuilder().apply(body)
    return notification(context, channelId, smallIcon) {
        contentTitle = builder.title
        contentText = builder.text
        largeIcon = builder.largeIcon
        bigPictureStyle {
            picture = builder.expanded.bigPicture
            largeIcon = builder.expanded.largeIcon

            contentTitle = builder.expanded._title as CharSequence?
            summaryText = builder.expanded._text as CharSequence?

            builder.expanded.buildActions?.let(this@notification::actions)
        }
        builder.extender?.invoke(this)
    }
}

@BigPictureNotificationBuilderMarker
@NotificationDsl
class BigPictureNotificationBuilder internal constructor() {

    /**
     * Notification title
     */
    @NotificationDsl
    var title: CharSequence? = null

    /**
     * Notification text
     *
     * Will be showed on Android 5.0 and newer
     */
    @NotificationDsl
    var text: CharSequence? = null

    /**
     * Notification large icon
     */
    @NotificationDsl
    var largeIcon: Bitmap? = null

    internal val expanded = Expanded()

    internal var extender: (NotificationBuilder.() -> Unit)? = null

    @NotificationDsl
    fun expanded(body: @BigPictureNotificationBuilderMarker Expanded.() -> Unit) {
        expanded.body()
    }

    @NotificationDsl
    fun extend(body: NotificationBuilder.() -> Unit) {
        extender = body
    }

    @NotificationDsl
    class Expanded internal constructor() {

        /**
         * Picture displayed in notification expanded state
         */
        @NotificationDsl
        var bigPicture: Bitmap by requiredNotificationProperty("bigPicture")

        @RestrictTo(RestrictTo.Scope.LIBRARY)
        internal var _title: Any? = NOTHING

        @RestrictTo(RestrictTo.Scope.LIBRARY)
        internal var _text: Any? = NOTHING

        internal var buildActions: (Actions.() -> Unit)? = null

        @NotificationDsl
        var largeIcon: Bitmap? = null

        /**
         * Replace notification title in expanded state
         *
         * @see BigPictureNotificationBuilder.title
         */
        @NotificationDsl
        var title: CharSequence?
            get() = _title as? CharSequence?
            set(value) {
                _title = value
            }

        /**
         * Replace notification text in expanded state
         *
         * @see BigPictureNotificationBuilder.text
         */
        @NotificationDsl
        var text: CharSequence?
            get() = _text as? CharSequence?
            set(value) {
                _text = value
            }

        /**
         * Notification's actions
         *
         * Actions will not be showed on Android before 4.1
         */
        @NotificationDsl
        fun actions(body: Actions.() -> Unit) {
            buildActions = body
        }
    }
}

