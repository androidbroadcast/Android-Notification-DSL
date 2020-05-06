@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.remoteinput

import android.os.Bundle
import androidx.core.app.RemoteInput.EditChoicesBeforeSending
import com.kirich1409.androidnotificationdsl.DataTypes
import androidx.core.app.RemoteInput as AndroidRemoteInput

@NotificationRemoteInputMarker
class RemoteInput @PublishedApi internal constructor(
    @PublishedApi internal val remoteInput: AndroidRemoteInput.Builder
) {

    /**
     * The metadata Bundle used by this builder.
     */
    inline val extras: Bundle
        get() = remoteInput.extras

    /**
     * Specifies whether the user can provide arbitrary values.
     *
     * @see AndroidRemoteInput.Builder.setAllowDataType
     */
    inline val dataTypes: DataTypes
        get() = DataTypes(remoteInput)

    /**
     * Specifies whether the user can provide arbitrary text values.
     *
     * @param allowFreeFormTextInput The default is `true`. If you specify `false`, you must either provide a non-null
     *         and non-empty array to [RemoteInput.choices], or enable a data result
     *         in [DataTypes.allow]. Otherwise an [IllegalArgumentException] is thrown
     *
     * @see AndroidRemoteInput.Builder.setAllowFreeFormInput
     */
    fun allowFreeFormInput(allowFreeFormTextInput: Boolean) {
        remoteInput.setAllowFreeFormInput(allowFreeFormTextInput)
    }

    /**
     * Specifies choices available to the user to satisfy this input.
     *
     * Note: Starting in Android P, these choices will always be shown on phones if the app's
     * target SDK is >= P. However, these choices may also be rendered on other types of devices
     * regardless of target SDK.
     *
     * @param choices an array of pre-defined choices for users input.
     *        You must provide a non-empty array if
     *        you disabled free form input using [RemoteInput.allowFreeFormInput]
     */
    fun choices(choices: Array<CharSequence>?) {
        remoteInput.setChoices(choices)
    }

    inline fun dataTypes(body: DataTypes.() -> Unit) {
        dataTypes.body()
    }

    /**
     * Specifies whether tapping on a choice should let the user edit the input before it is
     * sent to the app. The default is [AndroidRemoteInput.EDIT_CHOICES_BEFORE_SENDING_AUTO].
     *
     * It cannot be used if [RemoteInput.allowFreeFormInput] has been set to false.
     */
    fun editChoicesBeforeSending(@EditChoicesBeforeSending editChoicesBeforeSending: Int) {
        remoteInput.setEditChoicesBeforeSending(editChoicesBeforeSending)
    }

    /**
     * Set a label to be displayed to the user when collecting this input.
     *
     * @param label The label to show to users when they input a response
     */
    fun label(label: CharSequence?) {
        remoteInput.setLabel(label)
    }
}