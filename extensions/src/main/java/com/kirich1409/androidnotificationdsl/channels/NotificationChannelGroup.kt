@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.channels

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.annotations.NotificationImportance
import android.app.NotificationChannel as AndroidNotificationChannel

@NotificationChannelGroupMarker
@TargetApi(Build.VERSION_CODES.O)
class NotificationChannelGroup @PublishedApi internal constructor(
    internal val channels: MutableList<AndroidNotificationChannel>
) {

    /**
     * Create a channel and add into the group
     */
    @SuppressLint("WrongConstant")
    fun channel(
        id: String,
        name: CharSequence,
        @NotificationImportance importance: Int = NotificationManagerCompat.IMPORTANCE_DEFAULT,
        build: @NotificationChannelGroupMarker NotificationChannel.() -> Unit
    ) {
        channels += AndroidNotificationChannel(id, name, importance).also { NotificationChannel(it).build() }
    }

    /**
     * Create a channel and add into the group
     */
    @SuppressLint("WrongConstant")
    fun channel(
        id: String,
        name: CharSequence,
        @NotificationImportance importance: Int = NotificationManagerCompat.IMPORTANCE_DEFAULT
    ) {
        channels += AndroidNotificationChannel(id, name, importance)
    }

    /**
     * Add the channel into the group
     */
    fun channel(channel: AndroidNotificationChannel) {
        @SuppressLint("WrongConstant")
        channels += channel
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