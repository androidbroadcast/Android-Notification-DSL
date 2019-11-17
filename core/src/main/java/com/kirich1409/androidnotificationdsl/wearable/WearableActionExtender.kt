@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.wearable

import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationWearableActionExtenderMarker

@NotificationWearableActionExtenderMarker
inline class WearableActionExtender(private val extender: NotificationCompat.Action.WearableExtender) {

    var availableOffline: Boolean
        get() = extender.isAvailableOffline
        set(value) {
            extender.isAvailableOffline = value
        }

    var hintLaunchesActivity: Boolean
        get() = extender.hintLaunchesActivity
        set(value) {
            extender.hintLaunchesActivity = value
        }

    var hintDisplayActionInline: Boolean
        get() = extender.hintDisplayActionInline
        set(value) {
            extender.hintDisplayActionInline = value
        }
}
