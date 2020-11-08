package com.kirich1409.androidnotificationdsl.expandable

import androidx.annotation.RestrictTo

/**
 * @see BigPictureNotificationBuilder
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class BigTextNotificationBuilderMarker
