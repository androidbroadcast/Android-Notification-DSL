@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.group

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.collection.SparseArrayCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.Notification
import com.kirich1409.androidnotificationdsl.areNotificationsEnabled
import com.kirich1409.androidnotificationdsl.group.annotations.NotificationsGroupMarker
import com.kirich1409.androidnotificationdsl.internal.asMap
import android.app.Notification as AndroidNotification

/**
 * Create group of notifications
 */
@Suppress("LongParameterList")
inline fun notificationsGroup(
    context: Context,
    groupKey: String,
    channelId: String,
    skipDisabledNotification: Boolean = true,
    @NotificationCompat.GroupAlertBehavior groupAlertBehavior: Int = NotificationCompat.GROUP_ALERT_ALL,
    body: NotificationsGroupBuilder.() -> Unit
): NotificationsGroup {
    val group = NotificationsGroupBuilder(context, groupKey, channelId, skipDisabledNotification, groupAlertBehavior)
        .apply(body)
    val summaryNotification = requireNotNull(group.summary) { "Summary notification isn't set" }
    return NotificationsGroup(group.notifications.asMap(), group.summaryId to summaryNotification)
}

@NotificationsGroupMarker
@Suppress("UndocumentedPublicClass")
class NotificationsGroupBuilder @PublishedApi internal constructor(
    private val context: Context,
    private val groupKey: String,
    private val channelId: String,
    private val skipDisabledNotification: Boolean,
    @NotificationCompat.GroupAlertBehavior private val groupAlertBehavior: Int
) {
    private val notificationManager = NotificationManagerCompat.from(context)

    @Suppress("MemberNameEqualsClassName")
    @PublishedApi
    internal val notifications = SparseArrayCompat<AndroidNotification>()

    @PublishedApi
    internal var summary: AndroidNotification? = null

    @PublishedApi
    internal var summaryId = 0

    /**
     * Create a notification and set it as notification groip summary.
     *
     * If notification channel is disabled than the notification will be added only
     * if [NotificationsGroupBuilder.skipDisabledNotification] is `false`.
     */
    fun summary(
        notificationId: Int,
        @DrawableRes smallIcon: Int,
        channelId: String = this.channelId,
        body: @NotificationsGroupMarker Notification.() -> Unit
    ) {
        if (needToBuildNotification(channelId)) {
            this.summaryId = notificationId
            summary = com.kirich1409.androidnotificationdsl.notification(context, channelId, smallIcon) {
                body()
                group(groupKey)
                groupAlertBehavior(groupAlertBehavior)
                groupSummary(true)
            }
        }
    }

    /**
     * Set [notification] as notifications group summary
     *
     * If notification channel is disabled than the notification will be added only
     * if [NotificationsGroupBuilder.skipDisabledNotification] is `false`.
     */
    fun summary(notificationId: Int, notification: AndroidNotification) {
        if (needToBuildNotification(channelId)) {
            this.summaryId = notificationId
            summary = notification
        }
    }

    /**
     * Create a notification and add it to the group. If notification channel is disabled than the notification
     * will be added only if [NotificationsGroupBuilder.skipDisabledNotification] is `false`.
     */
    fun notification(
        notificationId: Int,
        @DrawableRes smallIcon: Int,
        channelId: String = this@NotificationsGroupBuilder.channelId,
        body: @NotificationsGroupMarker Notification.() -> Unit
    ) {
        if (needToBuildNotification(channelId)) {
            notifications.put(notificationId,
                com.kirich1409.androidnotificationdsl.notification(context, channelId, smallIcon) {
                    body()
                    group(groupKey)
                }
            )
        }
    }

    /**
     * Create a notification and add it to the group. If notification channel is disabled than the notification
     * will be added only if [NotificationsGroupBuilder.skipDisabledNotification] is `false`.
     */
    fun notification(
        notificationId: Int,
        @DrawableRes smallIcon: Int,
        channelId: String = this@NotificationsGroupBuilder.channelId
    ) {
        if (needToBuildNotification(channelId)) {
            notifications.put(notificationId,
                com.kirich1409.androidnotificationdsl.notification(context, channelId, smallIcon) {
                    group(groupKey)
                }
            )
        }
    }

    private fun needToBuildNotification(channelId: String): Boolean {
        return !skipDisabledNotification || notificationManager.areNotificationsEnabled(channelId)
    }
}
