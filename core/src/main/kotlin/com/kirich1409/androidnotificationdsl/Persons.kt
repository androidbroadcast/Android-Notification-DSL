@file:Suppress("MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl

import androidx.core.app.NotificationCompat

@JvmInline
value class Persons(
    private val notificationBuilder: NotificationCompat.Builder
) : Container<String> {

    /**
     * Add a person that is relevant to this notification.
     *
     * Depending on user preferences, this annotation may allow the notification to pass
     * through interruption filters, and to appear more prominently in the user interface.
     *
     * The person should be specified by the {@code String} representation of a
     * [android.provider.ContactsContract.Contacts.CONTENT_LOOKUP_URI].
     *
     * The system will also attempt to resolve `mailto:` and `tel:` schema
     * URIs. The path part of these URIs must exist in the contacts database, in the
     * appropriate column, or the reference will be discarded as invalid. Telephone schema
     * URIs will be resolved by [android.provider.ContactsContract.PhoneLookup].
     *
     * @param uri A URI for the person.
     * @see NotificationCompat.EXTRA_PEOPLE
     */
    fun person(uri: String) {
        notificationBuilder.addPerson(uri)
    }

    /**
     * @see person
     */
    override fun plusAssign(item: String) {
        person(item)
    }
}


