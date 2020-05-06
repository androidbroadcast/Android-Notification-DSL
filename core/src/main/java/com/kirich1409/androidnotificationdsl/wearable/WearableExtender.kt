@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.wearable

import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.NotificationMarker

@NotificationWearableExtenderMarker
class WearableExtender @PublishedApi internal constructor(
    @PublishedApi internal val wearableExtender: NotificationCompat.WearableExtender
) {

    inline var bridgeTag: String?
        get() = wearableExtender.bridgeTag
        set(value) {
            wearableExtender.bridgeTag = value
        }

    inline val actions: List<NotificationCompat.Action>
        get() = wearableExtender.actions

    inline var contentActions: Int
        get() = wearableExtender.contentAction
        set(value) {
            wearableExtender.contentAction = value
        }

    inline var contentIntentAvailableOffline: Boolean
        get() = wearableExtender.contentIntentAvailableOffline
        set(value) {
            wearableExtender.contentIntentAvailableOffline = value
        }

    inline var dismissalId: String?
        get() = wearableExtender.dismissalId
        set(value) {
            wearableExtender.dismissalId = value
        }

    inline var hintContentIntentLaunchesActivity: Boolean
        get() = wearableExtender.hintContentIntentLaunchesActivity
        set(value) {
            wearableExtender.hintContentIntentLaunchesActivity = value
        }

    inline var startScrollBottom: Boolean
        get() = wearableExtender.startScrollBottom
        set(value) {
            wearableExtender.startScrollBottom = value
        }

    inline fun actions(body: WearableExtenderActions.() -> Unit) {
        WearableExtenderActions(wearableExtender).body()
    }
}

inline fun Notification.wearable(body: @NotificationMarker WearableExtender.() -> Unit) {
    val wearableExtender: NotificationCompat.WearableExtender = NotificationCompat.WearableExtender()
    WearableExtender(wearableExtender).body()
    extend(wearableExtender)
}
