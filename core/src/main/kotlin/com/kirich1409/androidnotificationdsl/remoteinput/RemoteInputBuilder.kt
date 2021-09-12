@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.remoteinput

import android.os.Bundle
import androidx.core.app.RemoteInput.EditChoicesBeforeSending
import com.kirich1409.androidnotificationdsl.DataTypesBuilder
import androidx.core.app.RemoteInput as AndroidRemoteInput

/**
 * DSL Builder of [RemoteInputBuilder]
 */
@NotificationRemoteInputMarker
class RemoteInputBuilder @PublishedApi internal constructor(
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
    inline val dataTypes: DataTypesBuilder
        get() = DataTypesBuilder(remoteInput)

    /**
     * Specifies whether the user can provide arbitrary values
     */
    inline fun dataTypes(body: DataTypesBuilder.() -> Unit) {
        dataTypes.body()
    }

    /**
     * Specifies whether the user can provide arbitrary text values. The default is `true`.
     * If you specify `false`, you must either provide a non-null  and non-empty array
     * to [RemoteInput.choices], or enable a data result in [DataTypes.allow].
     * Otherwise an [IllegalArgumentException] is thrown
     *
     * @see AndroidRemoteInput.Builder.setAllowFreeFormInput
     */
    var allowFreeFormInput: Boolean = true
        set(allowFreeFormTextInput) {
            field = allowFreeFormTextInput
            remoteInput.setAllowFreeFormInput(allowFreeFormTextInput)
        }

    /**
     * Specifies choices available to the user to satisfy this input. You must provide a non-empty array if
     * you disabled free form input using [RemoteInput.allowFreeFormInput]
     *
     * Note: Starting in Android P, these choices will always be shown on phones if the app's
     * target SDK is >= P. However, these choices may also be rendered on other types of devices
     * regardless of target SDK.
     *
     */
    var choices: Array<CharSequence>? = null
        set(choices) {
            remoteInput.setChoices(choices)
        }

    /**
     * Specifies whether tapping on a choice should let the user edit the input before it is
     * sent to the app. The default is [AndroidRemoteInput.EDIT_CHOICES_BEFORE_SENDING_AUTO].
     *
     * It cannot be used if [RemoteInputBuilder.allowFreeFormInput] has been set to false.
     */
    @EditChoicesBeforeSending
    var editChoicesBeforeSending: Int =
        androidx.core.app.RemoteInput.EDIT_CHOICES_BEFORE_SENDING_AUTO
        set(editChoicesBeforeSending) {
            field = editChoicesBeforeSending
            remoteInput.setEditChoicesBeforeSending(editChoicesBeforeSending)
        }

    /**
     * Set a label to be displayed to the user when collecting this input.
     */
    var label: CharSequence? = null
        set(label) {
            field = label
            remoteInput.setLabel(label)
        }
}
