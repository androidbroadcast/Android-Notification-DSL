@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private var notificationId = 1
        get() {
            val value = field
            field++
            return value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.show_notification).setOnClickListener { view: View ->
            buildNotificationsGroup(view.context).notify(NotificationManagerCompat.from(view.context))
        }
    }
}
