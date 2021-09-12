package com.kirich1409.androidnotificationdsl.utils

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import androidx.annotation.IntDef

@SuppressLint("InlinedApi")
@IntDef(
    flag = true,
    value = [
        PendingIntent.FLAG_ONE_SHOT,
        PendingIntent.FLAG_NO_CREATE,
        PendingIntent.FLAG_CANCEL_CURRENT,
        PendingIntent.FLAG_UPDATE_CURRENT,
        PendingIntent.FLAG_IMMUTABLE,

        Intent.FILL_IN_ACTION,
        Intent.FILL_IN_DATA,
        Intent.FILL_IN_CATEGORIES,
        Intent.FILL_IN_COMPONENT,
        Intent.FILL_IN_PACKAGE,
        Intent.FILL_IN_SOURCE_BOUNDS,
        Intent.FILL_IN_SELECTOR,
        Intent.FILL_IN_CLIP_DATA
    ]
)
@Retention(AnnotationRetention.SOURCE)
annotation class PengingIntentFlags
