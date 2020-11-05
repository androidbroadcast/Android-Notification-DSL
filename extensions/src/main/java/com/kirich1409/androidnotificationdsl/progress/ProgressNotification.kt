@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.kirich1409.androidnotificationdsl.progress

import android.app.Notification
import android.content.Context
import androidx.annotation.DrawableRes
import com.ironsource.aura.dslint.annotations.DSLint
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.action.Actions
import com.kirich1409.androidnotificationdsl.action.annotations.NotificationActionsMarker
import com.kirich1409.androidnotificationdsl.indeterminateProgress
import com.kirich1409.androidnotificationdsl.notification
import com.kirich1409.androidnotificationdsl.progress

/**
 * Create new notification with displaying big text in expanded state
 */
fun progressNotification(
    context: Context,
    channelId: String,
    @DrawableRes smallIcon: Int,
    body: ProgressNotificationBuilder.() -> Unit
): Notification {
    val builder = ProgressNotificationBuilder().apply(body)
    return notification(context, channelId, smallIcon) {
        contentTitle = builder.title
        contentText = builder.progressText
        progress = if (builder.indeterminated) {
            indeterminateProgress()
        } else {
            val builderProgress = checkNotNull(builder.progress)
            progress(builderProgress.current, builderProgress.max)
        }
        builder.buildActions?.let(::actions)
        builder.extender?.invoke(this)
    }
}

private fun check(p: ProgressNotificationBuilder.Progress?): ProgressNotificationBuilder.Progress {
    val progress: ProgressNotificationBuilder.Progress = requireNotNull(p) {
        "progress wasn't setup. Call indeterminate = true or set progress"
    }
    require(progress.current >= 0) { "Current progress must be >= 0" }
    require(progress.max >= 0) { "Max progress must be >= 0" }
    require(progress.current <= progress.max) { "Current progress must be <= max" }
    return progress
}

@DSLint
@ProgressNotificationBuilderMarker
class ProgressNotificationBuilder internal constructor() {

    /**
     * Notification title
     */
    var title: CharSequence? = null

    /**
     * Text that will be used to describe progress like "6 seconds left". When progress is hidden looks
     * like notification content text.
     */
    var progressText: CharSequence? = null

    /**
     * Is progress is indeterminate
     */
    var indeterminated: Boolean = false

    internal var extender: (NotificationBuilder.() -> Unit)? = null

    internal var progress: Progress? = null

    internal var buildActions: (Actions.() -> Unit)? = null

    fun progress(body: @ProgressMarker Progress.() -> Unit) {
        (this.progress ?: Progress().also { this.progress = it }).apply(body)
    }

    /**
     * Move ProgressBar to finished state
     */
    fun finished() {
        progress = Progress(1, 1)
    }

    /**
     * Hide ProgressBar from the notification. Don't forget to update content of the notification.
     */
    fun hideProgress() {
        progress = Progress(0, 0)
    }

    /**
     * Notification's actions
     *
     * Actions will not be showed on Android before 4.1
     */
    fun actions(body: @NotificationActionsMarker Actions.() -> Unit) {
        buildActions = body
    }

    fun extend(body: NotificationBuilder.() -> Unit) {
        extender = body
    }

    @ProgressMarker
    class Progress internal constructor(
        var current: Int = DEFAULT_CURRENT,
        var max: Int = DEFAULT_MAX
    ) {

        private companion object {
            const val DEFAULT_MAX = 100
            const val DEFAULT_CURRENT = 0
        }
    }
}

