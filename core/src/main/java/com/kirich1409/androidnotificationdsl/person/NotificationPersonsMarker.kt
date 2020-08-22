package com.kirich1409.androidnotificationdsl.person

import androidx.annotation.RestrictTo

/**
 * @see PersonsBuilder
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationPersonsMarker
