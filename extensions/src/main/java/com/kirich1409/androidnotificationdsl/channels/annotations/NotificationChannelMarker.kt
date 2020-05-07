package com.kirich1409.androidnotificationdsl.channels.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.channels.NotificationChannel

/**
 * @see NotificationChannel
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationChannelMarker
