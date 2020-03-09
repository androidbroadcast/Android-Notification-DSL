@file:Suppress("unused", "MemberVisibilityCanBePrivate", "NOTHING_TO_INLINE")

package com.kirich1409.androidnotificationdsl

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IntRange
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.NotificationVisibility
import androidx.core.app.Person
import com.kirich1409.androidnotificationdsl.annotations.NotificationCategory
import com.kirich1409.androidnotificationdsl.annotations.NotificationDefaults
import com.kirich1409.androidnotificationdsl.annotations.NotificationPriority
import com.kirich1409.androidnotificationdsl.car.CarExtender
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationMarker
import com.kirich1409.androidnotificationdsl.internal.toArray
import com.kirich1409.androidnotificationdsl.style.BigPictureStyle
import com.kirich1409.androidnotificationdsl.style.BigTextStyle
import com.kirich1409.androidnotificationdsl.style.InboxStyle
import com.kirich1409.androidnotificationdsl.style.message.MessagingStyle
import com.kirich1409.androidnotificationdsl.wearable.WearableExtender
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import android.app.Notification as AndroidNotification

/**
 * Create new notification for specified [channelId]
 *
 * @param channelId The constructed Notification will be posted on this NotificationChannel
 *
 * @return A new [Notification] object.
 */
inline fun notification(
    context: Context,
    channelId: String,
    @DrawableRes smallIcon: Int,
    body: Notification.() -> Unit
): AndroidNotification {
    NotificationCompat.Builder(context, channelId).apply {
        setSmallIcon(smallIcon)
        Notification(this).apply(body)
        return@notification build()
    }
}

inline fun notification(context: Context, channelId: String, @DrawableRes smallIcon: Int): AndroidNotification {
    NotificationCompat.Builder(context, channelId).apply {
        setSmallIcon(smallIcon)
        return@notification build()
    }
}

