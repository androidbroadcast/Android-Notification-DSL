@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.channels

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.ColorInt
import com.kirich1409.androidnotificationdsl.annotations.NotificationVisibility
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationChannelMarker

@TargetApi(Build.VERSION_CODES.O)
@NotificationChannelMarker
inline class NotificationChannel(private val channel: NotificationChannel) {

    var bubbleEnabled: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && channel.canBubble()
        set(value) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                channel.setAllowBubbles(value)
            }
        }

    var bypassDndEnabled: Boolean
        get() = channel.canBypassDnd()
        set(value) = channel.setBypassDnd(value)

    var showBadgeEnabled: Boolean
        get() = channel.canShowBadge()
        set(value) = channel.setShowBadge(value)

    var description: String?
        get() = channel.description
        set(value) {
            channel.description = value
        }

    var lightColor: Int
        @ColorInt get() = channel.lightColor
        set(@ColorInt value) {
            channel.lightColor = value
        }

    var lockscreenVisibility: Int
        @NotificationVisibility get() = channel.lockscreenVisibility
        set(@NotificationVisibility value) {
            channel.lockscreenVisibility = value
        }

    var lightsEnabled: Boolean
        get() = channel.shouldShowLights()
        set(value) = channel.enableLights(value)

    fun sound(sound: Uri, audioAttributes: AudioAttributes) {
        channel.setSound(sound, audioAttributes)
    }

    var vibrationPattern: LongArray?
        get() = channel.vibrationPattern
        set(value) {
            channel.vibrationPattern = value
        }

    var vibrationEnabled: Boolean
        get() = channel.shouldVibrate()
        set(value) = channel.enableVibration(value)
}