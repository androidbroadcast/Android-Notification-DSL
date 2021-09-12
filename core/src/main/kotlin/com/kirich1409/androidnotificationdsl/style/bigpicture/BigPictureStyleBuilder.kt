@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.style.bigpicture

import android.graphics.Bitmap
import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH
import com.kirich1409.androidnotificationdsl.internal.requiredNotificationProperty

@NotificationBigPictureStyleMarker
@Suppress("UndocumentedPublicClass")
class BigPictureStyleBuilder @PublishedApi internal constructor(
    private val bigPictureStyle: NotificationCompat.BigPictureStyle
) {

    /**
     * Override the large icon when the big notification is shown.
     */
    var largeIcon: Bitmap? = null
        set(icon) {
            field = icon
            bigPictureStyle.bigLargeIcon(icon)
        }

    /**
     * Provide the bitmap to be used as the payload for the BigPicture notification.
     */
    var picture: Bitmap by requiredNotificationProperty("picture") { icon ->
        bigPictureStyle.bigPicture(icon)
    }

    /**
     * Overrides ContentTitle in the big form of the template.
     * This defaults to the value passed to setContentTitle().
     */
    @Size(max = MAX_CHARSEQUENCE_LENGTH)
    var contentTitle: CharSequence? = null
        set(title) {
            field = title
            bigPictureStyle.setBigContentTitle(title)
        }

    /**
     * Set the first line of text after the detail section in the big form of the template.
     */
    @Size(max = MAX_CHARSEQUENCE_LENGTH)
    var summaryText: CharSequence? = null
        set(summaryText) {
            field = summaryText
            bigPictureStyle.setSummaryText(summaryText)
        }
}

/**
 * Add a [BigPictureStyle][NotificationCompat.BigPictureStyle] to be applied at build time.
 *
 * If the platform does not provide rich notification styles, this method has no effect. The
 * user will always see the normal notification style.
 */
inline fun NotificationBuilder.bigPictureStyle(
    body: @NotificationMarker BigPictureStyleBuilder.() -> Unit
) {
    val bigPictureStyle = NotificationCompat.BigPictureStyle()
    BigPictureStyleBuilder(bigPictureStyle).body()
    style = bigPictureStyle
}
