package com.kirich1409.androidnotificationdsl.expandable.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.expandable.BigPictureNotificationBuilder

/**
 * @see BigPictureNotificationBuilder
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class BigTextNotificationBuilderMarker
