@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import android.media.AudioManager
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

data class Sound internal constructor(val sound: Uri, val streamType: StreamType = StreamType.DEFAULT)

fun sound(sound: Uri, streamType: StreamType) = Sound(sound, streamType)

fun Uri.toSound() = Sound(this)

enum class StreamType(val streamTypeInt: Int) {

    /**
     * Use this constant as the value for audioStreamType to request that
     * the default stream type for notifications be used.  Currently the
     * default stream type is [AudioManager.STREAM_NOTIFICATION].
     */
    DEFAULT(NotificationCompat.STREAM_DEFAULT),

    /** Used to identify the volume of audio streams for phone calls */
    VOICE_CALL(AudioManager.STREAM_VOICE_CALL),

    /** Used to identify the volume of audio streams for system sounds */
    SYSTEM(AudioManager.STREAM_SYSTEM),

    /** Used to identify the volume of audio streams for the phone ring */
    RING(AudioManager.STREAM_RING),

    /** Used to identify the volume of audio streams for music playback */
    MUSIC(AudioManager.STREAM_MUSIC),

    /** Used to identify the volume of audio streams for alarms */
    ALARM(AudioManager.STREAM_ALARM),

    /** Used to identify the volume of audio streams for notifications */
    NOTIFICATION(AudioManager.STREAM_NOTIFICATION),

    /** Used to identify the volume of audio streams for DTMF Tones */
    DTMF(AudioManager.STREAM_DTMF),

    @RequiresApi(Build.VERSION_CODES.O)
    /** Used to identify the volume of audio streams for accessibility prompts */
    ACCESSIBILITY(AudioManager.STREAM_ACCESSIBILITY),
    ;

    companion object {

        fun fromInt(intValue: Int): StreamType {
            return values().find { it.streamTypeInt == intValue }
                ?: throw IllegalArgumentException("Unknown stream type for $intValue")
        }
    }
}
