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
     */
    fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        icon: IconCompat? = null,
        visible: Boolean = false
    ) {
        if (visible || isInvisibleActionsSupported()) {
            val action = NotificationCompat.Action.Builder(icon, title, intent).build()
            addAction(action, visible)
        }
    }

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     */
    fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        @DrawableRes icon: Int,
        visible: Boolean = false
    ) {
        if (visible || isInvisibleActionsSupported()) {
            val action = NotificationCompat.Action.Builder(icon, title, intent).build()
            addAction(action, visible)
        }
    }

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     */
    inline fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        icon: IconCompat? = null,
        visible: Boolean = false,
        body: @NotificationActionsMarker Action.() -> Unit
    ) {
        if (visible || isInvisibleActionsSupported()) {
            val actionBuilder = NotificationCompat.Action.Builder(icon, title, intent)
            Action(actionBuilder).body()
            addAction(actionBuilder.build(), visible)
        }
    }

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     */
    inline fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        @DrawableRes icon: Int,
        visible: Boolean = false,
        body: @NotificationActionsMarker Action.() -> Unit
    ) {
        if (visible || isInvisibleActionsSupported()) {
            val actionBuilder = NotificationCompat.Action.Builder(icon, title, intent)
            Action(actionBuilder).body()
            addAction(actionBuilder.build(), visible)
        }
    }

    /**
     * Add an action to this notification. Actions are typically displayed by
     * the system as a button adjacent to the notification content.
     *
     * On pre-Lollpop devices invisible actions will be ignored
     *
     * @param visible is actions are never displayed by the system,
     * but can be retrieved and used by other application listening to system notifications
     */
    fun addAction(action: NotificationCompat.Action, visible: Boolean = false) {
        if (visible) {
            builder.addAction(action)
        } else if (isInvisibleActionsSupported()) {
            builder.addInvisibleAction(action)
        }
    }
}
