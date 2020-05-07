package com.kirich1409.androidnotificationdsl.style.bigpicture.annotations

import androidx.annotation.RestrictTo
import com.kirich1409.androidnotificationdsl.style.bigpicture.BigPictureStyle

/**
 * @see BigPictureStyle
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal annotation class NotificationBigPictureStyleMarker
