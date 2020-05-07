package com.kirich1409.androidnotificationdsl.wearable.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.wearable.WearableActionExtender

/**
 * @see WearableActionExtender
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationWearableActionExtenderMarker