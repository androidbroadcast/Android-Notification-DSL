package com.kirich1409.androidnotificationdsl.style.inbox.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.style.inbox.InboxStyle

/**
 * @see InboxStyle
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationInboxStyleMarker
