@file:Suppress(
    "unused", "MemberVisibilityCanBePrivate", "NOTHING_TO_INLINE", "TooManyFunctions",
    "UsePropertyAccessSyntax"
)

package com.kirich1409.androidnotificationdsl

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.annotation.*
import androidx.annotation.IntRange
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.kirich1409.androidnotificationdsl.action.Actions
import com.kirich1409.androidnotificationdsl.bubble.BubbleMetadataBuilder
import com.kirich1409.androidnotificationdsl.internal.toArray
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import android.app.Notification as AndroidNotification

/**
 * Notification builder
 */
@NotificationMarker
@Suppress("TooManyFunctions")
class NotificationBuilder @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@PublishedApi internal constructor(
    internal val context: Context,
    @PublishedApi internal val notification: NotificationCompat.Builder
) {

    /**
     * Notification's actions
     */
    inline val actions: Actions
        get() = Actions(notification)

    /**
     * Notification's actions
     */
    inline fun actions(body: @NotificationMarker Actions.() -> Unit) {
        actions.body()
    }

    /**
     * Determines whether the platform can generate contextual actions for a notification.
     *
     * By default this is true.
     */
    var allowSystemGeneratedContextualAction = DEFAULT_ALLOW_SYSTEM_GENERATED_CONTEXTUAL_ACTION
        set(value) {
            field = value
            notification.setAllowSystemGeneratedContextualActions(value)
        }

    /**
     * Setting this flag will make it so the notification is automatically canceled
     * when the user clicks it in the panel.
     *
     * The PendingIntent set with [deleteIntent] will be broadcast when the notification is canceled.
     */
    var autoCancel = DEFAULT_AUTO_CANCEL
        set(value) {
            field = value
            notification.setAutoCancel(value)
        }

    /**
     * Sets which icon to display as a badge for this notification.
     *
     * Must be one of [BADGE_ICON_NONE][NotificationCompat.BADGE_ICON_NONE],
     * [BADGE_ICON_SMALL][NotificationCompat.BADGE_ICON_SMALL], [BADGE_ICON_LARGE][NotificationCompat.BADGE_ICON_LARGE].
     *
     * **Note:** This value might be ignored, for launchers that don't support badge icons.
     */
    @NotificationCompat.BadgeIconType
    var badgeIconType = DEFAULT_BADGE_ICON_TYPE
        set(value) {
            field = value
            notification.setBadgeIconType(value)
        }

    /**
     * Setup bubble metadata of the notification
     */
    @RequiresApi(Build.VERSION_CODES.R)
    inline fun bubbleMetadata(
        shortcutId: String,
        body: @NotificationMarker BubbleMetadataBuilder.() -> Unit
    ) {
        notification.bubbleMetadata = NotificationCompat.BubbleMetadata.Builder(shortcutId)
            .also { BubbleMetadataBuilder(it).body() }
            .build()
    }


    /**
     * Setup bubble metadata of the notification
     */
    @RequiresApi(Build.VERSION_CODES.R)
    inline fun bubbleMetadata(
        intent: PendingIntent,
        icon: IconCompat,
        body: @NotificationMarker BubbleMetadataBuilder.() -> Unit
    ) {
        notification.bubbleMetadata = NotificationCompat.BubbleMetadata.Builder(intent, icon)
            .also { BubbleMetadataBuilder(it).body() }
            .build()
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    fun build(): AndroidNotification {
        return notification.build()
    }

    /**
     * Set the notification category.
     *
     * Must be one of the predefined notification categories (see the `CATEGORY_*`
     * constants in [NotificationCompat]) that best describes this notification.
     *
     * May be used by the system for ranking and filtering.
     */
    var category = DEFAULT_CATEGORY
        set(value) {
            field = value
            notification.setCategory(category?.stringValue)
        }

    /**
     * The accent color to use for [NotificationBuilder.color].
     */
    @ColorInt
    var color = DEFAULT_COLOR
        set(value) {
            field = value
            notification.setColor(color)
        }

    /**
     * Set whether this notification should be colorized. When set, the color set with [color] will be used
     * as the background color of this notification.
     *
     * This should only be used for high priority ongoing tasks like navigation, an ongoing call,
     * or other similarly high-priority events for the user.
     *
     * For most styles, the coloring will only be applied if the notification is for a
     * foreground service notification.
     *
     * However, for MediaStyle and DecoratedMediaCustomViewStyle notifications that have a media session attached
     * there is no such requirement.
     *
     * Calling this method on any version prior to [Build.VERSION_CODES.O][android.os.Build.VERSION_CODES.O] will
     * not have an effect on the notification and it won't be colorized.
     *
     * @see color
     */
    var colorized = DEFAULT_COLORIZED
        set(value) {
            field = value
            notification.setColorized(value)
        }

    /**
     * Supply a custom [RemoteViews] to use instead of the standard one.
     */
    var content: RemoteViews? = null
        set(value) {
            field = value
            notification.setContent(value)
        }

    /**
     * Supply a [PendingIntent] to send when the notification is clicked. If you do not supply an intent,
     * you can now add [PendingIntent]s to individual views to be launched when clicked by calling
     * [RemoteViews.setOnClickPendingIntent].
     * Be sure to read [NotificationBuilder.contentIntent] for how to correctly use this.
     */
    var contentIntent: PendingIntent? = DEFAULT_CONTENT_INTENT
        set(value) {
            field = value
            notification.setContentIntent(value)
        }

    /**
     * Set the large text at the right-hand side of the notification.
     */
    var contentInfo: CharSequence? = DEFAULT_CONTENT_INFO
        set(value) {
            field = value
            notification.setContentInfo(value)
        }

    /**
     * Set the text (second row) of the notification, in a standard notification.
     */
    var contentText: CharSequence? = DEFAULT_CONTENT_TEXT
        set(value) {
            field = value
            notification.setContentText(value)
        }

    /**
     * Set the title (first row) of the notification, in a standard notification.
     */
    var contentTitle: CharSequence? = DEFAULT_CONTENT_TITLE
        set(value) {
            field = value
            notification.setContentTitle(value)
        }

    /**
     * Sets the Chronometer to count down instead of counting up.
     *
     * This is only relevant if setUsesChronometer(boolean) has been set to true. If it
     * isn't set the chronometer will count up.
     *
     * Has effect only on [Build.VERSION_CODES.N][android.os.Build.VERSION_CODES.N] and newer
     *
     * @see android.widget.Chronometer
     */
    var chronometerCountDown: Boolean = DEFAULT_CHRONOMETER_COUNT_DOWN
        set(countsDown) {
            field = countsDown
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                notification.setChronometerCountDown(countsDown)
            }
        }

    /**
     * Supply custom RemoteViews to use instead of the platform template in the expanded form.
     *
     * This will override the expanded layout that would otherwise be constructed by this Builder object.
     *
     * No-op on versions prior to [Build.VERSION_CODES.JELLY_BEAN][android.os.Build.VERSION_CODES.JELLY_BEAN].
     */
    var customBigContentView: RemoteViews? = DEFAULT_CUSTOM_BIG_CONTENT_VIEW
        set(contentView) {
            field = contentView
            notification.setCustomBigContentView(contentView)
        }

    /**
     * Supply custom RemoteViews to use instead of the platform template.
     *
     * This will override the layout that would otherwise be constructed by this Builder object.
     */
    var customContentView: RemoteViews? = DEFAULT_CUSTOM_CONTENT_VIEW
        set(contentView) {
            field = contentView
            notification.setCustomContentView(contentView)
        }

    /**
     * Supply custom RemoteViews to use instead of the platform template in the heads up dialog.
     *
     * This will override the heads-up layout that would otherwise be constructed by this Builder object.
     *
     * No-op on versions prior to [Build.VERSION_CODES.LOLLIPOP][android.os.Build.VERSION_CODES.LOLLIPOP].
     */
    var customHeadsUpContentView: RemoteViews? = DEFAULT_CUSTOM_HEADS_UP_CONTENT_VIEW
        set(contentView) {
            field = contentView
            notification.setCustomHeadsUpContentView(contentView)
        }

    /**
     * Set the default notification options that will be used.
     *
     * The value should be one or more of the following fields combined with bitwise-or:
     * [NotificationCompat.DEFAULT_SOUND], [NotificationCompat.DEFAULT_VIBRATE], [NotificationCompat.DEFAULT_LIGHTS].
     *
     * For all default values, use [NotificationCompat.DEFAULT_ALL].
     */
    var defaults: NotificationDefaults? = DEFAULT_DEFAULTS
        set(value) {
            field = value
            notification.setDefaults(value?.intValue ?: 0)
        }

    /**
     * Supply a [PendingIntent] to send when the notification is cleared by the user directly from
     * the notification panel. For example, this intent is sent when the user clicks the "Clear all" button,
     * or the individual "X" buttons on notifications. This intent is not sent when the application calls
     * [NotificationManager.cancel].
     */
    var deleteIntent: PendingIntent? = DEFAULT_DELETE_INTENT
        set(value) {
            field = value
            notification.setDeleteIntent(value)
        }

    /**
     * The current metadata [Bundle] used by this notification Builder.
     */
    inline var extras: Bundle
        get() = notification.extras
        set(value) {
            notification.setExtras(value)
        }

    /**
     * An intent to launch instead of posting the notification to the status bar.
     * Only for use with extremely high-priority notifications demanding the user's **immediate** attention,
     * such as an incoming phone call or alarm clock that the user has explicitly set to a particular time.
     * If this facility is used for something else, please give the user an option
     * to turn it off and use a normal notification, as this can be extremely disruptive.
     *
     * &nbsp;
     *
     * On some platforms, the system UI may choose to display a heads-up notification,
     * instead of launching this intent, while the user is using the device.
     *
     * &nbsp;
     *
     * @param intent The pending intent to launch.
     * @param highPriority Passing true will cause this notification to be sent even
     * if other notifications are suppressed.
     */
    @SuppressLint("InlinedApi")
    @RequiresPermission(Manifest.permission.USE_FULL_SCREEN_INTENT)
    fun fullScreenIntent(intent: PendingIntent, highPriority: Boolean = false) {
        notification.setFullScreenIntent(intent, highPriority)
    }

    /**
     * Set this notification to be part of a group of notifications sharing the same key.
     * Grouped notifications may display in a cluster or stack on devices which support such rendering.
     *
     * To make this notification the summary for its group, also call [groupSummary].
     * A sort order can be specified for group members by using [sortKey]
     *
     * @see groupSummary
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    var group: String? = DEFAULT_GROUP_KEY
        set(groupKey) {
            field = groupKey
            notification.setGroup(groupKey)
        }

    /**
     * Sets the group alert behavior for this notification. Use this method to mute this
     * notification if alerts for this notification's group should be handled by a different
     * notification. This is only applicable for notifications that belong to a
     * [group]. This must be called on all notifications you want to mute.
     *
     * For example, if you want only the summary of your group to make noise, all children in the group should have
     * the group alert behavior [GROUP_ALERT_SUMMARY][NotificationCompat.GROUP_ALERT_SUMMARY].
     *
     * &nbsp;
     *
     * The default value is [GROUP_ALERT_ALL][NotificationCompat.GROUP_ALERT_ALL]
     */
    @NotificationCompat.GroupAlertBehavior
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    var groupAlertBehavior: Int = DEFAULT_GROUP_ALERT_BEHAVIOR
        set(groupAlertBehavior) {
            field = groupAlertBehavior
            notification.setGroupAlertBehavior(groupAlertBehavior)
        }

    /**
     * Set this notification to be the group summary for a group of notifications.
     * Grouped notifications may display in a cluster or stack on devices which
     * support such rendering. Requires a group key also be set using [group].
     *
     * @see group
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    var groupSummary: Boolean = DEFAULT_GROUP_SUMMARY
        set(value) {
            field = value
            notification.setGroupSummary(value)
        }

    /**
     * Set the large icon that is shown in the ticker and notification.
     */
    var largeIcon: Bitmap? = DEFAULT_LARGE_ICON
        set(value) {
            field = value
            notification.setLargeIcon(value)
        }

    /**
     * Set the argb value that you would like the LED on the device to blink, as well as the
     * rate. The rate is specified in terms of the number of milliseconds to be on
     * and then the number of milliseconds to be off.
     */
    var lights: Lights? = DEFAULT_LIGHTS
        set(value) {
            field = value
            with(value ?: Lights.NULL) {
                notification.setLights(color, onMs, offMs)
            }
        }

    /**
     * Set whether or not this notification is only relevant to the current device.
     *
     * Some notifications can be bridged to other devices for remote display.
     * This hint can be set to recommend this notification not be bridged.
     *
     * Default value is `false`
     */
    var localOnly: Boolean = DEFAULT_LOCAL_ONLY
        set(localOnly) {
            field = localOnly
            notification.setLocalOnly(localOnly)
        }

    /**
     * Set the large number at the right-hand side of the notification. This is equivalent to setContentInfo,
     * although it might show the number in a different font size for readability.
     */
    var number: Int = 0
        set(number) {
            field = number
            notification.setNumber(number)
        }

    /**
     * Set whether this is an ongoing notification.
     *
     * Ongoing notifications differ from regular notifications in the following ways:
     * * Ongoing notifications are sorted above the regular notifications in the notification panel.
     * * Ongoing notifications do not have an 'X' close button, and are not affected by the "Clear all" button.
     */
    var ongoing: Boolean = DEFAULT_ONGOING
        set(ongoing) {
            field = ongoing
            notification.setOngoing(ongoing)
        }

    /**
     * Set this flag if you would only like the sound, vibrate
     * and ticker to be played if the notification is not already showing.
     */
    var onlyAlertOnce: Boolean = DEFAULT_ONLY_ALERT_ONCE
        set(value) {
            field = value
            notification.setOnlyAlertOnce(value)
        }

    /**
     * Persons associated with the notification
     */
    val persons = Persons(notification)

    /**
     * Persons associated with the notification
     */
    fun persons(body: @NotificationMarker Persons.() -> Unit) {
        persons.body()
    }

    /**
     * Set the relative priority for this notification.
     *
     * Priority is an indication of how much of the user's valuable attention should be consumed by this
     * notification. Low-priority notifications may be hidden from the user in certain situations,
     * while the user might be interrupted for a higher-priority notification.
     * The system sets a notification's priority based on various factors including the setPriority value.
     * The effect may differ slightly on different platforms.
     *
     * Must be one of the priority constants defined by [NotificationCompat].
     * Acceptable values range from [NotificationCompat.PRIORITY_MIN] (-2) to [NotificationCompat.PRIORITY_MAX] (2).
     */
    var priority: NotificationPriority = DEFAULT_PRIORITY
        set(value) {
            field = value
            notification.setPriority(priority.intValue)
        }

    /**
     * Set the progress this notification represents, which may be
     * represented as a [ProgressBar][android.widget.ProgressBar].
     */
    var progress: NotificationProgress? = DEFAULT_PROGRESS
        set(value) {
            field = value
            with(value ?: NotificationProgress.NULL) {
                notification.setProgress(max, progress, indeterminate)
            }
        }

    /**
     * Supply a replacement Notification whose contents should be shown in insecure contexts
     * (i.e. atop the secure lockscreen). See [NotificationBuilder.notificationVisibility] and
     * [VISIBILITY_PUBLIC][NotificationCompat.VISIBILITY_PUBLIC].
     */
    var publicVersion: AndroidNotification? = null
        set(notification) {
            field = notification
            this.notification.setPublicVersion(notification)
        }

    /**
     * Set the remote input history.
     *
     * This should be set to the most recent inputs that have been sent
     * through a [RemoteInput][androidx.core.app.RemoteInput] of this Notification and cleared once the it is no
     * longer relevant (e.g. for chat notifications once the other party has responded).
     *
     * The most recent input must be stored at the 0 index, the second most recent at the
     * 1 index, etc. Note that the system will limit both how far back the inputs will be shown
     * and how much of each individual input is shown.
     *
     * **Note:** The reply text will only be shown on notifications that have least one action with a `RemoteInput`.
     */
    var remoteInputHistory: Array<CharSequence>? = null
        get() = field?.copyOf()
        set(text) {
            field = text
            notification.setRemoteInputHistory(text)
        }

    /**
     * If this notification is duplicative of a Launcher shortcut,
     * sets the [id][androidx.core.content.pm.ShortcutInfoCompat.getId] of the shortcut,
     * in case the Launcher wants to hide the shortcut.
     *
     * **Note:** This field will be ignored by Launchers that doesn't support
     * badging or [shortcuts][androidx.core.content.pm.ShortcutInfoCompat].
     */
    var shortcutId: String? = DEFAULT_SHORTCUT_ID
        set(value) {
            field = value
            notification.setShortcutId(shortcutId)
        }

    /**
     * Control whether the timestamp set with [whenTime] is shown in the content view.
     */
    var showWhen: Boolean = DEFAULT_SHOW_WHEN
        set(value) {
            field = value
            notification.setShowWhen(value)
        }

    /**
     * Silences this instance of the notification, regardless of the sounds or vibrations set
     * on the notification or notification channel.
     */
    var silent: Boolean = DEFAULT_SILENT
        set(value) {
            field = value
            notification.setSilent(value)
        }

    /**
     * Set the small icon to use in the notification layouts. Different classes of devices
     * may return different sizes. See the UX guidelines for more information on how to
     * design these icons.
     */
    var smallIcon: NotificationIcon? = DEFAULT_SMALL_ICON
        set(value) {
            field = value
            with(value ?: NotificationIcon.NULL) {
                if (level >= 0) {
                    notification.setSmallIcon(icon, level)
                } else {
                    notification.setSmallIcon(icon)
                }
            }
        }

    /**
     * Set a sort key that orders this notification among other notifications from the
     * same package. This can be useful if an external sort was already applied and an app
     * would like to preserve this. Notifications will be sorted lexicographically using this
     * value, although providing different priorities in addition to providing sort key may
     * cause this value to be ignored.
     *
     * This sort key can also be used to order members of a notification group. See [group]
     *
     * @see String.compareTo
     */
    var sortKey: String? = null
        set(sortKey) {
            field = sortKey
            notification.setSortKey(sortKey)
        }

    /**
     * Set the sound to play. It will play on the stream you supply.
     *
     * On some platforms, a notification that is noisy is more likely to be presented as a heads-up notification.
     *
     * See [AudioManager][android.media.AudioManager] for the `STREAM_` constants.
     *
     * @see NotificationCompat.STREAM_DEFAULT
     */
    var sound: Sound? = DEFAULT_SOUND
        @SuppressLint("WrongConstant")
        set(value) {
            field = value
            if (value == null) {
                notification.setSound(null, NotificationCompat.STREAM_DEFAULT)
            } else {
                notification.setSound(value.sound, value.streamType.streamTypeInt)
            }
        }

    /**
     * Add a rich notification style to be applied at build time.
     *
     * If the platform does not provide rich notification styles, this method has no effect. The
     * user will always see the normal notification style.
     */
    var style: NotificationCompat.Style? = DEFAULT_STYLE
        set(style) {
            field = style
            notification.setStyle(style)
        }

    /**
     * Set the third line of text in the platform notification template.
     * Don't use if you're also using [progress]; they occupy the same location in the standard template.
     *
     * If the platform does not provide large-format notifications, this method has no effect.
     * The third line of text only appears in expanded view.
     */
    var subText: CharSequence? = DEFAULT_SUB_TEXT
        set(text) {
            field = text
            notification.setSubText(text)
        }

    /**
     * Sets the "ticker" text which is sent to accessibility services. Prior to [Build.VERSION_CODES.LOLLIPOP],
     * sets the text that is displayed in the status bar when the notification first arrives,
     * and also a RemoteViews object that may be displayed instead on some devices.
     */
    var ticker: CharSequence? = null
        set(value) {
            field = value
            notification.setTicker(value)
        }

    /**
     * Specifies the time at which this notification should be canceled, if it is not already canceled.
     */
    @IntRange(from = 0)
    var timeoutAfter: Long = DEFAULT_TIMEOUT_AFTER
        set(value) {
            require(value >= 0) { "ticker must be >= 0" }
            field = value
            notification.setTimeoutAfter(value)
        }

    /**
     * Show the [Notification.when][AndroidNotification.when] field as a stopwatch.
     *
     * Instead of presenting `when` as a timestamp, the notification will show an
     * automatically updating display of the minutes and seconds since `when`.
     *
     * Useful when showing an elapsed time (like an ongoing phone call).
     *
     * @see android.widget.Chronometer
     * @see whenTime
     */
    var usesChronometer: Boolean = DEFAULT_USES_CHRONOMETER
        set(value) {
            field = value
            notification.setUsesChronometer(value)
        }

    /**
     * Set the vibration pattern to use.
     *
     * On some platforms, a notification that vibrates is more likely to be presented as a heads-up notification.
     *
     * See [Vibrator][android.os.Vibrator] for a discussion of the `pattern` parameter.
     */
    var vibrate: VibratePattern? = DEFAULT_VIBRATE
        set(value) {
            field = value
            notification.setVibrate(value?.asArray())
        }

    /**
     * Sets [NotificationBuilder.notificationVisibility].
     *
     * @see NotificationVisibility
     */
    var notificationVisibility: NotificationVisibility = DEFAULT_VISIBILITY
        set(value) {
            field = value
            notification.setWhen(whenTime)
        }

    /**
     * Set the time that the event occurred. Notifications in the panel are sorted by this time.
     */
    @IntRange(from = 0)
    var whenTime: Long = 0
        set(value) {
            field = value
            notification.setWhen(whenTime)
        }

    init {
        whenTime = System.currentTimeMillis()
    }

    internal companion object {
        const val DEFAULT_AUTO_CANCEL = true
        const val DEFAULT_ALLOW_SYSTEM_GENERATED_CONTEXTUAL_ACTION = true
        const val DEFAULT_BADGE_ICON_TYPE = NotificationCompat.BADGE_ICON_NONE
        val DEFAULT_CATEGORY: NotificationCategory? = null
        const val DEFAULT_COLOR = Color.TRANSPARENT
        const val DEFAULT_COLORIZED = false
        val DEFAULT_CONTENT_TEXT: CharSequence? = null
        val DEFAULT_CONTENT_TITLE: CharSequence? = null
        val DEFAULT_CONTENT_INFO: CharSequence? = null
        val DEFAULT_CONTENT_INTENT: PendingIntent? = null
        val DEFAULT_DEFAULTS: NotificationDefaults? = null
        val DEFAULT_LARGE_ICON: Bitmap? = null
        const val DEFAULT_ONLY_ALERT_ONCE = true
        val DEFAULT_PRIORITY = NotificationPriority.DEFAULT
        val DEFAULT_VISIBILITY = NotificationVisibility.PRIVATE
        val DEFAULT_VIBRATE: VibratePattern? = null
        val DEFAULT_DELETE_INTENT: PendingIntent? = null
        val DEFAULT_SHORTCUT_ID: String? = null
        const val DEFAULT_SHOW_WHEN = true
        const val DEFAULT_SILENT = false
        const val DEFAULT_USES_CHRONOMETER = false
        const val DEFAULT_TIMEOUT_AFTER: Long = 0
        val DEFAULT_SUB_TEXT: CharSequence? = null
        val DEFAULT_STYLE: NotificationCompat.Style? = null
        val DEFAULT_SOUND: Sound? = null
        val DEFAULT_SORT_KEY: String? = null
        val DEFAULT_REMOTE_INPUT_HISTORY: Array<CharSequence>? = null
        const val DEFAULT_SOUND_STREAM_TYPE = NotificationCompat.STREAM_DEFAULT
        const val DEFAULT_ONGOING = false
        const val DEFAULT_NUMBER = 0
        const val DEFAULT_LOCAL_ONLY = false
        const val DEFAULT_GROUP_SUMMARY = false
        const val DEFAULT_GROUP_ALERT_BEHAVIOR: Int = NotificationCompat.GROUP_ALERT_ALL
        val DEFAULT_GROUP_KEY: String? = null
        const val DEFAULT_CHRONOMETER_COUNT_DOWN = false
        val DEFAULT_CUSTOM_BIG_CONTENT_VIEW: RemoteViews? = null
        val DEFAULT_CUSTOM_CONTENT_VIEW: RemoteViews? = null
        val DEFAULT_CUSTOM_HEADS_UP_CONTENT_VIEW: RemoteViews? = null
        val DEFAULT_LIGHTS: Lights? = null
        val DEFAULT_PROGRESS: NotificationProgress? = null
        var DEFAULT_SMALL_ICON: NotificationIcon? = null
    }
}

