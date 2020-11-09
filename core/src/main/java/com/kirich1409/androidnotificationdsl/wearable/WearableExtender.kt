@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.wearable

import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.extend

@NotificationWearableExtenderMarker
@Suppress("UndocumentedPublicClass")
class WearableExtender @PublishedApi internal constructor(
    @PublishedApi internal val wearableExtender: NotificationCompat.WearableExtender
) {

    /**
     * The bridge tag of the notification.
     */
    inline var bridgeTag: String?
        get() = wearableExtender.bridgeTag
        set(value) {
            wearableExtender.bridgeTag = value
        }

    /**
     * Get the wearable actions present on this notification.
     */
    inline val actions: List<NotificationCompat.Action>
        get() = wearableExtender.actions

    /**
     * Get the index of the notification action, if any, that was specified as the primary action.
     *
     * If wearable specific actions were added to the main notification, this index will
     * apply to that list, otherwise it will apply to the regular actions list.
     *
     * @return the action index or [UNSET_ACTION_INDEX][NotificationCompat.WearableExtender.UNSET_ACTION_INDEX]
     * if no action was selected.
     */
    inline var contentActions: Int
        get() = wearableExtender.contentAction
        set(value) {
            wearableExtender.contentAction = value
        }

    /**
     * Get whether the content intent is available when the wearable device is not connected to a companion device.
     * The user can still trigger this intent when the wearable device is offline,
     * but a visual hint will indicate that the content intent may not be available.
     *
     * Defaults to true.
     */
    inline var contentIntentAvailableOffline: Boolean
        get() = wearableExtender.contentIntentAvailableOffline
        set(value) {
            wearableExtender.contentIntentAvailableOffline = value
        }

    /**
     * The dismissal id of the notification.
     */
    inline var dismissalId: String?
        get() = wearableExtender.dismissalId
        set(value) {
            wearableExtender.dismissalId = value
        }

    /**
     * A hint that this notification's content intent will launch an [Activity][android.app.Activity] directly,
     * telling the platform that it can generate the appropriate transitions
     *
     * The property `true` if the content intent will launch an activity and transitions should be generated, `
     * false` otherwise. The default value is `false` if this was never set.
     */
    inline var hintContentIntentLaunchesActivity: Boolean
        get() = wearableExtender.hintContentIntentLaunchesActivity
        set(value) {
            wearableExtender.hintContentIntentLaunchesActivity = value
        }

    /**
     * Whether the scrolling position for the contents of this notification should start
     * at the bottom of the contents instead of the top when the contents are too long to
     * display within the screen.
     *
     * Default is false (start scroll at the top).
     */
    inline var startScrollBottom: Boolean
        get() = wearableExtender.startScrollBottom
        set(value) {
            wearableExtender.startScrollBottom = value
        }

    /**
     * Add a wearable [Action][NotificationCompat.Action]s to this notification.
     */
    inline fun actions(body: @NotificationWearableExtenderMarker WearableExtenderActions.() -> Unit) {
        WearableExtenderActions(wearableExtender).body()
    }
}

/**
 * Create a [NotificationCompat.WearableExtender] and extend notification with it
 */
inline fun NotificationBuilder.wearable(body: @NotificationMarker WearableExtender.() -> Unit) {
    extend(wearableExtender(body))
}

/**
 * Create a [NotificationCompat.WearableExtender]
 */
inline fun wearableExtender(
    body: @NotificationMarker WearableExtender.() -> Unit
): NotificationCompat.WearableExtender {
    return NotificationCompat.WearableExtender().also { extender -> WearableExtender(extender).body() }
}
