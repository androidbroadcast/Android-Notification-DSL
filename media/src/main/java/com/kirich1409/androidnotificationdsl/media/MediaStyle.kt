@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.media

import android.support.v4.media.session.MediaSessionCompat
import androidx.annotation.IntRange
import androidx.media.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.internal.requiredNotificationProperty

@Suppress("UndocumentedPublicClass")
class MediaStyle @PublishedApi internal constructor(private val style: NotificationCompat.MediaStyle) {

    /**
     * Attaches a token to the notification to provide additional playback information and control to the SystemUI.
     */
    var mediaSession: MediaSessionCompat.Token by requiredNotificationProperty("Media session") { token ->
        style.setMediaSession(token)
    }

    /**
     * Specify actions to show in the compact notification view
     */
    var actionsInCompactView: Actions by requiredNotificationProperty("actionsInCompactView") { actions ->
        when (actions.count) {
            1 -> style.setShowActionsInCompactView(actions.action1)
            2 -> style.setShowActionsInCompactView(actions.action1, actions.action2)
            3 -> style.setShowActionsInCompactView(actions.action1, actions.action2, actions.action3)
        }
    }

    class Actions internal constructor(
        @IntRange(from = 1, to = 3) internal val count: Int,
        val action1: Int,
        val action2: Int,
        val action3: Int
    ) {

        init {
            require(count in 1..3)
        }

        constructor(action1: Int) : this(1, action1, action2 = -1, action3 = -1)
        constructor(action1: Int, action2: Int) : this(2, action1, action2, action3 = -1)
        constructor(action1: Int, action2: Int, action3: Int) : this(3, action1, action2, action3)
    }
}
