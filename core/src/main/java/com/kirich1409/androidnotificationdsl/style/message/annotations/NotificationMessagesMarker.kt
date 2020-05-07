package com.kirich1409.androidnotificationdsl.style.message.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.style.message.Messages

/**
 * @see Messages
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationMessagesMarker
