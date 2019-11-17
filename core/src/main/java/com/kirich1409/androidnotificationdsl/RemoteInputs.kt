@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl

import android.os.Bundle
import androidx.core.app.RemoteInput.EditChoicesBeforeSending
import com.kirich1409.androidnotificationdsl.internal.dsl.NotificationRemoteInputMarker
import androidx.core.app.RemoteInput as AndroidRemoteInput

@NotificationRemoteInputMarker
inline class RemoteInput(private val remoteInput: AndroidRemoteInput.Builder) {

    fun allowFreeFormInput(allowFreeFormTextInput: Boolean) {
        remoteInput.setAllowFreeFormInput(allowFreeFormTextInput)
    }

    val extras: Bundle
        get() = remoteInput.extras

    fun choices(choices: Array<CharSequence>?) {
        remoteInput.setChoices(choices)
    }

    val dataTypes: DataTypes
        get() = DataTypes(remoteInput)

    fun dataTypes(body: DataTypes.() -> Unit) {
        dataTypes.body()
    }

    fun editChoicesBeforeSending(@EditChoicesBeforeSending editChoicesBeforeSending: Int) {
        remoteInput.setEditChoicesBeforeSending(editChoicesBeforeSending)
    }

    fun label(label: CharSequence?) {
        remoteInput.setLabel(label)
    }
}

@Suppress("FunctionName")
internal fun RemoteInput(resultKey: String, body: RemoteInput.() -> Unit): AndroidRemoteInput {
    val builder = AndroidRemoteInput.Builder(resultKey)
    RemoteInput(builder).body()
    return builder.build()
}