@PublishedApi
internal fun defaultNotification(
    context: Context,
    channelId: String,
    @DrawableRes smallIcon: Int
): NotificationCompat.Builder {
    val builder = NotificationCompat.Builder(context, channelId)
    builder.setSmallIcon(smallIcon)
        .setAutoCancel(NotificationBuilder.DEFAULT_AUTO_CANCEL)
        .setAllowSystemGeneratedContextualActions(
            NotificationBuilder.DEFAULT_ALLOW_SYSTEM_GENERATED_CONTEXTUAL_ACTION
        )
        .setBadgeIconType(NotificationBuilder.DEFAULT_BADGE_ICON_TYPE)
        .setCategory(NotificationBuilder.DEFAULT_CATEGORY?.stringValue)
        .setColor(NotificationBuilder.DEFAULT_COLOR)
        .setColorized(NotificationBuilder.DEFAULT_COLORIZED)
        .setContentText(NotificationBuilder.DEFAULT_CONTENT_TEXT)
        .setContentTitle(NotificationBuilder.DEFAULT_CONTENT_TITLE)
        .setContentInfo(NotificationBuilder.DEFAULT_CONTENT_INFO)
        .setContentIntent(NotificationBuilder.DEFAULT_CONTENT_INTENT)
        .setLargeIcon(NotificationBuilder.DEFAULT_LARGE_ICON)
        .setOnlyAlertOnce(NotificationBuilder.DEFAULT_ONLY_ALERT_ONCE)
        .setPriority(NotificationBuilder.DEFAULT_PRIORITY.intValue)
        .setVisibility(NotificationBuilder.DEFAULT_VISIBILITY.intValue)
        // Ignored .setVibrate(DEFAULT_VIBRATE)
        .setDeleteIntent(NotificationBuilder.DEFAULT_DELETE_INTENT)
        .setShortcutId(NotificationBuilder.DEFAULT_SHORTCUT_ID)
        .setShowWhen(NotificationBuilder.DEFAULT_SHOW_WHEN)
        .setSilent(NotificationBuilder.DEFAULT_SILENT)
        .setUsesChronometer(NotificationBuilder.DEFAULT_USES_CHRONOMETER)
        .setTimeoutAfter(NotificationBuilder.DEFAULT_TIMEOUT_AFTER)
        .setSubText(NotificationBuilder.DEFAULT_SUB_TEXT)
        .setStyle(NotificationBuilder.DEFAULT_STYLE)
        .setSortKey(NotificationBuilder.DEFAULT_SORT_KEY)
        .setRemoteInputHistory(NotificationBuilder.DEFAULT_REMOTE_INPUT_HISTORY)
        .setOngoing(NotificationBuilder.DEFAULT_ONGOING)
        .setNumber(NotificationBuilder.DEFAULT_NUMBER)
        .setLocalOnly(NotificationBuilder.DEFAULT_LOCAL_ONLY)
        .setGroupSummary(NotificationBuilder.DEFAULT_GROUP_SUMMARY)
        .setGroupAlertBehavior(NotificationBuilder.DEFAULT_GROUP_ALERT_BEHAVIOR)
        .setGroup(NotificationBuilder.DEFAULT_GROUP_KEY)
        .setCustomBigContentView(NotificationBuilder.DEFAULT_CUSTOM_BIG_CONTENT_VIEW)
        .setCustomContentView(NotificationBuilder.DEFAULT_CUSTOM_CONTENT_VIEW)
        .setCustomHeadsUpContentView(NotificationBuilder.DEFAULT_CUSTOM_HEADS_UP_CONTENT_VIEW)
    // Ignored .setLights(DEFAULT_LIGHTS)
    // Ignored .setProgress(DEFAULT_PROGRESS)
    // Ignored .setSmallIcon(DEFAULT_SMALL_ICON)
    return builder
}

