@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl

import androidx.annotation.DrawableRes
import androidx.annotation.IntRange

data class NotificationIcon internal constructor(
    @DrawableRes internal val icon: Int,
    internal val level: Int = 0
) {

    internal companion object {

        internal val NULL = NotificationIcon(0, -1)
    }
}

fun Int.toNotificationIcon(): NotificationIcon {
    return NotificationIcon(this)
}

/**
 * A variant of [smallIcon][this] that takes an additional level parameter for when the icon is a
 * [LevelListDrawable][android.graphics.drawable.LevelListDrawable].
 *
 * @receiver A resource ID in the application's package of the drawable to use.
 * @param level The level to use for the icon.
 *
 * @see android.graphics.drawable.LevelListDrawable
 */
fun Int.toNotificationIcon(@IntRange(from = 0) level: Int = 0): NotificationIcon {
    require(level >= 0) { "level must be >= 0" }
    return NotificationIcon(this, level)
}
