@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.style

import android.graphics.Bitmap
import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationBigPictureStyleMarker

@NotificationBigPictureStyleMarker
inline class BigPictureStyle(private val style: NotificationCompat.BigPictureStyle) {

    fun bigLargeIcon(icon: Bitmap?) {
        style.bigLargeIcon(icon)
    }

    fun bigPicture(icon: Bitmap?) {
        style.bigPicture(icon)
    }

    fun bigContentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
        style.setBigContentTitle(title)
    }

    fun summaryText(@Size(max = MAX_CHARSEQUENCE_LENGTH) summaryText: CharSequence?) {
        style.setSummaryText(summaryText)
    }
}