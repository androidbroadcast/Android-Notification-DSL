package com.kirich1409.androidnotificationdsl.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes

fun @receiver:DrawableRes Int.asBitmap(
    resources: Resources,
    options: BitmapFactory.Options? = null
): Bitmap {
    return BitmapFactory.decodeResource(resources, this, options)
}