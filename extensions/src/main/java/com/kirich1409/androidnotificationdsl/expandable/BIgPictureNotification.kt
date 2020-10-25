@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.expandable

import android.app.Notification
import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.annotation.RestrictTo
import com.ironsource.aura.dslint.annotations.DSLMandatory
import com.ironsource.aura.dslint.annotations.DSLint
import com.kirich1409.androidnotificationdsl.NotificationBuilder
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
        contentTitle = builder.title
        contentText = builder.text
        largeIcon = builder.largeIcon
        bigPictureStyle {
            picture(builder.expanded.bigPicture)
            largeIcon(builder.expanded.largeIcon)

            val title = builder.expanded._title
            if (title is CharSequence?) {
                contentTitle(title)
            }

            val text = builder.expanded._text
            if (text is CharSequence?) {
                summaryText(text)
            }

            builder.expanded.buildActions?.let(this@notification::actions)
        }
        builder.extender?.invoke(this)
    }
}

@DSLint
@BigPictureNotificationBuilderMarker
class BigPictureNotificationBuilder internal constructor() {

    /**
     * Notification title
     */
    var title: CharSequence? = null

    /**
     * Notification text
     *
     * Will be showed on Android 5.0 and newer
     */
    var text: CharSequence? = null

    /**
     * Notification large icon
     */
    var largeIcon: Bitmap? = null

    internal val expanded = Expanded()

    internal var extender: (NotificationBuilder.() -> Unit)? = null

    fun expanded(body: @BigPictureNotificationBuilderMarker Expanded.() -> Unit) {
        expanded.body()
    }

    fun extend(body: NotificationBuilder.() -> Unit) {
        extender = body
    }

    @DSLint
    class Expanded internal constructor() {

        /**
         * Picture displayed in notification expanded state
         */
        @set:DSLMandatory
        var bigPicture: Bitmap by requiredNotificationProperty("bigPicture")

        @RestrictTo(RestrictTo.Scope.LIBRARY)
        internal var _title: Any? = NOTHING

        @RestrictTo(RestrictTo.Scope.LIBRARY)
        internal var _text: Any? = NOTHING

        internal var buildActions: (Actions.() -> Unit)? = null

        var largeIcon: Bitmap? = null

        /**
         * Replace notification title in expanded state
         *
         * @see BigPictureNotificationBuilder.title
         */
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
        fun actions(body: Actions.() -> Unit) {
            buildActions = body
        }
    }
}

