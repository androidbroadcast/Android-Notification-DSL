@file:Suppress("unused", "MemberVisibilityCanBePrivate", "NOTHING_TO_INLINE")

package com.kirich1409.androidnotificationdsl

import android.app.PendingIntent
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationActionsMarker

@NotificationActionsMarker
class Actions(private val builder: NotificationCompat.Builder) {

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
        body: @NotificationActionsMarker NotificationAction.() -> Unit
    ) {
        val actionBuilder = NotificationCompat.Action.Builder(icon, title, intent)
        NotificationAction(actionBuilder).body()
        addAction(actionBuilder.build(), invisible)
    }

    inline fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        @DrawableRes icon: Int,
        invisible: Boolean = false,
        body: @NotificationActionsMarker NotificationAction.() -> Unit
    ) {
        val actionBuilder = NotificationCompat.Action.Builder(icon, title, intent)
        NotificationAction(actionBuilder).body()
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
