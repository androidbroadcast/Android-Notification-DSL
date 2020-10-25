package com.kirich1409.androidnotificationdsl.sample

import android.app.NotificationManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var notificationId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.show_notification).setOnClickListener { view: View ->
            val context = view.context
            val notifications = buildNotificationsGroupOld(context)

            val notificationManager =
                requireNotNull(ContextCompat.getSystemService(context, NotificationManager::class.java))
            notifications.forEach { notification ->
                notificationManager.notify(notificationId++, notification)
            }
        }
    }
}
