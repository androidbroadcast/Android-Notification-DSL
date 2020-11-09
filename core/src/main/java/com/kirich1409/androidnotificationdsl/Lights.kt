package com.kirich1409.androidnotificationdsl

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

data class Lights @PublishedApi internal constructor(
    @ColorInt val color: Int,
    @IntRange(from = 0) val onMs: Int,
    @IntRange(from = 0) val offMs: Int
) {

    companion object {

        internal val NULL = Lights(color = Color.TRANSPARENT, onMs = 0, offMs = 0)
    }
}

fun lights(
    @ColorInt color: Int,
    @IntRange(from = 0) onMs: Int,
    @IntRange(from = 0) offMs: Int
): Lights {
    require(onMs >= 0)
    require(offMs >= 0)
    return Lights(color, onMs, offMs)
}

@ExperimentalTime
fun lights(@ColorInt color: Int, on: Duration, off: Duration): Lights {
    require(on.isPositive() && on.isFinite()) { "`on` must be greater or equals than zero and finite" }
    require(off.isPositive() && off.isFinite()) { "`off` must be greater or equals than zero and finite" }
    return Lights(color, on.toInt(DurationUnit.MILLISECONDS), off.toInt(DurationUnit.MILLISECONDS))
}
