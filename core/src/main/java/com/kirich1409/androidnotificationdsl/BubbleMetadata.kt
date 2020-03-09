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
inline class BubbleMetadata(private val bubbleMetadata: NotificationCompat.BubbleMetadata.Builder) {

    fun autoExpandBubble(shouldExpand: Boolean) {
        bubbleMetadata.setAutoExpandBubble(shouldExpand)
    }

    fun deleteIntent(deleteIntent: PendingIntent?) {
        bubbleMetadata.setDeleteIntent(deleteIntent)
    }

    fun desiredHeight(@Dimension(unit = Dimension.DP) @IntRange(from = 0) height: Int) {
        bubbleMetadata.setDesiredHeight(height)
    }

    fun desiredHeightResId(@DimenRes heightResId: Int) {
        bubbleMetadata.setDesiredHeightResId(heightResId)
    }

    fun icon(icon: IconCompat) {
        bubbleMetadata.setIcon(icon)
    }

    fun intent(intent: PendingIntent) {
        bubbleMetadata.setIntent(intent)
    }

    fun suppressNotification(shouldSuppressNotification: Boolean) {
        bubbleMetadata.setSuppressNotification(shouldSuppressNotification)
    }
}