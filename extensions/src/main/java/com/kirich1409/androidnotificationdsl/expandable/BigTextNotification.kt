@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.kirich1409.androidnotificationdsl.expandable

import android.app.Notification
import android.content.Context
import androidx.annotation.DrawableRes
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.action.ActionsBuilder
import com.kirich1409.androidnotificationdsl.expandable.annotations.BigTextNotificationBuilderMarker
import com.kirich1409.androidnotificationdsl.notification
import com.kirich1409.androidnotificationdsl.style.bigtext.bigTextStyle
import com.kirich1409.androidnotificationdsl.utils.internal.NOTHING
import com.kirich1409.androidnotificationdsl.utils.internal.requiredNotificationProperty

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
        builder.title?.let(::contentTitle)
        contentText(builder.text)
        bigTextStyle {
            text(builder.text)

            val title = builder.expanded.title
            if (title is CharSequence?) {
                contentTitle(title)
            }

            val text = builder.expanded.text
            if (text is CharSequence) {
                summaryText(text)
            }

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

        internal var title: Any? = NOTHING

        internal var text: Any? = NOTHING

        internal var buildActions: (ActionsBuilder.() -> Unit)? = null

        /**
         * Replace notification title in expanded state
         *
         * @see BigTextNotificationBuilder.title
         */
        fun overrideTitle(title: CharSequence?) {
            this.title = title
        }

        /**
         * Replace notification text in expanded state
         *
         * @see BigTextNotificationBuilder.text
         */
        fun overrideText(text: CharSequence?) {
            this.text = text
        }

        /**
         * Notification's actions
         *
         * Actions will not be showed on Android before 4.1
         */
        fun actions(body: ActionsBuilder.() -> Unit) {
            buildActions = body
        }
    }
}

