package com.kirich1409.androidnotificationdsl

import android.annotation.SuppressLint
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

data class Sound internal constructor(val sound: Uri, val streamType: StreamType = StreamType.DEFAULT)

fun sound(sound: Uri, streamType: StreamType) = Sound(sound, streamType)

fun Uri.toSound() = Sound(this)

enum class StreamType(val streamTypeInt: Int) {
    DEFAULT(NotificationCompat.STREAM_DEFAULT),
    VOICE_CALL(AudioManager.STREAM_VOICE_CALL),
    SYSTEM(AudioManager.STREAM_SYSTEM),
    RING(AudioManager.STREAM_RING),
    MUSIC(AudioManager.STREAM_MUSIC),
    ALARM(AudioManager.STREAM_ALARM),
    NOTIFICATION(AudioManager.STREAM_NOTIFICATION),
    DTMF(AudioManager.STREAM_DTMF),

    @RequiresApi(Build.VERSION_CODES.O)
    ACCESSIBILITY(AudioManager.STREAM_ACCESSIBILITY),
    ;

    companion object {

        @SuppressLint("NewApi")
        fun from(streamTypeInt: Int): StreamType = when (streamTypeInt) {
            NotificationCompat.STREAM_DEFAULT -> DEFAULT
            AudioManager.STREAM_VOICE_CALL -> VOICE_CALL
            AudioManager.STREAM_SYSTEM -> SYSTEM
            AudioManager.STREAM_RING -> RING
            AudioManager.STREAM_MUSIC -> MUSIC
            AudioManager.STREAM_ALARM -> ALARM
            AudioManager.STREAM_NOTIFICATION -> NOTIFICATION
            AudioManager.STREAM_DTMF -> DTMF
            AudioManager.STREAM_ACCESSIBILITY -> ACCESSIBILITY
            else -> throw IllegalArgumentException("Unknown stream type for $streamTypeInt")
        }
    }
}
