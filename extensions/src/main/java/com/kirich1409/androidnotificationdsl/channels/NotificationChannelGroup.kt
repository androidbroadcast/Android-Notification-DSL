@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.channels

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import androidx.annotation.RequiresApi
import com.kirich1409.androidnotificationdsl.Container
import com.kirich1409.androidnotificationdsl.NotificationImportance
import android.app.NotificationChannel as AndroidNotificationChannel

@NotificationChannelGroupMarker
@TargetApi(Build.VERSION_CODES.O)
@Suppress("UndocumentedPublicClass")
class NotificationChannelGroup @PublishedApi internal constructor(
    internal val channels: MutableList<AndroidNotificationChannel>
) : Container<AndroidNotificationChannel> {

    /**
     * Create a channel and add into the group
     */
    @SuppressLint("WrongConstant")
    fun channel(
        id: String,
        name: CharSequence,
        importance: NotificationImportance = NotificationImportance.DEFAULT,
        build: @NotificationChannelGroupMarker NotificationChannel.() -> Unit
    ) {
        channels += AndroidNotificationChannel(id, name, importance.intValue).also { NotificationChannel(it).build() }
    }

    /**
     * Create a channel and add into the group
     */
    @SuppressLint("WrongConstant")
    fun channel(
        id: String,
        name: CharSequence,
        importance: NotificationImportance = NotificationImportance.DEFAULT
    ) {
        channels += AndroidNotificationChannel(id, name, importance.intValue)
    }

    /**
     * Add the channel into the group
     */
    fun channel(channel: AndroidNotificationChannel) {
        channels += channel
    }

    override fun plusAssign(item: android.app.NotificationChannel) {
        channel(item)
    }
}

/**
 * Add the channel into the group
 */
@RequiresApi(Build.VERSION_CODES.O)
operator fun NotificationChannelGroup.plus(channel: AndroidNotificationChannel) {
    this.channels += channel
}

/**
 * Add the channels into the group
 */
@RequiresApi(Build.VERSION_CODES.O)
operator fun NotificationChannelGroup.plus(channels: Iterable<AndroidNotificationChannel>) {
    this.channels += channels
}
