@file:Suppress("unused", "MemberVisibilityCanBePrivate")

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
fun notification(
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

fun notification(context: Context, channelId: String, @DrawableRes smallIcon: Int): AndroidNotification {
    NotificationCompat.Builder(context, channelId).apply {
        setSmallIcon(smallIcon)
        return@notification build()
    }
}

@NotificationMarker
inline class Notification(internal val builder: NotificationCompat.Builder) {

    val actions: Actions
        get() = Actions(builder)

    fun actions(body: @NotificationMarker Actions.() -> Unit) {
        actions.body()
    }

    fun allowSystemGeneratedContextualActions(allowed: Boolean) {
        builder.setAllowSystemGeneratedContextualActions(allowed)
    }

    fun autoCancel(autoCancel: Boolean) {
        builder.setAutoCancel(autoCancel)
    }

    fun badgeIconType(@NotificationCompat.BadgeIconType value: Int) {
        builder.setBadgeIconType(value)
    }

    fun bigPictureStyle(body: @NotificationMarker BigPictureStyle.() -> Unit) {
        val bigPictureStyle = NotificationCompat.BigPictureStyle()
        BigPictureStyle(bigPictureStyle).body()
        builder.setStyle(bigPictureStyle)
    }

    fun bigTextStyle(body: @NotificationMarker BigTextStyle.() -> Unit) {
        val bigTextStyle = NotificationCompat.BigTextStyle()
        BigTextStyle(bigTextStyle).body()
        builder.setStyle(bigTextStyle)
    }

    fun bubbleMetadata(body: @NotificationMarker BubbleMetadata.() -> Unit) {
        val bubbleMetadataBuilder = NotificationCompat.BubbleMetadata.Builder()
        BubbleMetadata(bubbleMetadataBuilder).body()
        builder.bubbleMetadata = bubbleMetadataBuilder.build()
    }

    fun category(@NotificationCategory category: String) {
        builder.setCategory(category)
    }

    fun car(body: @NotificationMarker CarExtender.() -> Unit) {
        val carExtender = NotificationCompat.CarExtender()
        CarExtender(carExtender).body()
        extend(carExtender)
    }

    fun color(@ColorInt color: Int) {
        builder.color = color
    }

    fun colorized(colorize: Boolean) {
        builder.setColorized(colorize)
    }

    fun content(views: RemoteViews) {
        builder.setContent(views)
    }

    fun contentIntent(intent: PendingIntent) {
        builder.setContentIntent(intent)
    }

    fun contentInfo(info: CharSequence) {
        builder.setContentInfo(info)
    }

    fun contentText(text: CharSequence) {
        builder.setContentText(text)
    }

    fun contentTitle(title: CharSequence) {
        builder.setContentTitle(title)
    }

    fun chronometerCountDown(countsDown: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setChronometerCountDown(countsDown)
        }
    }

    fun customBigContentView(contentView: RemoteViews) {
        builder.setCustomBigContentView(contentView)
    }

    fun customContentView(contentView: RemoteViews) {
        builder.setCustomContentView(contentView)
    }

    fun customHeadsUpContentView(contentView: RemoteViews) {
        builder.setCustomHeadsUpContentView(contentView)
    }

    fun decoratedCustomViewStyle() {
        builder.setStyle(NotificationCompat.DecoratedCustomViewStyle())
    }

    fun defaults(@NotificationDefaults defaults: Int) {
        builder.setDefaults(defaults)
    }

    var extras: Bundle
        get() = builder.extras
        set(value) {
            builder.extras = value
        }

    internal fun extend(extender: NotificationCompat.Extender) {
        builder.extend(extender)
    }

    fun fullScreenIntent(intent: PendingIntent, isHighPriority: Boolean = false) {
        builder.setFullScreenIntent(intent, isHighPriority)
    }

    fun group(groupKey: String) {
        builder.setGroup(groupKey)
    }

    fun groupAlertBehavior(@NotificationCompat.GroupAlertBehavior groupAlertBehavior: Int) {
        builder.setGroupAlertBehavior(groupAlertBehavior)
    }

    fun groupSummary(isGroupSummary: Boolean) {
        builder.setGroupSummary(isGroupSummary)
    }

    fun inboxStyle(body: @NotificationMarker InboxStyle.() -> Unit) {
        val inboxStyle = NotificationCompat.InboxStyle()
        InboxStyle(inboxStyle).body()
        builder.setStyle(inboxStyle)
    }

    fun largeIcon(icon: Bitmap) {
        builder.setLargeIcon(icon)
    }

    fun lights(
        @ColorInt color: Int,
        @IntRange(from = 0) onMs: Int,
        @IntRange(from = 0) offMs: Int
    ) {
        builder.setLights(color, onMs, offMs)
    }

    fun localOnly(localOnly: Boolean) {
        builder.setLocalOnly(localOnly)
    }

    fun messagingStyle(person: Person, body: @NotificationMarker MessagingStyle.() -> Unit) {
        val messagingStyle = NotificationCompat.MessagingStyle(person)
        MessagingStyle(messagingStyle).body()
        builder.setStyle(messagingStyle)
    }

    fun number(number: Int) {
        builder.setNumber(number)
    }

    fun ongoing(ongoing: Boolean) {
        builder.setOngoing(ongoing)
    }

    fun onlyAlertOnce(onlyAlertOnce: Boolean) {
        builder.setOnlyAlertOnce(onlyAlertOnce)
    }

    val persons: Persons
        get() = Persons(builder)

    fun persons(body: @NotificationMarker Persons.() -> Unit) {
        persons.body()
    }

    fun priority(@NotificationPriority priority: Int) {
        builder.priority = priority
    }

    fun progress(
        @IntRange(from = 0) max: Int,
        @IntRange(from = 0) progress: Int,
        indeterminate: Boolean = false
    ) {
        builder.setProgress(max, progress, indeterminate)
    }

    infix fun publicVersion(value: AndroidNotification) {
        builder.setPublicVersion(value)
    }

    fun remoteInputHistory(text: Array<CharSequence>) {
        builder.setRemoteInputHistory(text)
    }

    fun shortcutId(shortcutId: String) {
        builder.setShortcutId(shortcutId)
    }

    fun showWhen(show: Boolean) {
        builder.setShowWhen(show)
    }

    fun smallIcon(@DrawableRes icon: Int) {
        builder.setSmallIcon(icon)
    }

    fun smallIcon(@DrawableRes icon: Int, @IntRange(from = 0) level: Int) {
        builder.setSmallIcon(icon, level)
    }

    fun sortKey(sortKey: String) {
        builder.setSortKey(sortKey)
    }

    fun sound(sound: Uri) {
        builder.setSound(sound)
    }

    fun sound(sound: Uri, @NotificationCompat.StreamType streamType: Int) {
        builder.setSound(sound, streamType)
    }

    fun subText(text: CharSequence) {
        builder.setSubText(text)
    }

    fun ticker(tickerText: CharSequence, views: RemoteViews? = null) {
        builder.setTicker(tickerText, views)
    }

    fun timeoutAfter(@IntRange(from = 0) durationMs: Long) {
        builder.setTimeoutAfter(durationMs)
    }

    fun usesChronometer(value: Boolean) {
        builder.setUsesChronometer(value)
    }

    fun vibrate(pattern: LongArray) {
        builder.setVibrate(pattern)
    }

    fun visibility(@NotificationVisibility value: Int) {
        builder.setVisibility(value)
    }

    fun wearable(body: @NotificationMarker WearableExtender.() -> Unit) {
        val wearableExtender: NotificationCompat.WearableExtender = NotificationCompat.WearableExtender()
        WearableExtender(wearableExtender).body()
        extend(wearableExtender)
    }

    fun whenTime(@IntRange(from = 0) time: Long) {
        builder.setWhen(time)
    }
}

@ExperimentalTime
fun Notification.lights(
    @ColorInt color: Int,
    on: Duration,
    off: Duration
) {
    require(on.isPositive() && on.isFinite()) { "`on` must be greater or equals than zero and finite" }
    require(off.isPositive() && off.isFinite()) { "`off` must be greater or equals than zero and finite" }
    lights(color, on.toInt(DurationUnit.MILLISECONDS), off.toInt(DurationUnit.MILLISECONDS))
}

fun Notification.remoteInputHistory(vararg text: CharSequence) {
    builder.setRemoteInputHistory(text)
}

fun Notification.remoteInputHistory(text: Iterable<CharSequence>) {
    builder.setRemoteInputHistory(text.toArray())
}

@ExperimentalTime
fun Notification.timeoutAfter(duration: Duration) {
    builder.setTimeoutAfter(duration.toLongMilliseconds())
}

fun Notification.vibrate(vararg pattern: Long) {
    builder.setVibrate(pattern)
}

fun Notification.style(style: NotificationCompat.Style) {
    builder.setStyle(style)
}
