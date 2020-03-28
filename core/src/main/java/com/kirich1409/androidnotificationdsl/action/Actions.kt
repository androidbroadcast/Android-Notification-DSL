@file:Suppress("unused", "MemberVisibilityCanBePrivate", "NOTHING_TO_INLINE")

package com.kirich1409.androidnotificationdsl.action

import android.app.PendingIntent
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat

@NotificationActionsMarker
class Actions @PublishedApi internal constructor(private val builder: NotificationCompat.Builder) {

    fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        icon: IconCompat? = null,
        invisible: Boolean = false
    ) {
        addAction(NotificationCompat.Action.Builder(icon, title, intent).build(), invisible)
    }

    inline fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        @DrawableRes icon: Int,
        invisible: Boolean = false
    ) {
        addAction(NotificationCompat.Action.Builder(icon, title, intent).build(), invisible)
    }

    inline fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        icon: IconCompat? = null,
        invisible: Boolean = false,
        body: @NotificationActionsMarker Action.() -> Unit
    ) {
        val actionBuilder = NotificationCompat.Action.Builder(icon, title, intent)
        Action(actionBuilder).body()
        addAction(actionBuilder.build(), invisible)
    }

    inline fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        @DrawableRes icon: Int,
        invisible: Boolean = false,
        body: @NotificationActionsMarker Action.() -> Unit
    ) {
        val actionBuilder = NotificationCompat.Action.Builder(icon, title, intent)
        Action(actionBuilder).body()
        addAction(actionBuilder.build(), invisible)
    }

    fun addAction(action: NotificationCompat.Action, invisible: Boolean = false) {
        if (invisible) {
            builder.addInvisibleAction(action)
        } else {
            builder.addAction(action)
        }
    }
}
