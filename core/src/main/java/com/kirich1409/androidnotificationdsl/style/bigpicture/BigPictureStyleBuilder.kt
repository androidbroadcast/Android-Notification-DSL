@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.style.bigpicture

import android.graphics.Bitmap
import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.annotations.NotificationMarker
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH

@NotificationBigPictureStyleMarker
@Suppress("UndocumentedPublicClass")
class BigPictureStyleBuilder @PublishedApi internal constructor(
    private val bigPictureStyle: NotificationCompat.BigPictureStyle
) {

    /**
     * Override the large icon when the big notification is shown.
     */
    @Deprecated("Use largeIcon() instead", ReplaceWith("largeIcon(icon)"))
    fun bigLargeIcon(icon: Bitmap) {
        largeIcon(icon)
    }

    /**
     * Override the large icon when the big notification is shown.
     */
    fun largeIcon(icon: Bitmap?) {
        bigPictureStyle.bigLargeIcon(icon)
    }

    /**
     * Provide the bitmap to be used as the payload for the BigPicture notification.
     */
    @Deprecated("Use picture() instead", ReplaceWith("picture(icon)"))
    fun bigPicture(icon: Bitmap) {
        picture(icon)
    }

    /**
     * Provide the bitmap to be used as the payload for the BigPicture notification.
     */
    fun picture(icon: Bitmap) {
        bigPictureStyle.bigPicture(icon)
    }

    /**
     * Overrides ContentTitle in the big form of the template.
     * This defaults to the value passed to setContentTitle().
     */
    @Deprecated("Use contentTitle() instead", ReplaceWith("contentTitle(title)"))
    fun bigContentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
        contentTitle(title)
    }

    /**
     * Overrides ContentTitle in the big form of the template.
     * This defaults to the value passed to setContentTitle().
     */
    fun contentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
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
inline fun NotificationBuilder.bigPictureStyle(
    body: @NotificationMarker BigPictureStyleBuilder.() -> Unit
) {
    val bigPictureStyle = NotificationCompat.BigPictureStyle()
    BigPictureStyleBuilder(bigPictureStyle).body()
    style = bigPictureStyle
}
