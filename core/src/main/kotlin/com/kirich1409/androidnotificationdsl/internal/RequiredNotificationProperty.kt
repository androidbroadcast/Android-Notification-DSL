package com.kirich1409.androidnotificationdsl.internal

import androidx.annotation.RestrictTo
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private val doNothing: (Any) -> Unit = {}

/**
 * @see Delegates.notNull
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun <T : Any> requiredNotificationProperty(propertyName: String): ReadWriteProperty<Any?, T> {
    return RequiredNotificationProperty({ "Required notification property `$propertyName` isn't" }, doNothing)
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun <T : Any> requiredNotificationProperty(propertyName: String, onChanged: (T) -> Unit): ReadWriteProperty<Any?, T> {
    return RequiredNotificationProperty({ "Required notification property `$propertyName` isn't" }, onChanged)
}

private class RequiredNotificationProperty<T : Any>(
    private val errorMessage: () -> String,
    private val onChanged: (T) -> Unit
) : ReadWriteProperty<Any?, T> {

    private var value: T? = null

    public override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException(errorMessage())
    }

    public override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
        onChanged(value)
    }
}
