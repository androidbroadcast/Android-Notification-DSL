@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.style

import androidx.annotation.Size
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.internal.MAX_CHARSEQUENCE_LENGTH
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationBigTextStyleMarker
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.style

@NotificationBigTextStyleMarker
inline class BigTextStyle(
    private val bigTextStyle: NotificationCompat.BigTextStyle
) {

    fun bigText(@Size(max = MAX_CHARSEQUENCE_LENGTH) text: CharSequence?) {
        bigTextStyle.bigText(text)
    }

    fun bigContentTitle(@Size(max = MAX_CHARSEQUENCE_LENGTH) title: CharSequence?) {
        bigTextStyle.setBigContentTitle(title)
    }

    fun summaryText(@Size(max = MAX_CHARSEQUENCE_LENGTH) text: CharSequence) {
        bigTextStyle.setSummaryText(text)
    }
}

inline fun Notification.bigTextStyle(body: @NotificationMarker BigTextStyle.() -> Unit) {
    val bigTextStyle = NotificationCompat.BigTextStyle()
    BigTextStyle(bigTextStyle).body()
    style(bigTextStyle)
}
