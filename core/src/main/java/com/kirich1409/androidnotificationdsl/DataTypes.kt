@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import androidx.core.app.RemoteInput
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationDataTypesMarker

@NotificationDataTypesMarker
inline class DataTypes(private val remoteInput: RemoteInput.Builder) {

    fun allow(mimeType: String) {
        remoteInput.setAllowDataType(mimeType, true)
    }

    fun disallow(mimeType: String) {
        remoteInput.setAllowDataType(mimeType, false)
    }
}

fun DataTypes.allow(vararg mimeTypes: String) {
    mimeTypes.forEach(::allow)
}

fun DataTypes.disallow(vararg mimeTypes: String) {
    mimeTypes.forEach(::disallow)
}

fun DataTypes.allow(mimeTypes: Iterable<String>) {
    mimeTypes.forEach(::allow)
}

fun DataTypes.disallow(mimeTypes: Iterable<String>) {
    mimeTypes.forEach(::disallow)
}

operator fun DataTypes.minus(mimeType: String) {
    disallow(mimeType)
}

operator fun DataTypes.plus(mimeType: String) {
    allow(mimeType)
}