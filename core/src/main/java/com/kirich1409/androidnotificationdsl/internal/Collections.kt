@file:JvmMultifileClass

package com.kirich1409.androidnotificationdsl.internal

import androidx.annotation.RestrictTo
import androidx.collection.SparseArrayCompat

/**
 * Convert [Iterable] to [Array]
 */
@PublishedApi
@Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST")
internal inline fun <reified T> Iterable<T>.toArray(): Array<T> {
    return when (this) {
        is List<T> -> toTypedArray<T>()
        is Array<*> -> this as Array<T>
        else -> toList().toTypedArray()
    }
}

@PublishedApi
internal inline fun <E> SparseArrayCompat<E>.forEach(body: (Int, E) -> Unit) {
    for (index in 0 until size()) {
        body(keyAt(index), valueAt(index))
    }
}

/**
 * Transform [SparseArrayCompat<E>][SparseArrayCompat] to [Map<Int,E>][Map]
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun <E> SparseArrayCompat<E>.asMap(): Map<Int, E> {
    val map = HashMap<Int, E>()
    forEach { key, value ->
        map[key] = value
    }
    return map
}