/**
 * Create new notification for specified [channelId]
 *
 * @param channelId The constructed Notification will be posted on this NotificationChannel
 *
 * @return New [AndroidNotification] instance
 */
inline fun notification(
    context: Context,
    channelId: String,
    @DrawableRes smallIcon: Int,
    body: NotificationBuilder.() -> Unit
): AndroidNotification {
    return buildNotification(context, channelId, smallIcon, body).build()
}

/**
 * Create new notification for specified [channelId]
 *
 * @param channelId The constructed Notification will be posted on this NotificationChannel
 *
 * @return New [AndroidNotification] instance
 */
fun notification(
    context: Context,
    channelId: String,
    @DrawableRes smallIcon: Int
): AndroidNotification {
    return NotificationBuilder(context, defaultNotification(context, channelId, smallIcon)).build()
}

/**
 * Create new notification based on [notification]
 *
 * @param notification Base notification
 *
 * @return New [AndroidNotification] instance
 */
@RequiresApi(Build.VERSION_CODES.KITKAT)
fun notification(
    context: Context,
    notification: AndroidNotification
): AndroidNotification {
    return NotificationBuilder(context, NotificationCompat.Builder(context, notification)).build()
}

inline fun buildNotification(
    context: Context,
    channelId: String,
    @DrawableRes smallIcon: Int,
    body: NotificationBuilder.() -> Unit
): NotificationBuilder {
    return NotificationBuilder(context, defaultNotification(context, channelId, smallIcon))
        .apply(body)
}

