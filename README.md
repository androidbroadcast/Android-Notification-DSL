# Android Notification DSL (In Development)

Kotlin DSL for creating Android Notification using [NotificationCompat](https://developer.android.com/reference/androidx/core/app/NotificationCompat).

```gradle
// Core functionality
implementation 'com.github.kirich1409:android-notification-dsl-core:0.2.0'

// Extentions to simoplify work
implementation 'com.github.kirich1409:android-notification-dsl-extensions:0.2.0'

// Media Notification DSL
implementation 'com.github.kirich1409:android-notification-dsl-media:0.2.0'
```

## Sample

### Simple Notification
:
```kotlin
notification(context, CHANNEL_ID, smallIcon = R.drawable.notification_icon) {
    contentTitle(textTitle)
    contentText(textContent)
    priority(NotificationCompat.PRIORITY_DEFAULT)
}
```

### Grouped Notification

Create grouped notifications

```kotlin
notificationsGroup(context, groupKey = GROUP_KEY, channelId = CHANNEL) {
    summary(SUMMARY_NOTIFICATION_ID, smallIcon = R.drawable.ic_android_white_24dp) {
        contentTitle(R.string.notification_summary_title)
        contentText(R.string.notification_summary_text)
    }

    notifications {
        notification(NOTIFICATION_1_ID, smallIcon = R.drawable.ic_android_white_24dp) {
            contentTitle(R.string.notification_1_title)
        }

        notification(NOTIFICATION_2_ID, smallIcon = R.drawable.ic_android_white_24dp) {
            contentTitle(R.string.notification_2_title)
        }
    }
}
```

### Notification Channels DSL

Creating Android Notification Channels and Groups

```kotlin
createChannelsAndGroups(context) {
    channel(CHANNEL_1_ID, CHANNEL_1_NAME)

    group(CHANNEL_GROUP_1_ID, CHANNEL_GROUP_1_NAME) {
        // Empty group
    }

    group(CHANNEL_GROUP_2_ID, CHANNEL_GROUP_2_NAME) {
        channel(CHANNEL_2_ID, CHANNEL_2_NAME)
    }
}
```

