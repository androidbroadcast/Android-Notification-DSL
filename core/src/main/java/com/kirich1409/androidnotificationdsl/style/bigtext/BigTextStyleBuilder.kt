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
    @Size(max = MAX_CHARSEQUENCE_LENGTH)
    var text: CharSequence? = null
        set(value) {
            field = value
            bigTextStyle.bigText(value)
        }

    /**
     * Overrides ContentTitle in the big form of the template.
     * This defaults to the value passed to setContentTitle().
     */
    @Size(max = MAX_CHARSEQUENCE_LENGTH)
    var contentTitle: CharSequence? = null
        set(value) {
            field = value
            bigTextStyle.setBigContentTitle(value)
        }

    /**
     * Set the first line of text after the detail section in the big form of the template.
     */
    @Size(max = MAX_CHARSEQUENCE_LENGTH)
    var summaryText: CharSequence? = null
        set(value) {
            field = value
            bigTextStyle.setSummaryText(value)
        }
}

/**
 * Add a [BigTextStyle][NotificationCompat.BigTextStyle] to be applied at build time.
 *
 * If the platform does not provide rich notification styles, this method has no effect. The
 * user will always see the normal notification style.
 */
inline fun NotificationBuilder.bigTextStyle(body: @NotificationMarker BigTextStyleBuilder.() -> Unit) {
    style = NotificationCompat.BigTextStyle().also { bigTextStyle ->
        BigTextStyleBuilder(bigTextStyle).body()
    }
}

fun NotificationBuilder.bigTextStyle(
    text: CharSequence? = null,
    contentTitle: CharSequence? = null,
    summaryText: CharSequence? = null
) {
    style = NotificationCompat.BigTextStyle()
        .bigText(text)
        .setBigContentTitle(contentTitle)
        .setSummaryText(summaryText)
}

fun NotificationBuilder.bigTextStyle() {
    style = NotificationCompat.BigTextStyle()
}