/**
 * Set the remote input history.
 *
 * This should be set to the most recent inputs that have been sent
 * through a [RemoteInput][androidx.core.app.RemoteInput] of this Notification and cleared once the it is no
 * longer relevant (e.g. for chat notifications once the other party has responded).
 *
 * The most recent input must be stored at the 0 index, the second most recent at the
 * 1 index, etc. Note that the system will limit both how far back the inputs will be shown
 * and how much of each individual input is shown.
 *
 * **Note:** The reply text will only be shown on notifications that have least one action with a `RemoteInput`.
 */
inline fun NotificationBuilder.remoteInputHistory(vararg text: CharSequence) {
    notification.setRemoteInputHistory(text)
}

/**
 * Set the remote input history.
 *
 * This should be set to the most recent inputs that have been sent
 * through a [RemoteInput][androidx.core.app.RemoteInput] of this Notification and cleared once the it is no
 * longer relevant (e.g. for chat notifications once the other party has responded).
 *
 * The most recent input must be stored at the 0 index, the second most recent at the
 * 1 index, etc. Note that the system will limit both how far back the inputs will be shown
 * and how much of each individual input is shown.
 *
 * **Note:** The reply text will only be shown on notifications that have least one action with a `RemoteInput`.
 */
inline fun NotificationBuilder.remoteInputHistory(text: Iterable<CharSequence>) {
    notification.setRemoteInputHistory(text.toArray())
}

