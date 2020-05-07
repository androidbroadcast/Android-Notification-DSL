package com.kirich1409.androidnotificationdsl.style.message.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.style.message.MessagingStyle

/**
 * @see MessagingStyle
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationMessagingStyleMarker
