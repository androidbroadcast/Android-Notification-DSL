@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.action

import android.app.PendingIntent
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.kirich1409.androidnotificationdsl.Container
import com.kirich1409.androidnotificationdsl.internal.isInvisibleActionsSupported

/**
 * Builder of notification's actions
 */
@JvmInline
@NotificationActionsMarker
value class Actions(
    private val builder: NotificationCompat.Builder
) : Container<NotificationCompat.Action> {

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     *
     * On pre-Lollipop devices invisible actions will be ignored
     */
    fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        icon: IconCompat? = null,
        visible: Boolean = true
    ) {
        action(visible) {
            NotificationCompat.Action.Builder(icon, title, intent)
        }
    }

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     *
     * On pre-Lollipop devices invisible actions will be ignored
     */
    fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        @DrawableRes icon: Int,
        visible: Boolean = true
    ) {
        action(visible) {
            NotificationCompat.Action.Builder(icon, title, intent)
        }
    }

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     *
     * On pre-Lollipop devices invisible actions will be ignored
     */
    inline fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        icon: IconCompat? = null,
        visible: Boolean = true,
        crossinline body: @NotificationActionsMarker ActionBuilder.() -> Unit
    ) {
        action(visible) {
            NotificationCompat.Action.Builder(icon, title, intent)
                .apply { ActionBuilder(this).body() }
        }
    }

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     *
     * On pre-Lollipop devices invisible actions will be ignored
     */
    inline fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        @DrawableRes icon: Int,
        visible: Boolean = true,
        crossinline body: @NotificationActionsMarker ActionBuilder.() -> Unit
    ) {
        action(visible) {
            NotificationCompat.Action.Builder(icon, title, intent)
                .apply { ActionBuilder(this).body() }
        }
    }

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     *
     * On pre-Lollipop devices invisible actions will be ignored
     *
     * @param visible is actions are never displayed by the system, but can be retrieved
     *                and used by other application listening to system notifications
     */
    fun action(action: NotificationCompat.Action, visible: Boolean = true) {
        if (visible) {
            builder.addAction(action)
        } else if (isInvisibleActionsSupported()) {
            builder.addInvisibleAction(action)
        }
    }

    @PublishedApi
    internal fun action(visible: Boolean = true, action: () -> NotificationCompat.Action.Builder) {
        if (visible) {
            builder.addAction(action().build())
        } else if (isInvisibleActionsSupported()) {
            builder.addInvisibleAction(action().build())
        }
    }

    override fun plusAssign(item: NotificationCompat.Action) = action(item, visible = true)
}
