package com.kirich1409.androidnotificationdsl.channels

import androidx.annotation.RestrictTo

/**
 * @see NotificationChannels
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationChannelsMarker
