@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.kirich1409.androidnotificationdsl.wearable

import android.app.PendingIntent
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.kirich1409.androidnotificationdsl.action.ActionBuilder

@NotificationWearableExtenderActionsMarker
@Suppress("UndocumentedPublicClass")
class WearableExtenderActions @PublishedApi internal constructor(
    @PublishedApi internal val wearableExtender: NotificationCompat.WearableExtender
) {
    /**
     * Add a wearable action to this notification.
     *
     * When wearable actions are added using this method, the set of actions that show on a wearable device splits
     * from devices that only show actions added using [NotificationCompat.Builder.addAction].
     * This allows for customization of which actions display on different devices.
     *
     * @param action the action to add to this notification
     *
     * @see NotificationCompat.Action
     */
    fun addAction(action: NotificationCompat.Action) {
        wearableExtender.addAction(action)
    }

    /**
     * Add a wearable action to this notification.
     *
     * When wearable actions are added using this method, the set of actions that show on a wearable device splits
     * from devices that only show actions added using [NotificationCompat.Builder.addAction].
     * This allows for customization of which actions display on different devices.
     *
     * <p><strong>Note:</strong> For devices running an Android version strictly lower than
     * API level 23 this constructor only supports resource-ID based IconCompat objects.
     *
     * @param icon icon to show for this action
     * @param title the title of the action
     * @param intent the [PendingIntent] to fire when users trigger this action
     *
     * @see NotificationCompat.Action
     */
    inline fun action(
        title: CharSequence,
        intent: PendingIntent,
        icon: IconCompat? = null,
        body: @NotificationWearableExtenderActionsMarker ActionBuilder.() -> Unit
    ) {
        val action = NotificationCompat.Action.Builder(icon, title, intent)
            .also { ActionBuilder(it).body() }
            .build()
        wearableExtender.addAction(action)
    }

    /**
     * Add a wearable action to this notification.
     *
     * When wearable actions are added using this method, the set of actions that show on a wearable device splits
     * from devices that only show actions added using [NotificationCompat.Builder.addAction].
     * This allows for customization of which actions display on different devices.
     *
     * @param icon icon to show for this action
     * @param title the title of the action
     * @param intent the [PendingIntent] to fire when users trigger this action
     *
     * @see NotificationCompat.Action
     */
    inline fun action(
        title: CharSequence,
        intent: PendingIntent,
        @DrawableRes icon: Int,
        body: @NotificationWearableExtenderActionsMarker ActionBuilder.() -> Unit
    ) {
        val action = NotificationCompat.Action.Builder(icon, title, intent)
            .also { ActionBuilder(it).body() }
            .build()
        wearableExtender.addAction(action)
    }

    /**
     * Add a wearable action to this notification.
     *
     * When wearable actions are added using this method, the set of actions that show on a wearable device splits
     * from devices that only show actions added using [NotificationCompat.Builder.addAction].
     * This allows for customization of which actions display on different devices.
     *
     * @param action the action to add to this notification
     *
     * @see NotificationCompat.Action
     */
    operator fun plus(action: NotificationCompat.Action) {
        addAction(action)
    }
}
