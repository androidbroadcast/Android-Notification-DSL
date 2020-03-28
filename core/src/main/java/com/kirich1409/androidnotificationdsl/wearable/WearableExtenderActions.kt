@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.wearable

import android.app.PendingIntent
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.kirich1409.androidnotificationdsl.action.Action

@NotificationWearableExtenderActionsMarker
class WearableExtenderActions @PublishedApi internal constructor(
    @PublishedApi internal val wearableExtender: NotificationCompat.WearableExtender
) {

    fun addAction(action: NotificationCompat.Action) {
        wearableExtender.addAction(action)
    }

    inline fun action(
        title: CharSequence,
        intent: PendingIntent,
        icon: IconCompat? = null,
        body: @NotificationWearableExtenderActionsMarker Action.() -> Unit
    ) {
        val action = NotificationCompat.Action.Builder(icon, title, intent)
            .also { Action(it).body() }
            .build()
        wearableExtender.addAction(action)
    }

    inline fun action(
        title: CharSequence,
        intent: PendingIntent,
        @DrawableRes icon: Int,
        body: @NotificationWearableExtenderActionsMarker Action.() -> Unit
    ) {
        val action = NotificationCompat.Action.Builder(icon, title, intent)
            .also { Action(it).body() }
            .build()
        wearableExtender.addAction(action)
    }

    operator fun plus(action: NotificationCompat.Action) {
        addAction(action)
    }
}