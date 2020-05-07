package com.kirich1409.androidnotificationdsl.wearable.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.wearable.WearableExtenderActions

/**
 * @see WearableExtenderActions
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationWearableExtenderActionsMarker
