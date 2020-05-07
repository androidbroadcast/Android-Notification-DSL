package com.kirich1409.androidnotificationdsl.car.annotations

import androidx.annotation.RestrictTo

/**
 * @see NotificationCarExtenderUnreadConversationMarker
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationCarExtenderUnreadConversationMarker
