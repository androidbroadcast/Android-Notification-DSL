package com.kirich1409.androidnotificationdsl.action

import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.kirich1409.androidnotificationdsl.Container

class Extras internal constructor(
    private val action: NotificationCompat.Action.Builder
) : Container<Bundle> {

    fun extras(item: Bundle) {
        action.addExtras(item)
    }

    override fun plusAssign(item: Bundle) {
        extras(item)
    }
}
