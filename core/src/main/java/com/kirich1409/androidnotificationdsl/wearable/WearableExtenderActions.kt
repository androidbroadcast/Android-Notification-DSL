@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.wearable

import android.app.PendingIntent
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.kirich1409.androidnotificationdsl.NotificationAction
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationWearableExtenderActionsMarker

@NotificationWearableExtenderActionsMarker
inline class WearableExtenderActions(private val extender: NotificationCompat.WearableExtender) {

    fun addAction(action: NotificationCompat.Action) {
        extender.addAction(action)
    }

    fun action(
        title: CharSequence,
        intent: PendingIntent,
        icon: IconCompat? = null,
        body: @NotificationWearableExtenderActionsMarker NotificationAction.() -> Unit
    ) {
        action(NotificationCompat.Action.Builder(icon, title, intent), body)
    }

    fun action(
        title: CharSequence,
        intent: PendingIntent,
        @DrawableRes icon: Int,
        body: @NotificationWearableExtenderActionsMarker NotificationAction.() -> Unit
    ) {
        action(NotificationCompat.Action.Builder(icon, title, intent), body)
    }

    private fun action(
        notificationActionBuilder: NotificationCompat.Action.Builder,
        body: @NotificationWearableExtenderActionsMarker NotificationAction.() -> Unit
    ) {
        val actionBuilder = NotificationAction(notificationActionBuilder)
        actionBuilder.body()
        extender.addAction(actionBuilder.build())
    }

    operator fun plus(action: NotificationCompat.Action) {
        addAction(action)
    }
}