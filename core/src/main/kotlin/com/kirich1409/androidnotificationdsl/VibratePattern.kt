package com.kirich1409.androidnotificationdsl

@JvmInline
value class VibratePattern(private val pattern: LongArray) {

    fun asArray(): LongArray = pattern.copyOf()
}
