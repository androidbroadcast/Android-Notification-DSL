package com.kirich1409.androidnotificationdsl.utils

import androidx.collection.SparseArrayCompat

inline fun <T> SparseArrayCompat<T>.forEach(action: (Int, T) -> Unit) {
    for(i in 0 until size()) {
        action(keyAt(i), valueAt(i))
    }
}
