package com.kirich1409.androidnotificationdsl.internal

/**
 * Convert [Iterable] to [Array]
 */
@Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST")
internal inline fun <reified T> Iterable<T>.toArray(): Array<T> {
    return when (this) {
        is List<T> -> toTypedArray<T>()
        is Array<*> -> this as Array<T>
        else -> toList().toTypedArray()
    }
}