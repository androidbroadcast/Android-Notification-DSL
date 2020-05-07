package com.kirich1409.androidnotificationdsl.group.annotations

import com.kirich1409.androidnotificationdsl.group.NotificationsGroupBuilder

/**
 * @see NotificationsGroupBuilder
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
internal annotation class NotificationsGroupMarker

