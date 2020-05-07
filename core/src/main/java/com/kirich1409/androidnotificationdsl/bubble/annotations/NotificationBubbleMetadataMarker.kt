package com.kirich1409.androidnotificationdsl.bubble.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.bubble.BubbleMetadata

/**
 * @see BubbleMetadata
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationBubbleMetadataMarker
