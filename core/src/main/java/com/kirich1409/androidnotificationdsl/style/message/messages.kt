@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.style.message

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationMessagesMarker
import java.time.Instant
import java.util.*
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@NotificationMessagesMarker
inline class Messages(private val style: NotificationCompat.MessagingStyle) {

    fun message(text: CharSequence, timestamp: Long, person: Person) {
        style.addMessage(text, timestamp, person)
    }

    fun message(text: CharSequence, timestamp: Long, person: Person, dataMimeType: String, dataUri: Uri) {
        val message = NotificationCompat.MessagingStyle.Message(text, timestamp, person)
        message.setData(dataMimeType, dataUri)
        style.addMessage(message)
    }
}

@Suppress("NOTHING_TO_INLINE")
@ExperimentalTime
inline fun Messages.message(text: CharSequence, timestamp: Duration, person: Person) {
    message(text, timestamp.toLongMilliseconds(), person)
}

@Suppress("NOTHING_TO_INLINE")
@ExperimentalTime
inline fun Messages.message(
    text: CharSequence,
    timestamp: Duration,
    person: Person,
    dataMimeType: String,
    dataUri: Uri
) {
    message(text, timestamp.toLongMilliseconds(), person, dataMimeType, dataUri)
}

@Suppress("NOTHING_TO_INLINE")
inline fun Messages.message(text: CharSequence, timestamp: Date, person: Person) {
    message(text, timestamp.time, person)
}

@Suppress("NOTHING_TO_INLINE")
inline fun Messages.message(text: CharSequence, timestamp: Date, person: Person, dataMimeType: String, dataUri: Uri) {
    message(text, timestamp.time, person, dataMimeType, dataUri)
}

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("NOTHING_TO_INLINE")
inline fun Messages.message(text: CharSequence, timestamp: Instant, person: Person) {
    message(text, timestamp.toEpochMilli(), person)
}

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("NOTHING_TO_INLINE")
inline fun Messages.message(
    text: CharSequence,
    timestamp: Instant,
    person: Person,
    dataMimeType: String,
    dataUri: Uri
) {
    message(text, timestamp.toEpochMilli(), person, dataMimeType, dataUri)
}