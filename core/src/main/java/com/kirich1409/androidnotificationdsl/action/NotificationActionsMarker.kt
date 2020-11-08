package com.kirich1409.androidnotificationdsl.action

import androidx.annotation.RestrictTo

/**
 * @see Actions
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
annotation class NotificationActionsMarker
