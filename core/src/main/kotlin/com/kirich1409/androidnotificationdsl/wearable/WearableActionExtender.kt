@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.wearable

import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.action.ActionBuilder
import com.kirich1409.androidnotificationdsl.action.NotificationActionMarker
import com.kirich1409.androidnotificationdsl.action.extend

@NotificationWearableActionExtenderMarker
@Suppress("UndocumentedPublicClass")
class WearableActionExtender @PublishedApi internal constructor(
    @PublishedApi internal val wearableExtender: NotificationCompat.Action.WearableExtender
) {

    /**
     * Whether this action is available when the wearable device is not connected to a companion device.
     * The user can still trigger this action when the wearable device is offline,
     * but a visual hint will indicate that the action may not be available.
     *
     * Defaults to true.
     */
    inline var availableOffline: Boolean
        get() = wearableExtender.isAvailableOffline
        set(value) {
            wearableExtender.isAvailableOffline = value
        }

    /**
     * Get a hint that this Action will launch an [Activity][android.app.Activity] directly, telling the platform
     * that it can generate the appropriate transitions
     *
     * Will be `true` if the content intent will launch an activity and transitions should be generated,
     * `false` otherwise. The default value is `false` if this was never set.
     */
    inline var hintLaunchesActivity: Boolean
        get() = wearableExtender.hintLaunchesActivity
        set(value) {
            wearableExtender.hintLaunchesActivity = value
        }

    /**
     * Hint that this Action should be displayed inline - i.e. it should have a visual representation directly on the
     * notification surface in addition to the expanded Notification
     *
     * Will be `true` if the Action should be displayed inline, `false` otherwise.
     *
     * The default value is `false` if this was never set.
     */
    inline var hintDisplayActionInline: Boolean
        get() = wearableExtender.hintDisplayActionInline
        set(value) {
            wearableExtender.hintDisplayActionInline = value
        }
}

/**
 * Apply an [WearableExtender][NotificationCompat.Action.WearableExtender] to this action builder.
 * Extenders may be used to add metadata or change options on this builder.
 */
inline fun ActionBuilder.wearable(body: @NotificationActionMarker WearableActionExtender.() -> Unit) {
    extend(wearableExtender(body))
}

/**
 * Create a [NotificationCompat.Action.WearableExtender].
 */
inline fun ActionBuilder.wearableExtender(
    body: @NotificationActionMarker WearableActionExtender.() -> Unit
): NotificationCompat.Action.WearableExtender {
    val wearableExtender = NotificationCompat.Action.WearableExtender()
    WearableActionExtender(wearableExtender).body()
    return wearableExtender
}
