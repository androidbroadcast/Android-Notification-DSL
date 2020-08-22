package com.kirich1409.androidnotificationdsl.action.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.action.ActionsBuilder

/**
 * @see ActionsBuilder
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationActionsMarker
