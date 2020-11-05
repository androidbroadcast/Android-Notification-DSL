package com.kirich1409.androidnotificationdsl.sample

import android.app.NotificationManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kirich1409.androidnotificationdsl.sample.presentation.evolution.NotificationAndroid41

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
            NotificationAndroid41.actions(view.context, notificationId)
            NotificationAndroid41.bigPicture(view.context, notificationId)
        }
    }
}
