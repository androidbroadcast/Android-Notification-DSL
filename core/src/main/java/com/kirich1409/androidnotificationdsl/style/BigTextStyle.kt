@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.style

import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationBigTextStyleMarker

@NotificationBigTextStyleMarker
inline class BigTextStyle(private val style: NotificationCompat.BigTextStyle) {

    fun bigText(@Size(max = MAX_CHARSEQUENCE_LENGTH) text: CharSequence?) {
        style.bigText(text)
    }

    fun bigContentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
        style.setBigContentTitle(title)
    }

    fun summaryText(@Size(max = MAX_CHARSEQUENCE_LENGTH) text: CharSequence) {
        style.setSummaryText(text)
    }
}
