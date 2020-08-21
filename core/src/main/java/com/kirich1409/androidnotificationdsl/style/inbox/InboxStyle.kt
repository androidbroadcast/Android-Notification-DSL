@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.style.inbox

import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH
import com.kirich1409.androidnotificationdsl.style.inbox.annotations.NotificationInboxStyleLinesMarker
import com.kirich1409.androidnotificationdsl.style.inbox.annotations.NotificationInboxStyleMarker

@NotificationInboxStyleMarker
@Suppress("UndocumentedPublicClass")
class InboxStyle @PublishedApi internal constructor(
    @PublishedApi internal val inboxStyle: NotificationCompat.InboxStyle
) {

    /**
     * Lines of the digest section of the Inbox notification.
     */
    inline val lines: Lines
        get() = Lines(inboxStyle)

    /**
     * Overrides ContentTitle in the big form of the template.
     * This defaults to the value passed to setContentTitle().
     */
    @Deprecated("Use contentTitle() instead", ReplaceWith("contentTitle(title)"))
    fun bigContentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
        contentTitle(title)
    }

    /**
     * Overrides ContentTitle in the big form of the template.
     * This defaults to the value passed to setContentTitle().
     */
    fun contentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
        inboxStyle.setBigContentTitle(title)
    }

    /**
     * Append lines to the digest section of the Inbox notification.
     */
    inline fun lines(body: @NotificationInboxStyleMarker Lines.() -> Unit) {
        lines.body()
    }

    /**
     * Set the first line of text after the detail section in the big form of the template.
     */
    fun summaryText(@Size(max = MAX_CHARSEQUENCE_LENGTH) summaryText: CharSequence?) {
        inboxStyle.setSummaryText(summaryText)
    }

    @NotificationInboxStyleLinesMarker
    class Lines @PublishedApi internal constructor(
        internal val inboxStyle: NotificationCompat.InboxStyle
    ) {

        /**
         * Append a line to the digest section of the Inbox notification.
         */
        fun line(@Size(max = MAX_CHARSEQUENCE_LENGTH) line: CharSequence) {
            inboxStyle.addLine(line)
        }
    }
}

/**
 * Append a line to the digest section of the Inbox notification.
 */
operator fun InboxStyle.Lines.plus(@Size(max = MAX_CHARSEQUENCE_LENGTH) line: CharSequence) {
    inboxStyle.addLine(line)
}

/**
 * Append a line to the digest section of the Inbox notification.
 */
operator fun InboxStyle.Lines.plus(lines: Iterable<CharSequence>) {
    lines.forEach { inboxStyle.addLine(it) }
}

/**
 * Add a [InboxStyle][NotificationCompat.InboxStyle] to be applied at build time.
 *
 * If the platform does not provide rich notification styles, this method has no effect. The
 * user will always see the normal notification style.
 */
inline fun Notification.inboxStyle(body: @NotificationMarker InboxStyle.() -> Unit): NotificationCompat.InboxStyle {
    val inboxStyle = NotificationCompat.InboxStyle()
    style(inboxStyle.also { InboxStyle(it).body() })
    return inboxStyle
}
