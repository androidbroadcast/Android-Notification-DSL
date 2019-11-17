@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import android.app.PendingIntent
import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.annotation.IntRange
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationBubbleMetadataMarker

@NotificationBubbleMetadataMarker
inline class BubbleMetadata(private val builder: NotificationCompat.BubbleMetadata.Builder) {

    fun autoExpandBubble(shouldExpand: Boolean) {
        builder.setAutoExpandBubble(shouldExpand)
    }

    fun deleteIntent(deleteIntent: PendingIntent?) {
        builder.setDeleteIntent(deleteIntent)
    }

    fun desiredHeight(@Dimension(unit = Dimension.DP) @IntRange(from = 0) height: Int) {
        builder.setDesiredHeight(height)
    }

    fun desiredHeightResId(@DimenRes heightResId: Int) {
        builder.setDesiredHeightResId(heightResId)
    }

    fun icon(icon: IconCompat) {
        builder.setIcon(icon)
    }

    fun intent(intent: PendingIntent) {
        builder.setIntent(intent)
    }

    fun suppressNotification(shouldSuppressNotification: Boolean) {
        builder.setSuppressNotification(shouldSuppressNotification)
    }
}