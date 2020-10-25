@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.action

import android.app.PendingIntent
import androidx.annotation.RestrictTo
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.action.annotations.NotificationActionMarker
import com.kirich1409.androidnotificationdsl.remoteinput.RemoteInputBuilder
import androidx.core.app.RemoteInput as AndroidRemoteInput

/**
 * Builder of notification's action
 */
@NotificationActionMarker
class ActionBuilder @PublishedApi internal constructor(private val action: NotificationCompat.Action.Builder) {

    /**
     * Merge additional metadata into this builder.
     */
    val extras = Extras(action)

    /**
     * Add an input to be collected from the user when this action is sent.
     * Response values can be retrieved from the fired intent by using the
     * [AndroidRemoteInput.getResultsFromIntent] function.
     */
    val remoteInputs = RemoteInputs(action)

    /**
     * Add an input to be collected from the user when this action is sent.
     * Response values can be retrieved from the fired intent by using the
     * [AndroidRemoteInput.getResultsFromIntent] function.
     */
    fun addRemoteInput(resultKey: String, body: RemoteInputBuilder.() -> Unit) {
        val remoteInput = AndroidRemoteInput.Builder(resultKey).also { RemoteInputBuilder(it).body() }.build()
        action.addRemoteInput(remoteInput)
    }

    /**
     * Set whether the platform should automatically generate possible replies to add to
     * [AndroidRemoteInput.getChoices]. If the [ActionBuilder] doesn't have a
     * [RemoteInputBuilder], this has no effect.
     *
     * @param allowGeneratedReplies `true` to allow generated replies, `false` otherwise
     *
     * The default value is {@code true}
     */
    var allowGeneratedReplies: Boolean = true
        set(value) {
            field = value
            action.setAllowGeneratedReplies(value)
        }

    /**
     * Sets whether this [ActionBuilder] is a contextual action, i.e. whether the action is
     * dependent on the notification message body. An example of a contextual action could
     * be an action opening a map application with an address shown in the notification.
     */
    var contextual: Boolean = false
        set(value) {
            field = value
            action.setContextual(value)
        }

    /**
     * Apply an extender to this action builder.
     * Extenders may be used to add metadata or change options on this builder.
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    fun extend(extender: NotificationCompat.Action.Extender) {
        action.extend(extender)
    }

    /**
     * Sets the [SemanticAction][NotificationCompat.Action.SemanticAction] for this [ActionBuilder].
     * A [SemanticAction][NotificationCompat.Action.SemanticAction] denotes what an {@link Action}'s
     * [PendingIntent] will do (eg. reply, mark as read, delete, etc).
     *
     * @param semanticAction a [SemanticAction][@NotificationCompat.Action.SemanticAction] defined within
     * [ActionBuilder] with `SEMANTIC_ACTION_` prefixes
     */
    @NotificationCompat.Action.SemanticAction
    var semanticAction: Int = NotificationCompat.Action.SEMANTIC_ACTION_NONE
        set(value) {
            field = value
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
    var showsUserInterface = true
        set(value) {
            field = value
            action.setShowsUserInterface(value)
        }
}
