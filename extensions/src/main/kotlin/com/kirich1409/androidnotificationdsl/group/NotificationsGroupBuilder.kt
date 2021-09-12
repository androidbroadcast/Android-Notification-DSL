@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.group

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.collection.SparseArrayCompat
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.NotificationBuilder
import com.kirich1409.androidnotificationdsl.areNotificationsEnabled
import android.app.Notification as AndroidNotification

/**
 * Create group of notifications
 */
@Suppress("LongParameterList")
inline fun notificationsGroup(
    context: Context,
    groupKey: String,
    /**
     * Base channel id for all notification
     */
    channelId: String,
    /**
     * Don't build notification if all notifications for the app are disabled or notification channel is disabled
     */
    skipDisabledNotification: Boolean = true,
    alertBehavior: NotificationGroupAlertBehavior = NotificationGroupAlertBehavior.ALERT_ALL,
    body: NotificationsGroupBuilder.() -> Unit
): NotificationsGroup {
    val group = NotificationsGroupBuilder(context, groupKey, channelId, skipDisabledNotification, alertBehavior)
        .apply(body)
    val summaryNotification = requireNotNull(group.summary) { "Summary notification isn't set" }
    return NotificationsGroup(group.notifications, group.summaryId to summaryNotification, alertBehavior)
}

@NotificationsGroupMarker
@Suppress("UndocumentedPublicClass")
class NotificationsGroupBuilder @PublishedApi internal constructor(
    private val context: Context,
    private val groupKey: String,
    private val channelId: String,
    private val skipDisabledNotification: Boolean,
    private val groupAlertBehavior: NotificationGroupAlertBehavior
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
        body: @NotificationsGroupMarker NotificationBuilder.() -> Unit
    ) {
        if (needToBuildNotification(channelId)) {
            this.summaryId = notificationId
            summary = com.kirich1409.androidnotificationdsl.notification(context, channelId, smallIcon) {
                body()
                group = this@NotificationsGroupBuilder.groupKey
                groupAlertBehavior = this@NotificationsGroupBuilder.groupAlertBehavior.intValue
                groupSummary = true
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
        body: @NotificationsGroupMarker NotificationBuilder.() -> Unit
    ) {
        if (needToBuildNotification(channelId)) {
            notifications.put(notificationId,
                com.kirich1409.androidnotificationdsl.notification(context, channelId, smallIcon) {
                    body()
                    group = this@NotificationsGroupBuilder.groupKey
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
                    group = this@NotificationsGroupBuilder.groupKey
                }
            )
        }
    }

    private fun needToBuildNotification(channelId: String): Boolean {
        return !skipDisabledNotification || notificationManager.areNotificationsEnabled(channelId)
    }
}
