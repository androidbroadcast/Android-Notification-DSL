package com.kirich1409.androidnotificationdsl

import androidx.annotation.IntRange

data class NotificationProgress internal constructor(
    @IntRange(from = 0) val progress: Int,
    @IntRange(from = 0) val max: Int,
    val indeterminate: Boolean = false
) {

    companion object {

        internal val NULL = NotificationProgress(0, 0, false)
    }
}

fun indeterminateProgress(): NotificationProgress {
    return NotificationProgress(1, 1, indeterminate = true)
}

fun progress(
    @IntRange(from = 0) progress: Int,
    @IntRange(from = 0) max: Int
): NotificationProgress {
    require(progress >= 0)
    require(max >= 0)
    require(max >= progress)
    return NotificationProgress(progress, max, indeterminate = false)
}
