@file:Suppress("unused", "NOTHING_TO_INLINE")

package com.kirich1409.androidnotificationdsl

import androidx.core.app.RemoteInput
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationDataTypesMarker

@NotificationDataTypesMarker
inline class DataTypes(private val remoteInput: RemoteInput.Builder) {

    /**
     * Specifies whether the user can provide arbitrary values.
     *
     * @param mimeType A mime type that results are allowed to come in.
     *        Be aware that text results (see [RemoteInputs.allowFreeFormInput])
     *        are allowed by default. If you do not want text results you will have to call [disallow]
     *
     * @see disallow
     */
    fun allow(mimeType: String) {
        remoteInput.setAllowDataType(mimeType, true)
    }

    /**
     * Specifies whether the user can provide arbitrary values.
     *
     * @param mimeType A mime type that results are disallowed to come in.
     *         Be aware that text results (see [RemoteInputs.allowFreeFormInput])
     *         are allowed by default. If you want text results you will have to call [allow]
     *
     * @see allow
     */
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