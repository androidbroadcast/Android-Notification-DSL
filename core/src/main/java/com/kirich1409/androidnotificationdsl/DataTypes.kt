@file:Suppress("unused", "NOTHING_TO_INLINE")

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

inline fun DataTypes.allow(vararg mimeTypes: String) {
    mimeTypes.forEach(::allow)
}

inline fun DataTypes.disallow(vararg mimeTypes: String) {
    mimeTypes.forEach(::disallow)
}

inline fun DataTypes.allow(mimeTypes: Iterable<String>) {
    mimeTypes.forEach(::allow)
}

inline fun DataTypes.disallow(mimeTypes: Iterable<String>) {
    mimeTypes.forEach(::disallow)
}

inline operator fun DataTypes.minus(mimeType: String) {
    disallow(mimeType)
}

inline operator fun DataTypes.plus(mimeType: String) {
    allow(mimeType)
}