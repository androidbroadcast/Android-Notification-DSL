@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.media

import android.support.v4.media.session.MediaSessionCompat
import androidx.media.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.media.annotations.NotificationMediaStyleMarker

@NotificationMediaStyleMarker
@Suppress("UndocumentedPublicClass")
class MediaStyle @PublishedApi internal constructor(private val style: NotificationCompat.MediaStyle) {

    /**
     * Attaches a [token] to the notification to provide additional playback information and control to the SystemUI.
     */
    fun mediaSession(token: MediaSessionCompat.Token) {
        style.setMediaSession(token)
    }

    /**
     * Specify action to show in the compact notification view
     */
    fun actionsInCompactView(action1: Int) {
        style.setShowActionsInCompactView(action1)
    }

    /**
     * Specify actions to show in the compact notification view
     */
    fun actionsInCompactView(action1: Int, action2: Int) {
        style.setShowActionsInCompactView(action1, action2)
    }

    /**
     * Specify actions to show in the compact notification view
     */
    fun actionsInCompactView(action1: Int, action2: Int, action3: Int) {
        style.setShowActionsInCompactView(action1, action2, action3)
    }
}
