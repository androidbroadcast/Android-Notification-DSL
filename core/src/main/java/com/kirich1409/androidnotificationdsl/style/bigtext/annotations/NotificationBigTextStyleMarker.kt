package com.kirich1409.androidnotificationdsl.style.bigtext.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.style.bigtext.BigTextStyle

/**
 * @see BigTextStyle
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationBigTextStyleMarker
