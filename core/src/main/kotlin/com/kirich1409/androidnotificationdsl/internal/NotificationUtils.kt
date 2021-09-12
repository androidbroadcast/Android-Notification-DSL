@file:JvmName("NotificationUtils")

package com.kirich1409.androidnotificationdsl.internal

import android.os.Build
import androidx.annotation.RestrictTo

/**
 * Check is invisible actions are supported on current device
 */
@PublishedApi
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun isInvisibleActionsSupported(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
}
