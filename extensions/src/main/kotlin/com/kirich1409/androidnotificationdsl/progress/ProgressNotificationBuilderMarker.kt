package com.kirich1409.androidnotificationdsl.progress

import androidx.annotation.RestrictTo

/**
 * @see ProgressNotificationBuilder
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class ProgressNotificationBuilderMarker
