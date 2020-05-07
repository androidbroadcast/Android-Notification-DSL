package com.kirich1409.androidnotificationdsl.style.inbox.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.style.inbox.InboxStyle

/**
 * @see InboxStyle.Lines
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationInboxStyleLinesMarker
