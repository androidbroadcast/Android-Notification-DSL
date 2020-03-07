package com.kirich1409.androidnotificationdsl.sample

import android.app.NotificationManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kirich1409.androidnotificationdsl.notification

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.show_notification).setOnClickListener { view: View ->
            val context = view.context
            val notification = context.notification(NOTIFICATION_CHANNEL_DEFAULT, R.drawable.ic_android_white_24dp)

            val notificationManager =
                requireNotNull(ContextCompat.getSystemService(context, NotificationManager::class.java))
            notificationManager.notify(1, notification)
        }
    }
}