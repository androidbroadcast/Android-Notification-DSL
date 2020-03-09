@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl

import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationActionMarker
import com.kirich1409.androidnotificationdsl.wearable.WearableActionExtender

@NotificationActionMarker
inline class NotificationAction(private val action: NotificationCompat.Action.Builder) {

    fun addExtras(extras: Bundle) {
        action.addExtras(extras)
    }

    fun addRemoteInput(remoteInput: RemoteInput) {
        action.addRemoteInput(remoteInput)
    }

    fun allowGeneratedReplies(allowGeneratedReplies: Boolean) {
        action.setAllowGeneratedReplies(allowGeneratedReplies)
    }

    fun contextual(contextual: Boolean) {
        action.setContextual(contextual)
    }

    fun extend(extender: NotificationCompat.Action.Extender) {
        action.extend(extender)
    }

    fun semanticAction(@NotificationCompat.Action.SemanticAction semanticAction: Int) {
        action.setSemanticAction(semanticAction)
    }

    fun showsUserInterface(showsUserInterface: Boolean) {
        action.setShowsUserInterface(showsUserInterface)
    }

    inline fun wearable(body: @NotificationActionMarker WearableActionExtender.() -> Unit) {
        val wearableExtender = NotificationCompat.Action.WearableExtender()
        WearableActionExtender(wearableExtender).body()
        extend(wearableExtender)
    }
}