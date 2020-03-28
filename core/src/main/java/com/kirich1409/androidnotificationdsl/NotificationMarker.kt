package com.kirich1409.androidnotificationdsl

import androidx.annotation.RestrictTo

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
annotation class NotificationMarker