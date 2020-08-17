@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.action

import android.app.PendingIntent
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.kirich1409.androidnotificationdsl.action.annotations.NotificationActionsMarker
import com.kirich1409.androidnotificationdsl.internal.isInvisibleActionsSupported

/**
 * Builder of notification's actions
 */
@NotificationActionsMarker
class Actions @PublishedApi internal constructor(private val builder: NotificationCompat.Builder) {

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     *
     * On pre-Lollpop devices invisible actions will be ignored
     */
    fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        icon: IconCompat? = null,
        visible: Boolean = true
    ) {
        addAction(visible) {
            NotificationCompat.Action.Builder(icon, title, intent)
        }
    }

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     *
     * On pre-Lollpop devices invisible actions will be ignored
     */
    fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        @DrawableRes icon: Int,
        visible: Boolean = true
    ) {
        addAction(visible) {
            NotificationCompat.Action.Builder(icon, title, intent)
        }
    }

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     *
     * On pre-Lollpop devices invisible actions will be ignored
     */
    inline fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        icon: IconCompat? = null,
        visible: Boolean = true,
        crossinline body: @NotificationActionsMarker Action.() -> Unit
    ) {
        addAction(visible) {
            NotificationCompat.Action.Builder(icon, title, intent).apply { Action(this).body() }
        }
    }

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     *
     * On pre-Lollpop devices invisible actions will be ignored
     */
    inline fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        @DrawableRes icon: Int,
        visible: Boolean = true,
        crossinline body: @NotificationActionsMarker Action.() -> Unit
    ) {
        addAction(visible) {
            NotificationCompat.Action.Builder(icon, title, intent).apply { Action(this).body() }
        }
    }

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     *
     * On pre-Lollpop devices invisible actions will be ignored
     *
     * @param visible is actions are never displayed by the system, but can be retrieved
     *                and used by other application listening to system notifications
     */
    fun addAction(action: NotificationCompat.Action, visible: Boolean = true) {
        if (visible) {
            builder.addAction(action)
        } else if (isInvisibleActionsSupported()) {
            builder.addInvisibleAction(action)
        }
    }

    @PublishedApi
    internal fun addAction(visible: Boolean = true, action: () -> NotificationCompat.Action.Builder) {
        if (visible) {
            builder.addAction(action().build())
        } else if (isInvisibleActionsSupported()) {
            builder.addInvisibleAction(action().build())
        }
    }
}
