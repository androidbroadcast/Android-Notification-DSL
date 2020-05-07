@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.style.bigpicture

import android.graphics.Bitmap
import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH
import com.kirich1409.androidnotificationdsl.style.bigpicture.annotations.NotificationBigPictureStyleMarker

@NotificationBigPictureStyleMarker
@Suppress("UndocumentedPublicClass")
class BigPictureStyle @PublishedApi internal constructor(
    private val bigPictureStyle: NotificationCompat.BigPictureStyle
) {

    /**
     * Override the large icon when the big notification is shown.
     */
    fun bigLargeIcon(icon: Bitmap) {
        bigPictureStyle.bigLargeIcon(icon)
    }

    /**
     * Provide the bitmap to be used as the payload for the BigPicture notification.
     */
    fun bigPicture(icon: Bitmap) {
        bigPictureStyle.bigPicture(icon)
    }

    /**
     * Overrides ContentTitle in the big form of the template.
     * This defaults to the value passed to setContentTitle().
     */
    fun bigContentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
        bigPictureStyle.setBigContentTitle(title)
    }

    /**
     * Set the first line of text after the detail section in the big form of the template.
     */
    fun summaryText(@Size(max = MAX_CHARSEQUENCE_LENGTH) summaryText: CharSequence?) {
        bigPictureStyle.setSummaryText(summaryText)
    }
}

/**
 * Add a [BigPictureStyle][NotificationCompat.BigPictureStyle] to be applied at build time.
 *
 * If the platform does not provide rich notification styles, this method has no effect. The
 * user will always see the normal notification style.
 */
inline fun Notification.bigPictureStyle(
    body: @NotificationMarker BigPictureStyle.() -> Unit
): NotificationCompat.BigPictureStyle {
    val bigPictureStyle = NotificationCompat.BigPictureStyle()
    BigPictureStyle(bigPictureStyle).body()
    style(bigPictureStyle)
    return bigPictureStyle
}
