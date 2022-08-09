package com.gzslt.imagesearch

import android.app.Application
import logcat.AndroidLogcatLogger
import logcat.LogPriority

class ImageSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidLogcatLogger.installOnDebuggableApp(this, LogPriority.VERBOSE)
    }
}
