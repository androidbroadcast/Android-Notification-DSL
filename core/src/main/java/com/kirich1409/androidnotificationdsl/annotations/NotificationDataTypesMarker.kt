package com.kirich1409.androidnotificationdsl.annotations

import androidx.annotation.RestrictTo

@DslMarker
@Target(AnnotationTarget.CLASS)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationDataTypesMarker
