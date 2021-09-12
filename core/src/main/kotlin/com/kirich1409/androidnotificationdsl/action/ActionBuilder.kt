@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.action

import android.app.PendingIntent
import androidx.annotation.RestrictTo
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.remoteinput.RemoteInputBuilder
import androidx.core.app.RemoteInput as AndroidRemoteInput

/**
 * Builder of notification's action
 */
@NotificationActionMarker
class ActionBuilder @PublishedApi internal constructor(internal val action: NotificationCompat.Action.Builder) {

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

    fun remoteInputs(body: RemoteInputs.() -> Unit) {
        remoteInputs.body()
    }

    /**
     * Set whether the platform should automatically generate possible replies to add to
     * [AndroidRemoteInput.getChoices]. If the [ActionBuilder] doesn't have a
     * [RemoteInputBuilder], this has no effect.
     *
     * The default value is `true`
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
     * Sets the [SemanticAction] for this [ActionBuilder].
     * A [SemanticAction] denotes what an [Action][NotificationCompat.Action]'s
     * [PendingIntent] will do (eg. reply, mark as read, delete, etc).
     */
    var semanticAction: SemanticAction = SemanticAction.NONE
        set(value) {
            field = value
            action.setSemanticAction(semanticAction.intValue)
        }

    /**
     * Set whether or not this [Action][NotificationCompat.Action]'s [PendingIntent] will open a user
     * interface.
     *
     * The default value is `true`
     */
    var showsUserInterface = true
        set(value) {
            field = value
            action.setShowsUserInterface(value)
        }
}



/**
 * Apply an extender to this action builder.
 * Extenders may be used to add metadata or change options on this builder.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun ActionBuilder.extend(extender: NotificationCompat.Action.Extender) {
    action.extend(extender)
}