/**
 * Specifies the time at which this notification should be canceled, if it is not already canceled.
 */
@ExperimentalTime
inline fun NotificationBuilder.timeoutAfter(duration: Duration) {
    notification.setTimeoutAfter(duration.inWholeMilliseconds)
}

/**
 * Set the vibration pattern to use.
 *
 * On some platforms, a notification that vibrates is more likely to be presented as a heads-up notification.
 *
 * See [Vibrator][android.os.Vibrator] for a discussion of the `pattern` parameter.
 */
inline fun NotificationBuilder.vibrate(vararg pattern: Long) {
    notification.setVibrate(pattern)
}

/**
 * Set the large text at the right-hand side of the notification.
 */
fun NotificationBuilder.contentInfo(@StringRes infoRes: Int) {
    contentInfo = context.getText(infoRes)
}

/**
 * Set the text (second row) of the notification, in a standard notification.
 */
fun NotificationBuilder.contentText(@StringRes contentRes: Int) {
    contentText = context.getText(contentRes)
}

/**
 * Set the title (first row) of the notification, in a standard notification.
 */
fun NotificationBuilder.contentTitle(@StringRes titleRes: Int) {
    contentTitle = context.getText(titleRes)
}

/**
 * Set the third line of text in the platform notification template.
 * Don't use if you're also using [progress][NotificationBuilder.progress];
 * they occupy the same location in the standard template.
 *
 * &nbsp;
 *
 * If the platform does not provide large-format notifications, this method has no effect.
 * The third line of text only appears in expanded view.
 */
fun NotificationBuilder.subText(@StringRes textRes: Int) {
    subText = context.getText(textRes)
}

/**
 * Apply an extender to this notification builder.
 * Extenders may be used to add metadata or change options on this builder.
 */
fun NotificationBuilder.extend(extender: NotificationCompat.Extender) {
    notification.extend(extender)
}
