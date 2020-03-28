package com.kirich1409.androidnotificationdsl.action

import androidx.annotation.RestrictTo

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationActionsMarker