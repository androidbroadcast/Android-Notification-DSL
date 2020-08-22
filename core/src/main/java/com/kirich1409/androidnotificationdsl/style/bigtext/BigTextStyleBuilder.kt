@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.style.bigtext

import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.annotations.NotificationMarker
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH

@NotificationBigTextStyleMarker
@Suppress("UndocumentedPublicClass")
class BigTextStyleBuilder @PublishedApi internal constructor(
    private val bigTextStyle: NotificationCompat.BigTextStyle
) {

    /**
     * Provide the longer text to be displayed in the big form of the
     * template in place of the content text.
     */
    @Deprecated("Use text() instead", ReplaceWith("text(text)"))
    fun bigText(@Size(max = MAX_CHARSEQUENCE_LENGTH) text: CharSequence?) {
        text(text)
    }

    /**
     * Provide the longer text to be displayed in the big form of the
     * template in place of the content text.
     */
    fun text(@Size(max = MAX_CHARSEQUENCE_LENGTH) text: CharSequence?) {
        bigTextStyle.bigText(text)
    }

    /**
     * Overrides ContentTitle in the big form of the template.
     * This defaults to the value passed to setContentTitle().
     */
    @Deprecated("Use contentTitle() instead", ReplaceWith("contentTitle(text)"))
    fun bigContentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
        contentTitle(title)
    }

    /**
     * Overrides ContentTitle in the big form of the template.
     * This defaults to the value passed to setContentTitle().
     */
    fun contentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
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
inline fun NotificationBuilder.bigTextStyle(body: @NotificationMarker BigTextStyleBuilder.() -> Unit) {
    val bigTextStyle = NotificationCompat.BigTextStyle()
    BigTextStyleBuilder(bigTextStyle).body()
    style(bigTextStyle)
}
