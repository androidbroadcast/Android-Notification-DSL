package com.kirich1409.androidnotificationdsl.car

import androidx.annotation.RestrictTo

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationCarExtenderUnreadConversationMarker