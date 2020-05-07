@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.person

import android.app.Notification
import android.provider.ContactsContract
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.person.annotations.NotificationPersonsMarker

@NotificationPersonsMarker
@Suppress("UndocumentedPublicClass")
class Persons @PublishedApi internal constructor(
    private val notification: NotificationCompat.Builder
) {

    /**
     * Add a person that is relevant to this notification.
     *
     * Depending on user preferences, this annotation may allow the notification to pass
     * through interruption filters, and to appear more prominently in the user interface.
     *
     * The person should be specified by the [String] representation of a
     * [ContactsContract.Contacts.CONTENT_LOOKUP_URI].
     *
     * The system will also attempt to resolve `mailto:` and `tel:` schema
     * URIs. The path part of these URIs must exist in the contacts database, in the
     * appropriate column, or the reference will be discarded as invalid. Telephone schema
     * URIs will be resolved by [ContactsContract.PhoneLookup].
     *
     * @param uri A URI for the person.
     *
     * @see Notification.EXTRA_PEOPLE
     */
    fun person(uri: String) {
        notification.addPerson(uri)
    }

    /**
     * Add a person that is relevant to this notification.
     *
     * Depending on user preferences, this annotation may allow the notification to pass
     * through interruption filters, and to appear more prominently in the user interface.
     *
     * The person should be specified by the [String] representation of a
     * [ContactsContract.Contacts.CONTENT_LOOKUP_URI].
     *
     * The system will also attempt to resolve `mailto:` and `tel:` schema
     * URIs. The path part of these URIs must exist in the contacts database, in the
     * appropriate column, or the reference will be discarded as invalid. Telephone schema
     * URIs will be resolved by [ContactsContract.PhoneLookup].
     *
     * @param personUri A URI for the person.
     *
     * @see Notification.EXTRA_PEOPLE
     */
    operator fun plus(personUri: String) {
        notification.addPerson(personUri)
    }
}
