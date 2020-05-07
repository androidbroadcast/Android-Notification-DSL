package com.kirich1409.androidnotificationdsl.person.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.person.Persons

/**
 * @see Persons
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationPersonsMarker
