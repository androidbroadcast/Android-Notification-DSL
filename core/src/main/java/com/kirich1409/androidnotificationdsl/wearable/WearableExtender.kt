@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.wearable

import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationWearableExtenderMarker

@NotificationWearableExtenderMarker
inline class WearableExtender(@PublishedApi internal val wearableExtender: NotificationCompat.WearableExtender) {

    var bridgeTag: String?
        get() = wearableExtender.bridgeTag
        set(value) {
            wearableExtender.bridgeTag = value
        }

    val actions: List<NotificationCompat.Action>
        get() = wearableExtender.actions

    var contentActions: Int
        get() = wearableExtender.contentAction
        set(value) {
            wearableExtender.contentAction = value
        }

    var contentIntentAvailableOffline: Boolean
        get() = wearableExtender.contentIntentAvailableOffline
        set(value) {
            wearableExtender.contentIntentAvailableOffline = value
        }

    var dismissalId: String?
        get() = wearableExtender.dismissalId
        set(value) {
            wearableExtender.dismissalId = value
        }

    var hintContentIntentLaunchesActivity: Boolean
        get() = wearableExtender.hintContentIntentLaunchesActivity
        set(value) {
            wearableExtender.hintContentIntentLaunchesActivity = value
        }

    var startScrollBottom: Boolean
        get() = wearableExtender.startScrollBottom
        set(value) {
            wearableExtender.startScrollBottom = value
        }

    inline fun actions(body: WearableExtenderActions.() -> Unit) {
        WearableExtenderActions(wearableExtender).body()
    }
}
