package com.kirich1409.androidnotificationdsl.action.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.action.Action

/**
 * @see Action
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationActionMarker
