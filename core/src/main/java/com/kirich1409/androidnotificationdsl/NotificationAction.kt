@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl

import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationActionMarker
import com.kirich1409.androidnotificationdsl.wearable.WearableActionExtender

@NotificationActionMarker
inline class NotificationAction(private val builder: NotificationCompat.Action.Builder) {

    fun addExtras(extras: Bundle) {
        builder.addExtras(extras)
    }

    fun addRemoteInput(remoteInput: RemoteInput) {
        builder.addRemoteInput(remoteInput)
    }

    fun allowGeneratedReplies(allowGeneratedReplies: Boolean) {
        builder.setAllowGeneratedReplies(allowGeneratedReplies)
    }

    fun contextual(contextual: Boolean) {
        builder.setContextual(contextual)
    }

    fun extend(extender: NotificationCompat.Action.Extender) {
        builder.extend(extender)
    }

    fun semanticAction(@NotificationCompat.Action.SemanticAction semanticAction: Int) {
        builder.setSemanticAction(semanticAction)
    }

    fun showsUserInterface(showsUserInterface: Boolean) {
        builder.setShowsUserInterface(showsUserInterface)
    }

    fun wearable(body: @NotificationActionMarker WearableActionExtender.() -> Unit) {
        val wearableExtender = NotificationCompat.Action.WearableExtender()
        WearableActionExtender(wearableExtender).body()
        extend(wearableExtender)
    }
}