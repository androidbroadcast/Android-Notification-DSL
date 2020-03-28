@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.wearable

import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.action.Action
import com.kirich1409.androidnotificationdsl.action.NotificationActionMarker

@NotificationWearableActionExtenderMarker
class WearableActionExtender @PublishedApi internal constructor(
    private val wearableExtender: NotificationCompat.Action.WearableExtender
) {

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

inline fun Action.wearable(body: @NotificationActionMarker WearableActionExtender.() -> Unit) {
    val wearableExtender = NotificationCompat.Action.WearableExtender()
    WearableActionExtender(wearableExtender).body()
    extend(wearableExtender)
}
