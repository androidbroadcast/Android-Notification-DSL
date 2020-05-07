package com.kirich1409.androidnotificationdsl.channels.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.channels.NotificationChannels

/**
 * @see NotificationChannels
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationChannelsMarker
