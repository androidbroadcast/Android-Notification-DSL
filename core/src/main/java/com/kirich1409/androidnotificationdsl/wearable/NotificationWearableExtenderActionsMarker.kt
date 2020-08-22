package com.kirich1409.androidnotificationdsl.wearable

import androidx.annotation.RestrictTo

/**
 * @see WearableExtenderActions
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationWearableExtenderActionsMarker
