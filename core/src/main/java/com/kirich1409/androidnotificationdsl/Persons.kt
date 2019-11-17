@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationPersonsMarker

@NotificationPersonsMarker
inline class Persons(private val notificationBuilder: NotificationCompat.Builder) {

    fun person(uri: String) {
        notificationBuilder.addPerson(uri)
    }

    operator fun plus(personUri: String) {
        notificationBuilder.addPerson(personUri)
    }
}