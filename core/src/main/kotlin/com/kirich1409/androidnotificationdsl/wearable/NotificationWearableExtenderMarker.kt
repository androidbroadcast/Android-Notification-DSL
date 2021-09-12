package com.kirich1409.androidnotificationdsl.wearable

import androidx.annotation.RestrictTo

/**
 * @see WearableExtender
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationWearableExtenderMarker
