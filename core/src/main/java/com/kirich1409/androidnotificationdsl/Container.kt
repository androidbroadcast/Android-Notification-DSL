package com.kirich1409.androidnotificationdsl

interface Container<in T> {

    operator fun plusAssign(item: T)
}

interface ListContainer<T> : Container<T>, List<T>

fun <T> Container<T>.add(iterable: Iterable<T>) {
    iterable.forEach(this::plusAssign)
}

fun <T> Container<T>.add(iterator: Iterator<T>) {
    iterator.forEach(this::plusAssign)
}
