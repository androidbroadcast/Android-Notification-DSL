@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.kirich1409.androidnotificationdsl.channels

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.annotations.NotificationImportance
import android.app.NotificationChannel as AndroidNotificationChannel
import android.app.NotificationChannelGroup as AndroidNotificationChannelGroup

inline fun createChannelsAndGroups(context: Context, build: NotificationChannels.() -> Unit) {
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
class NotificationChannels @PublishedApi internal constructor(
    @PublishedApi internal val groups: MutableList<AndroidNotificationChannelGroup> = mutableListOf(),
    @PublishedApi internal val channels: MutableList<AndroidNotificationChannel> = mutableListOf()
) {

    fun channel(
        id: String,
        name: CharSequence,
        @NotificationImportance importance: Int = NotificationManagerCompat.IMPORTANCE_DEFAULT
    ) {
        @SuppressLint("WrongConstant")
        channels += AndroidNotificationChannel(id, name, importance)
    }

    fun channel(
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

    fun group(id: String, name: CharSequence) {
        groups += AndroidNotificationChannelGroup(id, name)
    }

    fun group(
        id: String,
        name: CharSequence,
        build: @NotificationChannelsMarker NotificationChannelGroup.() -> Unit
    ) {
        val group = AndroidNotificationChannelGroup(id, name)
        NotificationChannelGroup(group.channels).build()
        groups += group
    }
}

