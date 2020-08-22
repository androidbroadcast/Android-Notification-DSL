package com.kirich1409.androidnotificationdsl.style.inbox

import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH

@NotificationInboxStyleLinesMarker
class LinesBuilder @PublishedApi internal constructor(
    internal val inboxStyle: NotificationCompat.InboxStyle
) {

    /**
     * Append a line to the digest section of the Inbox notification.
     */
    fun line(@Size(max = MAX_CHARSEQUENCE_LENGTH) line: CharSequence) {
        inboxStyle.addLine(line)
    }
}
