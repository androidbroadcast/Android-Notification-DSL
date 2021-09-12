@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.kirich1409.androidnotificationdsl.expandable

import android.app.Notification
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.action.Actions
import com.kirich1409.androidnotificationdsl.internal.requiredNotificationProperty
import com.kirich1409.androidnotificationdsl.notification
import com.kirich1409.androidnotificationdsl.style.bigtext.bigTextStyle
import com.kirich1409.androidnotificationdsl.utils.internal.NOTHING

/**
 * Create new notification with displaying big text in expanded state
 */
fun bigTextNotification(
    context: Context,
    channelId: String,
    @DrawableRes smallIcon: Int,
    body: BigTextNotificationBuilder.() -> Unit
): Notification {
    val builder = BigTextNotificationBuilder().apply(body)
    return notification(context, channelId, smallIcon) {
        contentTitle = builder.title
        contentText = builder.text
        bigTextStyle {
            text = builder.text
            contentTitle = builder.expanded.title
            summaryText = builder.expanded.text
            builder.expanded.buildActions?.let(this@notification::actions)
        }
        builder.extender?.invoke(this)
    }
}

@BigTextNotificationBuilderMarker
class BigTextNotificationBuilder internal constructor() {

    /**
     * Notification title
     */
    var title: CharSequence? = null

    /**
     * Picture displayed in notification expanded state
     */
    var text: CharSequence by requiredNotificationProperty("text")

    internal val expanded = Expanded()

    internal var extender: (NotificationBuilder.() -> Unit)? = null

    fun expanded(body: @BigTextNotificationBuilderMarker Expanded.() -> Unit) {
        expanded.body()
    }

    fun extend(body: NotificationBuilder.() -> Unit) {
        extender = body
    }

    class Expanded internal constructor() {

        @RestrictTo(RestrictTo.Scope.LIBRARY)
        internal var _title: Any? = NOTHING

        @RestrictTo(RestrictTo.Scope.LIBRARY)
        internal var _text: Any? = NOTHING

        internal var buildActions: (Actions.() -> Unit)? = null

        /**
         * Replace notification title in expanded state
         *
         * @see BigTextNotificationBuilder.title
         */
        var title: CharSequence?
            get() = _title as? CharSequence?
            set(value) {
                _title = value
            }

        /**
         * Replace notification text in expanded state
         *
         * @see BigTextNotificationBuilder.text
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

