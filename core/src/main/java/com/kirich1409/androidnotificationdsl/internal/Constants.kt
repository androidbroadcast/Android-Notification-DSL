package com.kirich1409.androidnotificationdsl.internal

import androidx.annotation.RestrictTo

/**
 * Maximum length of CharSequences accepted by Builder and friends.
 *
 * Avoids spamming the system with overly large strings such as full e-mails.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal const val MAX_CHARSEQUENCE_LENGTH: Long = 5 * 1024
