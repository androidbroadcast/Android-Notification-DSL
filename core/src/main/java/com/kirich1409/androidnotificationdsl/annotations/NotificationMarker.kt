package com.kirich1409.androidnotificationdsl.annotations

import androidx.annotation.RestrictTo

@Suppress("UndocumentedPublicClass")
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
annotation class NotificationMarker
