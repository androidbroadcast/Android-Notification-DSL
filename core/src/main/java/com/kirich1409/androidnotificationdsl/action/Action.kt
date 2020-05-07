@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.action

import android.app.PendingIntent
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.action.annotations.NotificationActionMarker
import com.kirich1409.androidnotificationdsl.remoteinput.RemoteInput
import androidx.core.app.RemoteInput as AndroidRemoteInput

/**
 * Builder of notification's action
 */
@NotificationActionMarker
class Action @PublishedApi internal constructor(private val action: NotificationCompat.Action.Builder) {

    /**
     * Merge additional metadata into this builder.
     */
    fun addExtras(extras: Bundle) {
        action.addExtras(extras)
    }

    /**
     * Add an input to be collected from the user when this action is sent.
     * Response values can be retrieved from the fired intent by using the
     * [AndroidRemoteInput.getResultsFromIntent] function.
     *
     * @param remoteInput a [RemoteInput] to add to the action
     */
    fun addRemoteInput(remoteInput: AndroidRemoteInput) {
        action.addRemoteInput(remoteInput)
    }

    /**
     * Add an input to be collected from the user when this action is sent.
     * Response values can be retrieved from the fired intent by using the
     * [AndroidRemoteInput.getResultsFromIntent] function.
     */
    fun addRemoteInput(resultKey: String, body: RemoteInput.() -> Unit) {
        val remoteInput = AndroidRemoteInput.Builder(resultKey).also { RemoteInput(it).body() }.build()
        action.addRemoteInput(remoteInput)
    }

    /**
     * Set whether the platform should automatically generate possible replies to add to
     * [AndroidRemoteInput.getChoices]. If the [Action] doesn't have a
     * [RemoteInput], this has no effect.
     *
     * @param allowGeneratedReplies `true` to allow generated replies, `false` otherwise
     *
     * The default value is {@code true}
     */
    fun allowGeneratedReplies(allowGeneratedReplies: Boolean) {
        action.setAllowGeneratedReplies(allowGeneratedReplies)
    }

    /**
     * Sets whether this [Action] is a contextual action, i.e. whether the action is
     * dependent on the notification message body. An example of a contextual action could
     * be an action opening a map application with an address shown in the notification.
     */
    fun contextual(contextual: Boolean) {
        action.setContextual(contextual)
    }

    /**
     * Apply an extender to this action builder.
     * Extenders may be used to add metadata or change options on this builder.
     */
    fun extend(extender: NotificationCompat.Action.Extender) {
        action.extend(extender)
    }

    /**
     * Sets the [SemanticAction][NotificationCompat.Action.SemanticAction] for this [Action].
     * A [SemanticAction][NotificationCompat.Action.SemanticAction] denotes what an {@link Action}'s
     * [PendingIntent] will do (eg. reply, mark as read, delete, etc).
     *
     * @param semanticAction a [SemanticAction][@NotificationCompat.Action.SemanticAction] defined within
     * [Action] with `SEMANTIC_ACTION_` prefixes
     */
    fun semanticAction(@NotificationCompat.Action.SemanticAction semanticAction: Int) {
        action.setSemanticAction(semanticAction)
    }

    /**
     * Set whether or not this [Action][NotificationCompat.Action]'s [PendingIntent] will open a user
     * interface.
     *
     * @param showsUserInterface `true` if this [Action][NotificationCompat.Action]'s [PendingIntent]
     * will open a user interface, otherwise `false`
     *
     * The default value is {@code true}
     */
    fun showsUserInterface(showsUserInterface: Boolean) {
        action.setShowsUserInterface(showsUserInterface)
    }
}
