@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.style.inbox

import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH

@NotificationInboxStyleMarker
@Suppress("UndocumentedPublicClass")
class InboxStyle @PublishedApi internal constructor(
    @PublishedApi internal val inboxStyle: NotificationCompat.InboxStyle
) {

    /**
     * Overrides ContentTitle in the big form of the template.
     * This defaults to the value passed to setContentTitle().
     */
    @Size(max = MAX_CHARSEQUENCE_LENGTH)
    var contentTile: CharSequence? = null
        set(value) {
            field = value
            inboxStyle.setBigContentTitle(value)
        }

    /**
     * Lines of the digest section of the Inbox notification.
     */
    val lines = Lines(inboxStyle)

    /**
     * Append lines to the digest section of the Inbox notification.
     */
    inline fun lines(body: @NotificationInboxStyleMarker Lines.() -> Unit) {
        lines.body()
    }

    /**
     * Set the first line of text after the detail section in the big form of the template.
     */
    @Size(max = MAX_CHARSEQUENCE_LENGTH)
    var summaryText: CharSequence? = null
        set(value) {
            field = value
            inboxStyle.setSummaryText(value)
        }

}

/**
 * Add a [InboxStyle][NotificationCompat.InboxStyle] to be applied at build time.
 *
 * If the platform does not provide rich notification styles, this method has no effect. The
 * user will always see the normal notification style.
 */
inline fun NotificationBuilder.inboxStyle(
    body: @NotificationMarker InboxStyle.() -> Unit
): NotificationCompat.InboxStyle {
    return NotificationCompat.InboxStyle().also { inboxStyle ->
        style = inboxStyle.also { InboxStyle(it).body() }
    }
}

fun NotificationBuilder.inboxStyle(
    title: CharSequence? = null,
    summaryText: CharSequence? = null
): NotificationCompat.InboxStyle {
    return NotificationCompat.InboxStyle()
        .setBigContentTitle(title)
        .setSummaryText(summaryText)
        .also { style = it }
}

inline fun NotificationBuilder.inboxStyle(
    title: CharSequence? = null,
    summaryText: CharSequence? = null,
    lines: @NotificationMarker Lines.() -> Unit
): NotificationCompat.InboxStyle {
    val inboxStyle = NotificationCompat.InboxStyle()
        .setBigContentTitle(title)
        .setSummaryText(summaryText)
    Lines(inboxStyle).lines()
    style = inboxStyle
    return inboxStyle
}

fun NotificationBuilder.inboxStyle(): NotificationCompat.InboxStyle {
    return NotificationCompat.InboxStyle().also { style = it }
}
