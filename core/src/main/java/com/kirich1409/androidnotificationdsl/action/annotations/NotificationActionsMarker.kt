package com.kirich1409.androidnotificationdsl.action.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.action.Actions

/**
 * @see Actions
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
annotation class NotificationActionsMarker
