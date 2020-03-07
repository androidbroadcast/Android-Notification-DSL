@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl

import android.app.PendingIntent
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationActionsMarker

@NotificationActionsMarker
inline class Actions(private val builder: NotificationCompat.Builder) {

    fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        icon: IconCompat? = null,
        invisible: Boolean = false
    ) {
        val actionBuilder = NotificationCompat.Action.Builder(icon, title, intent)
        action(actionBuilder, invisible)
    }

    fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        @DrawableRes icon: Int,
        invisible: Boolean = false
    ) {
        val actionBuilder = NotificationCompat.Action.Builder(icon, title, intent)
        action(actionBuilder, invisible)
    }

    fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        icon: IconCompat? = null,
        invisible: Boolean = false,
        body: @NotificationActionsMarker NotificationAction.() -> Unit
    ) {
        val actionBuilder = NotificationCompat.Action.Builder(icon, title, intent)
        action(actionBuilder, invisible, body)
    }

    fun action(
        title: CharSequence?,
        intent: PendingIntent?,
        @DrawableRes icon: Int,
        invisible: Boolean = false,
        body: @NotificationActionsMarker NotificationAction.() -> Unit
    ) {
        val actionBuilder = NotificationCompat.Action.Builder(icon, title, intent)
        action(actionBuilder, invisible, body)
    }

    private fun action(actionBuilder: NotificationCompat.Action.Builder, invisible: Boolean) {
        val action = actionBuilder.build()
        addAction(action, invisible)
    }

    private fun action(
        actionBuilder: NotificationCompat.Action.Builder,
        invisible: Boolean,
        body: @NotificationActionsMarker NotificationAction.() -> Unit
    ) {
        NotificationAction(actionBuilder).body()
        val action = actionBuilder.build()
        addAction(action, invisible)
    }

    fun addAction(action: NotificationCompat.Action, invisible: Boolean = false) {
        if (invisible) {
            builder.addInvisibleAction(action)
        } else {
            builder.addAction(action)
        }
    }
}
