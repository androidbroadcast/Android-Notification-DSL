@file:Suppress("unused")

package com.kirich1409.androidnotificationdsl.utils

import android.app.Activity
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlin.reflect.KClass

fun Context.activityPendingIntent(
    requestCode: Int,
    intent: Intent,
    @PengingIntentFlags flags: Int = PendingIntent.FLAG_CANCEL_CURRENT,
    options: Bundle? = null
): PendingIntent {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        return PendingIntent.getActivity(this, requestCode, intent, flags, options)
    } else {
        return PendingIntent.getActivity(this, requestCode, intent, flags)
    }
}

fun Context.activityPendingIntent(
    requestCode: Int,
    activityClass: Class<out Activity>,
    @PengingIntentFlags flags: Int = PendingIntent.FLAG_CANCEL_CURRENT,
    options: Bundle? = null
): PendingIntent {
    return activityPendingIntent(requestCode, Intent(this, activityClass), flags, options)
}

fun Context.activityPendingIntent(
    requestCode: Int,
    activityClass: KClass<out Activity>,
    @PengingIntentFlags flags: Int = PendingIntent.FLAG_CANCEL_CURRENT,
    options: Bundle? = null
): PendingIntent {
    return activityPendingIntent(requestCode, activityClass.java, flags, options)
}

inline fun <reified T : Activity> Context.activityPendingIntent(
    requestCode: Int,
    @PengingIntentFlags flags: Int = PendingIntent.FLAG_CANCEL_CURRENT,
    options: Bundle? = null
): PendingIntent {
    return activityPendingIntent(requestCode, T::class, flags, options)
}

fun Context.activitiesPendingIntent(
    requestCode: Int,
    intents: Array<Intent>,
    @PengingIntentFlags flags: Int = PendingIntent.FLAG_CANCEL_CURRENT,
    options: Bundle? = null
): PendingIntent {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        return PendingIntent.getActivities(this, requestCode, intents, flags, options)
    } else {
        return PendingIntent.getActivities(this, requestCode, intents, flags)
    }
}

fun Context.broadcastPendingIntent(
    requestCode: Int,
    intent: Intent,
    flags: Int = PendingIntent.FLAG_CANCEL_CURRENT
): PendingIntent {
    return PendingIntent.getBroadcast(this, requestCode, intent, flags)
}

fun Context.broadcastPendingIntent(
    requestCode: Int,
    broadcastReceiverClass: Class<out BroadcastReceiver>,
    flags: Int = PendingIntent.FLAG_CANCEL_CURRENT
): PendingIntent {
    return broadcastPendingIntent(requestCode, Intent(this, broadcastReceiverClass), flags)
}

fun Context.broadcastPendingIntent(
    requestCode: Int,
    broadcastReceiverClass: KClass<out BroadcastReceiver>,
    flags: Int = PendingIntent.FLAG_CANCEL_CURRENT
): PendingIntent {
    return broadcastPendingIntent(requestCode, broadcastReceiverClass.java, flags)
}

inline fun <reified T : BroadcastReceiver> Context.broadcastPendingIntent(
    requestCode: Int,
    flags: Int = PendingIntent.FLAG_CANCEL_CURRENT
): PendingIntent {
    return broadcastPendingIntent(requestCode, T::class, flags)
}

fun Context.servicePendingIntent(
    requestCode: Int,
    intent: Intent,
    flags: Int = PendingIntent.FLAG_CANCEL_CURRENT
): PendingIntent {
    return PendingIntent.getService(this, requestCode, intent, flags)
}

fun Context.servicePendingIntent(
    requestCode: Int,
    serviceClass: Class<out Service>,
    flags: Int = PendingIntent.FLAG_CANCEL_CURRENT
): PendingIntent {
    return servicePendingIntent(requestCode, Intent(this, serviceClass), flags)
}

fun Context.servicePendingIntent(
    requestCode: Int,
    serviceClass: KClass<out Service>,
    flags: Int = PendingIntent.FLAG_CANCEL_CURRENT
): PendingIntent {
    return servicePendingIntent(requestCode, serviceClass.java, flags)
}

inline fun <reified T : Service> Context.servicePendingIntent(
    requestCode: Int,
    flags: Int = PendingIntent.FLAG_CANCEL_CURRENT
): PendingIntent {
    return servicePendingIntent(requestCode, T::class, flags)
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.foregroundServicePendingIntent(
    requestCode: Int,
    intent: Intent,
    flags: Int = PendingIntent.FLAG_CANCEL_CURRENT
): PendingIntent {
    return PendingIntent.getForegroundService(this, requestCode, intent, flags)
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.foregroundServicePendingIntent(
    requestCode: Int,
    serviceClass: Class<out Service>,
    flags: Int = PendingIntent.FLAG_CANCEL_CURRENT
): PendingIntent {
    return foregroundServicePendingIntent(requestCode, Intent(this, serviceClass), flags)
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.foregroundServicePendingIntent(
    requestCode: Int,
    serviceClass: KClass<out Service>,
    flags: Int = PendingIntent.FLAG_CANCEL_CURRENT
): PendingIntent {
    return foregroundServicePendingIntent(requestCode, serviceClass.java, flags)
}

@RequiresApi(Build.VERSION_CODES.O)
inline fun <reified T : Service> Context.foregroundServicePendingIntent(
    requestCode: Int,
    flags: Int = PendingIntent.FLAG_CANCEL_CURRENT
): PendingIntent {
    return foregroundServicePendingIntent(requestCode, T::class, flags)
}
