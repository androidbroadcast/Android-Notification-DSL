@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.media

import android.support.v4.media.session.MediaSessionCompat
import androidx.media.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.media.internal.dsl.NotificationMediaStyleMarker

@NotificationMediaStyleMarker
class MediaStyle internal constructor(private val style: NotificationCompat.MediaStyle) {

    fun mediaSession(token: MediaSessionCompat.Token) {
        style.setMediaSession(token)
    }

    fun actionsInCompactView(action1: Int) {
        style.setShowActionsInCompactView(action1)
    }

    fun actionsInCompactView(action1: Int, action2: Int) {
        style.setShowActionsInCompactView(action1, action2)
    }

    fun actionsInCompactView(action1: Int, action2: Int, action3: Int) {
        style.setShowActionsInCompactView(action1, action2, action3)
    }
}