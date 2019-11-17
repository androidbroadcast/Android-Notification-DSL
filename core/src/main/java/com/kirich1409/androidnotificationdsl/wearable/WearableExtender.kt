@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.wearable

import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationWearableExtenderMarker

@NotificationWearableExtenderMarker
inline class WearableExtender(private val extender: NotificationCompat.WearableExtender) {

    var bridgeTag: String?
        get() = extender.bridgeTag
        set(value) {
            extender.bridgeTag = value
        }

    val actions: List<NotificationCompat.Action>
        get() = extender.actions

    var contentActions: Int
        get() = extender.contentAction
        set(value) {
            extender.contentAction = value
        }

    var contentIntentAvailableOffline: Boolean
        get() = extender.contentIntentAvailableOffline
        set(value) {
            extender.contentIntentAvailableOffline = value
        }

    var dismissalId: String?
        get() = extender.dismissalId
        set(value) {
            extender.dismissalId = value
        }

    var hintContentIntentLaunchesActivity: Boolean
        get() = extender.hintContentIntentLaunchesActivity
        set(value) {
            extender.hintContentIntentLaunchesActivity = value
        }

    var startScrollBottom: Boolean
        get() = extender.startScrollBottom
        set(value) {
            extender.startScrollBottom = value
        }

    fun actions(body: WearableExtenderActions.() -> Unit) {
        WearableExtenderActions(extender).body()
    }
}
