@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.kirich1409.androidnotificationdsl.channels

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.annotations.NotificationImportance
import com.kirich1409.androidnotificationdsl.channels.annotations.NotificationChannelsMarker
import android.app.NotificationChannel as AndroidNotificationChannel
import android.app.NotificationChannelGroup as AndroidNotificationChannelGroup

/**
 * Create notification channels and group. Will be called only on [Build.VERSION_CODES.O] and greater
 */
inline fun createNotificationChannels(context: Context, build: NotificationChannels.() -> Unit) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        // Notification Channels aren't supported before Android 8.0 Oreo
        return
    }

    val channels = NotificationChannels().apply(build)

    NotificationManagerCompat.from(context).apply {
        createNotificationChannels(channels.channels)
        channels.groups.forEach { group -> createNotificationChannels(group.channels) }
        createNotificationChannelGroups(channels.groups)
    }
}

@TargetApi(Build.VERSION_CODES.O)
@NotificationChannelsMarker
@Suppress("UndocumentedPublicClass")
class NotificationChannels @PublishedApi internal constructor(
    @PublishedApi internal val groups: MutableList<AndroidNotificationChannelGroup> = mutableListOf(),
    @PublishedApi internal val channels: MutableList<AndroidNotificationChannel> = mutableListOf()
) {
    /**
     * Add notification channel
     *
     * It doesn't do anything on older SDKs which doesn't support Notification Channels.
     *
     * @param id The id of the channel. Must be unique per package. The value may be truncated if it is too long.
     * @param name The user visible name of the channel. You can rename this channel when the system
     *             locale changes by listening for the
     *             [Intent.ACTION_LOCALE_CHANGED][android.content.Intent.ACTION_LOCALE_CHANGED] broadcast.
     *             The recommended maximum length is 40 characters; the value may be truncated if it is too long.
     * @param importance The importance of the channel. This controls how interruptive notifications
     *                   posted to this channel are.
     */
    fun channel(
        id: String,
        name: CharSequence,
        @NotificationImportance importance: Int = NotificationManagerCompat.IMPORTANCE_DEFAULT
    ) {
        @SuppressLint("WrongConstant")
        channels += AndroidNotificationChannel(id, name, importance)
    }

    /**
     * Add notification channel
     *
     * It doesn't do anything on older SDKs which doesn't support Notification Channels.
     *
     * @param id The id of the channel. Must be unique per package. The value may be truncated if it is too long.
     * @param name The user visible name of the channel. You can rename this channel when the system locale changes
     *             by listening for the [Intent.ACTION_LOCALE_CHANGED][android.content.Intent.ACTION_LOCALE_CHANGED]
     *             broadcast.
     *             The recommended maximum length is 40 characters; the value may be truncated if it is too long.
     * @param importance The importance of the channel.
 *                       This controls how interruptive notifications posted to this channel are.
     */
    inline fun channel(
        id: String,
        name: CharSequence,
        @NotificationImportance importance: Int = NotificationManagerCompat.IMPORTANCE_DEFAULT,
        build: @NotificationChannelsMarker NotificationChannel.() -> Unit
    ) {
        @SuppressLint("WrongConstant")
        val channel = AndroidNotificationChannel(id, name, importance)
        NotificationChannel(channel).build()
        channels += channel
    }

    /**
     * Add notification channel group.
     *
     * @param id The id of the group. Must be unique per package. The value may be truncated if it is too long.
     * @param name The user visible name of the group. You can rename this group when the system locale changes
     *             by listening for the [Intent.ACTION_LOCALE_CHANGED][android.content.Intent.ACTION_LOCALE_CHANGED]
     *             broadcast.
     *             The recommended maximum length is 40 characters; the value may be truncated if it is too long.
     */
    fun group(id: String, name: CharSequence) {
        groups += AndroidNotificationChannelGroup(id, name)
    }

    /**
     * Add notification channel group.
     *
     * @param id The id of the group. Must be unique per package. The value may be truncated if it is too long.
     * @param name The user visible name of the group. You can rename this group when the system locale changes
     *             by listening for the [Intent.ACTION_LOCALE_CHANGED][android.content.Intent.ACTION_LOCALE_CHANGED]
     *             broadcast.
     *             The recommended maximum length is 40 characters; the value may be truncated if it is too long.
     */
    inline fun group(
        id: String,
        name: CharSequence,
        build: @NotificationChannelsMarker NotificationChannelGroup.() -> Unit
    ) {
        val group = AndroidNotificationChannelGroup(id, name)
        NotificationChannelGroup(group.channels).build()
        groups += group
    }
}

