package com.kirich1409.androidnotificationdsl.progress

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.NotificationDsl

/**
 * @see ProgressNotificationBuilder
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@NotificationDsl
internal annotation class ProgressNotificationBuilderMarker
