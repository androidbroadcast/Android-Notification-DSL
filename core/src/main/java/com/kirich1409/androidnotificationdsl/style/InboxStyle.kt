@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.style

import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationInboxStyleLinesMarker
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationInboxStyleMarker

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

    @NotificationInboxStyleLinesMarker
    class Lines(private val inboxStyle: NotificationCompat.InboxStyle) {

        fun String.line() {
            inboxStyle.addLine(this)
        }

        operator fun plus(@Size(max = MAX_CHARSEQUENCE_LENGTH) line: CharSequence) {
            inboxStyle.addLine(line)
        }

        operator fun plus(lines: Iterable<CharSequence>) {
            lines.forEach { line ->
                inboxStyle.addLine(line)
            }
        }
    }
}