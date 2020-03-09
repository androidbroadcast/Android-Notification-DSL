@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.style

import android.graphics.Bitmap
import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationBigPictureStyleMarker

@NotificationBigPictureStyleMarker
inline class BigPictureStyle(
    private val bigPictureStyle: NotificationCompat.BigPictureStyle
) {

    fun bigLargeIcon(icon: Bitmap?) {
        bigPictureStyle.bigLargeIcon(icon)
    }

    fun bigPicture(icon: Bitmap?) {
        bigPictureStyle.bigPicture(icon)
    }

    fun bigContentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
        bigPictureStyle.setBigContentTitle(title)
    }

    fun summaryText(@Size(max = MAX_CHARSEQUENCE_LENGTH) summaryText: CharSequence?) {
        bigPictureStyle.setSummaryText(summaryText)
    }
}