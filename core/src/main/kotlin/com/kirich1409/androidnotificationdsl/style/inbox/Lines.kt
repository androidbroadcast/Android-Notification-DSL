@file:Suppress("MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.style.inbox

import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.Container

inline class Lines(private val inboxStyle: NotificationCompat.InboxStyle) : Container<CharSequence> {

    /**
     * Append a line to the digest section of the Inbox notification.
     */
    fun line(line: CharSequence) {
        inboxStyle.addLine(line)
    }

    /**
     * @see line
     */
    override fun plusAssign(item: CharSequence) {
        line(item)
    }
}
