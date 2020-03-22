@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.style

import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationInboxStyleLinesMarker
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationInboxStyleMarker
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.style

@NotificationInboxStyleMarker
inline class InboxStyle(
    private val inboxStyle: NotificationCompat.InboxStyle
) {

    fun bigContentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
        inboxStyle.setBigContentTitle(title)
    }

    val lines: Lines
        get() = Lines(inboxStyle)

    inline fun lines(body: @NotificationInboxStyleMarker Lines.() -> Unit) {
        lines.body()
    }

    fun summaryText(@Size(max = MAX_CHARSEQUENCE_LENGTH) summaryText: CharSequence?) {
        inboxStyle.setSummaryText(summaryText)
    }
}

@NotificationInboxStyleLinesMarker
inline class Lines(internal val inboxStyle: NotificationCompat.InboxStyle) {

    fun CharSequence.asLine() {
        inboxStyle.addLine(this)
    }
}

operator fun Lines.plus(@Size(max = MAX_CHARSEQUENCE_LENGTH) line: CharSequence) {
    inboxStyle.addLine(line)
}

operator fun Lines.plus(lines: Iterable<CharSequence>) {
    lines.forEach(inboxStyle::addLine)
}

inline fun Notification.inboxStyle(body: @NotificationMarker InboxStyle.() -> Unit) {
    style(NotificationCompat.InboxStyle().also { InboxStyle(it).body() })
}
