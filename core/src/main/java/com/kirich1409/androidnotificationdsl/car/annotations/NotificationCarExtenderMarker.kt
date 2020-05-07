package com.kirich1409.androidnotificationdsl.car.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.car.CarExtender

/**
 * @see CarExtender
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationCarExtenderMarker
