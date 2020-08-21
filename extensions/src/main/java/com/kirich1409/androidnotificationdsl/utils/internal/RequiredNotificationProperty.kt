package com.kirich1409.androidnotificationdsl.utils.internal

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @see Delegates.notNull
 */
internal fun <T : Any> requiredNotificationProperty(propertyName: String): ReadWriteProperty<Any?, T> {
    return RequiredNotificationProperty {"Required notification property `$propertyName` isn't"}
}

private class RequiredNotificationProperty<T : Any>(
    private val errorMessage: () -> String
) : ReadWriteProperty<Any?, T> {

    private var value: T? = null

    public override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException(errorMessage())
    }

    public override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }
}