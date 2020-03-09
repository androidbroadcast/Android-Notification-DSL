@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationPersonsMarker

@NotificationPersonsMarker
inline class Persons(private val notification: NotificationCompat.Builder) {

    fun person(uri: String) {
        notification.addPerson(uri)
    }

    operator fun plus(personUri: String) {
        notification.addPerson(personUri)
    }
}