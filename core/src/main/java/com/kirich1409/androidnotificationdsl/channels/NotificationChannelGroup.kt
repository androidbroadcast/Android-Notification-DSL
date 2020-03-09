@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.channels

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.annotations.NotificationImportance
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationChannelGroupMarker
import android.app.NotificationChannel as AndroidNotificationChannel

@NotificationChannelGroupMarker
@RequiresApi(Build.VERSION_CODES.O)
inline class NotificationChannelGroup(@PublishedApi internal val channels: MutableList<AndroidNotificationChannel>) {

    inline fun channel(
        id: String,
        name: CharSequence,
        @NotificationImportance importance: Int = NotificationManagerCompat.IMPORTANCE_DEFAULT,
        build: @NotificationChannelGroupMarker NotificationChannel.() -> Unit
    ) {
        @SuppressLint("WrongConstant")
        channels += AndroidNotificationChannel(id, name, importance).also { NotificationChannel(it).build() }
    }

    inline fun channel(
        id: String,
        name: CharSequence,
        @NotificationImportance importance: Int = NotificationManagerCompat.IMPORTANCE_DEFAULT
    ) {
        @SuppressLint("WrongConstant")
        channels += AndroidNotificationChannel(id, name, importance)
    }
}