@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.style.bigtext

import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH
import com.kirich1409.androidnotificationdsl.style.bigtext.annotations.NotificationBigTextStyleMarker

@NotificationBigTextStyleMarker
@Suppress("UndocumentedPublicClass")
class BigTextStyle @PublishedApi internal constructor(
    private val bigTextStyle: NotificationCompat.BigTextStyle
) {

    /**
     * Provide the longer text to be displayed in the big form of the
     * template in place of the content text.
     */
    fun bigText(@Size(max = MAX_CHARSEQUENCE_LENGTH) text: CharSequence?) {
        bigTextStyle.bigText(text)
    }

    /**
     * Overrides ContentTitle in the big form of the template.
     * This defaults to the value passed to setContentTitle().
     */
    fun bigContentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
        bigTextStyle.setBigContentTitle(title)
    }

    /**
     * Set the first line of text after the detail section in the big form of the template.
     */
    fun summaryText(@Size(max = MAX_CHARSEQUENCE_LENGTH) text: CharSequence) {
        bigTextStyle.setSummaryText(text)
    }
}

/**
 * Add a [BigTextStyle][NotificationCompat.BigTextStyle] to be applied at build time.
 *
 * If the platform does not provide rich notification styles, this method has no effect. The
 * user will always see the normal notification style.
 */
inline fun Notification.bigTextStyle(body: @NotificationMarker BigTextStyle.() -> Unit) {
    val bigTextStyle = NotificationCompat.BigTextStyle()
    BigTextStyle(bigTextStyle).body()
    style(bigTextStyle)
}
