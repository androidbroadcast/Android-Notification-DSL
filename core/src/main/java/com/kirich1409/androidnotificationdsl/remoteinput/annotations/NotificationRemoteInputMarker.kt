package com.kirich1409.androidnotificationdsl.remoteinput.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.remoteinput.RemoteInput

/**
 * @see RemoteInput
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationRemoteInputMarker
