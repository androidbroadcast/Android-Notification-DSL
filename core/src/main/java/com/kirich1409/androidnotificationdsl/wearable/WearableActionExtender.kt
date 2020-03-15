@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.wearable

import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.NotificationAction
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationActionMarker
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationWearableActionExtenderMarker

@NotificationWearableActionExtenderMarker
inline class WearableActionExtender(private val wearableExtender: NotificationCompat.Action.WearableExtender) {

    var availableOffline: Boolean
        get() = wearableExtender.isAvailableOffline
        set(value) {
            wearableExtender.isAvailableOffline = value
        }

    var hintLaunchesActivity: Boolean
        get() = wearableExtender.hintLaunchesActivity
        set(value) {
            wearableExtender.hintLaunchesActivity = value
        }

    var hintDisplayActionInline: Boolean
        get() = wearableExtender.hintDisplayActionInline
        set(value) {
            wearableExtender.hintDisplayActionInline = value
        }
}

inline fun NotificationAction.wearable(body: @NotificationActionMarker WearableActionExtender.() -> Unit) {
    val wearableExtender = NotificationCompat.Action.WearableExtender()
    WearableActionExtender(wearableExtender).body()
    extend(wearableExtender)
}
