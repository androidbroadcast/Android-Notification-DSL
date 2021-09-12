package com.kirich1409.androidnotificationdsl.action

import androidx.annotation.RestrictTo

/**
 * @see ActionBuilder
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationActionMarker
