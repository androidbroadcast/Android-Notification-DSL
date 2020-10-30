package com.kirich1409.androidnotificationdsl

inline class VibratePattern(private val pattern: LongArray) {

    fun asArray(): LongArray = pattern.copyOf()
}

fun LongArray.asVibratePattern() = VibratePattern(copyOf())