@NotificationMarker
inline class Notification(@PublishedApi internal val notification: NotificationCompat.Builder) {

    val actions: Actions
        get() = Actions(notification)

    inline fun actions(body: @NotificationMarker Actions.() -> Unit) {
        actions.body()
    }

    fun allowSystemGeneratedContextualActions(allowed: Boolean) {
        notification.setAllowSystemGeneratedContextualActions(allowed)
    }

    fun autoCancel(autoCancel: Boolean) {
        notification.setAutoCancel(autoCancel)
    }

    fun badgeIconType(@NotificationCompat.BadgeIconType value: Int) {
        notification.setBadgeIconType(value)
    }

    inline fun bigPictureStyle(body: @NotificationMarker BigPictureStyle.() -> Unit) {
        val bigPictureStyle = NotificationCompat.BigPictureStyle()
        BigPictureStyle(bigPictureStyle).body()
        notification.setStyle(bigPictureStyle)
    }

    inline fun bigTextStyle(body: @NotificationMarker BigTextStyle.() -> Unit) {
        val bigTextStyle = NotificationCompat.BigTextStyle()
        BigTextStyle(bigTextStyle).body()
        notification.setStyle(bigTextStyle)
    }

    inline fun bubbleMetadata(body: @NotificationMarker BubbleMetadata.() -> Unit) {
        val bubbleMetadataBuilder = NotificationCompat.BubbleMetadata.Builder()
        BubbleMetadata(bubbleMetadataBuilder).body()
        notification.bubbleMetadata = bubbleMetadataBuilder.build()
    }

    fun category(@NotificationCategory category: String) {
        notification.setCategory(category)
    }

    inline fun car(body: @NotificationMarker CarExtender.() -> Unit) {
        val carExtender = NotificationCompat.CarExtender()
        CarExtender(carExtender).body()
        extend(carExtender)
    }

    fun color(@ColorInt color: Int) {
        notification.color = color
    }

    fun colorized(colorize: Boolean) {
        notification.setColorized(colorize)
    }

    fun content(views: RemoteViews) {
        notification.setContent(views)
    }

    fun contentIntent(intent: PendingIntent) {
        notification.setContentIntent(intent)
    }

    fun contentInfo(info: CharSequence) {
        notification.setContentInfo(info)
    }

    fun contentText(text: CharSequence) {
        notification.setContentText(text)
    }

    fun contentTitle(title: CharSequence) {
        notification.setContentTitle(title)
    }

    fun chronometerCountDown(countsDown: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            notification.setChronometerCountDown(countsDown)
        }
    }

    fun customBigContentView(contentView: RemoteViews) {
        notification.setCustomBigContentView(contentView)
    }

    fun customContentView(contentView: RemoteViews) {
        notification.setCustomContentView(contentView)
    }

    fun customHeadsUpContentView(contentView: RemoteViews) {
        notification.setCustomHeadsUpContentView(contentView)
    }

    fun decoratedCustomViewStyle() {
        notification.setStyle(NotificationCompat.DecoratedCustomViewStyle())
    }

    fun defaults(@NotificationDefaults defaults: Int) {
        notification.setDefaults(defaults)
    }

    var extras: Bundle
        get() = notification.extras
        set(value) {
            notification.extras = value
        }

    @PublishedApi
    internal fun extend(extender: NotificationCompat.Extender) {
        notification.extend(extender)
    }

    fun fullScreenIntent(intent: PendingIntent, isHighPriority: Boolean = false) {
        notification.setFullScreenIntent(intent, isHighPriority)
    }

    fun group(groupKey: String) {
        notification.setGroup(groupKey)
    }

    fun groupAlertBehavior(@NotificationCompat.GroupAlertBehavior groupAlertBehavior: Int) {
        notification.setGroupAlertBehavior(groupAlertBehavior)
    }

    fun groupSummary(isGroupSummary: Boolean) {
        notification.setGroupSummary(isGroupSummary)
    }

    inline fun inboxStyle(body: @NotificationMarker InboxStyle.() -> Unit) {
        val inboxStyle = NotificationCompat.InboxStyle()
        InboxStyle(inboxStyle).body()
        notification.setStyle(inboxStyle)
    }

    fun largeIcon(icon: Bitmap) {
        notification.setLargeIcon(icon)
    }

    fun lights(@ColorInt color: Int, @IntRange(from = 0) onMs: Int, @IntRange(from = 0) offMs: Int) {
        notification.setLights(color, onMs, offMs)
    }

    fun localOnly(localOnly: Boolean) {
        notification.setLocalOnly(localOnly)
    }

    inline fun messagingStyle(person: Person, body: @NotificationMarker MessagingStyle.() -> Unit) {
        val messagingStyle = NotificationCompat.MessagingStyle(person)
        MessagingStyle(messagingStyle).body()
        notification.setStyle(messagingStyle)
    }

    fun number(number: Int) {
        notification.setNumber(number)
    }

    fun ongoing(ongoing: Boolean) {
        notification.setOngoing(ongoing)
    }

    fun onlyAlertOnce(onlyAlertOnce: Boolean) {
        notification.setOnlyAlertOnce(onlyAlertOnce)
    }

    val persons: Persons
        get() = Persons(notification)

    inline fun persons(body: @NotificationMarker Persons.() -> Unit) {
        persons.body()
    }

    fun priority(@NotificationPriority priority: Int) {
        notification.priority = priority
    }

    fun progress(@IntRange(from = 0) max: Int, @IntRange(from = 0) progress: Int, indeterminate: Boolean = false) {
        notification.setProgress(max, progress, indeterminate)
    }

    infix fun publicVersion(value: AndroidNotification) {
        notification.setPublicVersion(value)
    }

    fun remoteInputHistory(text: Array<CharSequence>) {
        notification.setRemoteInputHistory(text)
    }

    fun shortcutId(shortcutId: String) {
        notification.setShortcutId(shortcutId)
    }

    fun showWhen(show: Boolean) {
        notification.setShowWhen(show)
    }

    fun smallIcon(@DrawableRes icon: Int) {
        notification.setSmallIcon(icon)
    }

    fun smallIcon(@DrawableRes icon: Int, @IntRange(from = 0) level: Int) {
        notification.setSmallIcon(icon, level)
    }

    fun sortKey(sortKey: String) {
        notification.setSortKey(sortKey)
    }

    fun sound(sound: Uri) {
        notification.setSound(sound)
    }

    fun sound(sound: Uri, @NotificationCompat.StreamType streamType: Int) {
        notification.setSound(sound, streamType)
    }

    fun subText(text: CharSequence) {
        notification.setSubText(text)
    }

    fun ticker(tickerText: CharSequence, views: RemoteViews? = null) {
        notification.setTicker(tickerText, views)
    }

    fun timeoutAfter(@IntRange(from = 0) durationMs: Long) {
        notification.setTimeoutAfter(durationMs)
    }

    fun usesChronometer(value: Boolean) {
        notification.setUsesChronometer(value)
    }

    fun vibrate(pattern: LongArray) {
        notification.setVibrate(pattern)
    }

    fun visibility(@NotificationVisibility value: Int) {
        notification.setVisibility(value)
    }

    inline fun wearable(body: @NotificationMarker WearableExtender.() -> Unit) {
        val wearableExtender: NotificationCompat.WearableExtender = NotificationCompat.WearableExtender()
        WearableExtender(wearableExtender).body()
        extend(wearableExtender)
    }

    fun whenTime(@IntRange(from = 0) time: Long) {
        notification.setWhen(time)
    }
}

@ExperimentalTime
inline fun Notification.lights(@ColorInt color: Int, on: Duration, off: Duration) {
    require(on.isPositive() && on.isFinite()) { "`on` must be greater or equals than zero and finite" }
    require(off.isPositive() && off.isFinite()) { "`off` must be greater or equals than zero and finite" }
    lights(color, on.toInt(DurationUnit.MILLISECONDS), off.toInt(DurationUnit.MILLISECONDS))
}

inline fun Notification.remoteInputHistory(vararg text: CharSequence) {
    notification.setRemoteInputHistory(text)
}

inline fun Notification.remoteInputHistory(text: Iterable<CharSequence>) {
    notification.setRemoteInputHistory(text.toArray())
}

@ExperimentalTime
inline fun Notification.timeoutAfter(duration: Duration) {
    notification.setTimeoutAfter(duration.toLongMilliseconds())
}

inline fun Notification.vibrate(vararg pattern: Long) {
    notification.setVibrate(pattern)
}

inline fun Notification.style(style: NotificationCompat.Style) {
    notification.setStyle(style)
}
