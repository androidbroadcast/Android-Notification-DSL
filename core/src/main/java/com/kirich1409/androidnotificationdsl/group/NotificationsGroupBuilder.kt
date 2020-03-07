@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.group

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.IntRange
import androidx.collection.SparseArrayCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.internal.areNotificationsEnabled
import com.kirich1409.androidnotificationdsl.internal.asMap
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationsGroupMarker
import android.app.Notification as AndroidNotification

fun NotificationManagerCompat.notificationsGroup(
    context: Context,
    groupKey: String,
    channelId: String,
    skipDisabledNotification: Boolean = true,
    @NotificationCompat.GroupAlertBehavior groupAlertBehavior: Int = NotificationCompat.GROUP_ALERT_ALL,
    body: NotificationsGroupBuilder.() -> Unit
): NotificationsGroup {
    val group =
        NotificationsGroupBuilder(context, groupKey, channelId, skipDisabledNotification, groupAlertBehavior)
            .apply(body)
    val summaryNotification = requireNotNull(group.summary) { "Summary notification isn't set" }
    return NotificationsGroup(group.notifications.asMap(), group.summaryId to summaryNotification)
}

@NotificationsGroupMarker
class NotificationsGroupBuilder internal constructor(
    context: Context,
    private val groupKey: String,
    private val channelId: String,
    private val skipDisabledNotification: Boolean,
    @NotificationCompat.GroupAlertBehavior private val groupAlertBehavior: Int
) {
    private val context: Context = context.applicationContext
    private val notificationManager = NotificationManagerCompat.from(context)

    internal val notifications = SparseArrayCompat<AndroidNotification>()
    internal var summary: AndroidNotification? = null
    internal var summaryId: Int = 0

    fun summary(
        @IntRange(from = 1) notificationId: Int,
        @DrawableRes smallIcon: Int,
        channelId: String = this.channelId,
        body: @NotificationsGroupMarker Notification.() -> Unit
    ) {
        if (needToBuildNotification(channelId)) {
            this.summaryId = notificationId
            summary =
                com.kirich1409.androidnotificationdsl.notification(context, channelId, smallIcon) {
                    body()
                    group(groupKey)
                    groupAlertBehavior(groupAlertBehavior)
                    groupSummary(true)
                }
        }
    }

    fun notification(
        @IntRange(from = 1) notificationId: Int,
        @DrawableRes smallIcon: Int,
        channelId: String = this.channelId,
        body: @NotificationsGroupMarker Notification.() -> Unit
    ) {
        if (needToBuildNotification(channelId)) {
            val notification =
                com.kirich1409.androidnotificationdsl.notification(context, channelId, smallIcon) {
                    body()
                    group(groupKey)
                }
            notifications.put(notificationId, notification)
        }
    }

    private fun needToBuildNotification(channelId: String): Boolean {
        return !skipDisabledNotification || notificationManager.areNotificationsEnabled(channelId)
    }
}