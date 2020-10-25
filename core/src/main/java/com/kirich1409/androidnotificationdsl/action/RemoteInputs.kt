@file:Suppress("MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.action

import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.kirich1409.androidnotificationdsl.Container

inline class RemoteInputs(private val action: NotificationCompat.Action.Builder) : Container<RemoteInput> {

    /**
     * @see remoteInput
     */
    override fun plusAssign(item: RemoteInput) {
        remoteInput(item)
    }

    /**
     * Add an input to be collected from the user when this action is sent.
     * Response values can be retrieved from the fired intent by using the
     * [RemoteInput.getResultsFromIntent] function.
     *
     * @param remoteInput a [RemoteInput] to add to the action
     */
    fun remoteInput(remoteInput: RemoteInput) {
        action.addRemoteInput(remoteInput)
    }
}
