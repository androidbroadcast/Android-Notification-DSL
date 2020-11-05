package com.kirich1409.androidnotificationdsl.sample.presentation.evolution

import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationManagerCompat
import com.kirich1409.androidnotificationdsl.NotificationCategory
import com.kirich1409.androidnotificationdsl.NotificationDefaults
import com.kirich1409.androidnotificationdsl.expandable.bigPictureNotification
import com.kirich1409.androidnotificationdsl.notify
import com.kirich1409.androidnotificationdsl.sample.CHANNEL_DEFAULT
import com.kirich1409.androidnotificationdsl.sample.MainActivity
import com.kirich1409.androidnotificationdsl.sample.R
import com.kirich1409.androidnotificationdsl.utils.activityPendingIntent

object NotificationAndroid41 {

    private fun callActionIntent(context: Context): PendingIntent = context.activityPendingIntent<MainActivity>(1)

    private fun callIgnoreIntent(context: Context): PendingIntent = context.activityPendingIntent<MainActivity>(2)

    fun actions(context: Context, notificationId: Int) {
        notify(context, notificationId, CHANNEL_DEFAULT, R.drawable.ic_android_white_24dp) {
            contentTitle = "Входящий звонок"
            contentText = "Штурмовик"
            largeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.stormtrooper)
            category = NotificationCategory.CALL
            showWhen = false

            actions {
                action("Ответить", callActionIntent(context), R.drawable.ic_call)
                action("Отклонить", callIgnoreIntent(context), R.drawable.ic_call_end)
            }
        }
    }

    fun bigPicture(context: Context, notificationId: Int) {
        NotificationManagerCompat.from(context)
            .notify(notificationId,
                bigPictureNotification(context, CHANNEL_DEFAULT, R.drawable.ic_android_white_24dp) {
                    largeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_vader)
                    title = "Дарт Вейдер"
                    text = "Мои детки"

                    expanded {
                        largeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_vader)
                        bigPicture = BitmapFactory.decodeResource(context.resources, R.drawable.skyworkers)
                        text = "Мои детки"
                    }

                    expanded {
                        actions {
                            action("Лайк", callActionIntent(context), R.drawable.ic_like)
                        }
                    }
                }
            )
    }
}
