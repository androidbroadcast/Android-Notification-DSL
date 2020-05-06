@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.channels

import android.annotation.TargetApi
import android.app.NotificationManager
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.ColorInt
import com.kirich1409.androidnotificationdsl.annotations.NotificationImportance
import com.kirich1409.androidnotificationdsl.annotations.NotificationVisibility
import android.app.NotificationChannel as AndroidNotificationChannel

@TargetApi(Build.VERSION_CODES.O)
@NotificationChannelMarker
class NotificationChannel @PublishedApi internal constructor(
    @PublishedApi internal val channel: AndroidNotificationChannel
) {

    /**
     * Returns whether notifications posted to this channel can display outside of the notification
     * shade, in a floating window on top of other apps.
     *
     * Has no effect on pre Q (Android 10) devices
     */
    inline var bubbleEnabled: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && channel.canBubble()
        set(value) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                channel.setAllowBubbles(value)
            }
        }

    /**
     * Whether or not notifications posted to this channel can bypass the Do Not Disturb
     * [NotificationManager.INTERRUPTION_FILTER_PRIORITY] mode.
     */
    inline var bypassDndEnabled: Boolean
        get() = channel.canBypassDnd()
        set(value) = channel.setBypassDnd(value)

    /**
     * Returns whether notifications posted to this channel can appear as badges in a Launcher
     * application.
     *
     * Note that badging may be disabled for other reasons.
     */
    inline var showBadgeEnabled: Boolean
        get() = channel.canShowBadge()
        set(value) = channel.setShowBadge(value)

    /**
     * The user visible description of this channel.
     */
    inline var description: String?
        get() = channel.description
        set(value) {
            channel.description = value
        }

    @NotificationImportance
    inline var importance: Int
        get() = channel.importance
        set(value) {
            channel.importance = value
        }

    /**
     * Returns the notification light color for notifications posted to this channel.
     * Irrelevant unless NotificationChannel.lightsEnabled].
     */
    inline var lightColor: Int
        @ColorInt get() = channel.lightColor
        set(@ColorInt value) {
            channel.lightColor = value
        }

    /**
     * Whether or not notifications posted to this channel are shown on the lockscreen in full or redacted form.
     */
    inline var lockscreenVisibility: Int
        @NotificationVisibility get() = channel.lockscreenVisibility
        set(@NotificationVisibility value) {
            channel.lockscreenVisibility = value
        }

    /**
     * Whether notifications posted to this channel trigger notification lights.
     */
    inline var lightsEnabled: Boolean
        get() = channel.shouldShowLights()
        set(value) = channel.enableLights(value)

    /**
     * The vibration pattern for notifications posted to this channel. Will be ignored if
     * vibration is not enabled ([NotificationChannel.vibrationEnabled]).
     */
    inline var vibrationPattern: LongArray?
        get() = channel.vibrationPattern
        set(value) {
            channel.vibrationPattern = value
        }

    /**
     * Whether notifications posted to this channel always vibrate.
     */
    inline var vibrationEnabled: Boolean
        get() = channel.shouldVibrate()
        set(value) = channel.enableVibration(value)

    /**
     * Sets the sound that should be played for notifications posted to this channel and its
     * audio attributes. Notification channels with an [NotificationChannel.importance] of at
     * least [NotificationManager.IMPORTANCE_DEFAULT] should have a sound.
     *
     * Only modifiable before the channel is submitted to [NotificationManager.createNotificationChannel]
     */
    fun sound(sound: Uri, audioAttributes: AudioAttributes) {
        channel.setSound(sound, audioAttributes)
    }
}