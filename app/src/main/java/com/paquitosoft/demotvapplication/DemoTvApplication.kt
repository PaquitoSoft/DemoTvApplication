package com.paquitosoft.demotvapplication

import android.app.Application
import android.os.StrictMode
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.cast.Cast
import com.google.android.gms.cast.tv.CastReceiverContext
import com.squareup.picasso.BuildConfig
import timber.log.Timber

class DemoTvApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            enableStrictMode()
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        /**
         * This initialises an instance of the CastReceiverContext object which is needed to
         * interact with Cast while in the TV app. This object allows for passing media signals
         * and the data load and so needs to exist while the app is in the foreground so that all
         * cast commands can be picked up by the TV App.
         */
        CastReceiverContext.initInstance((this))
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifecycleObserver())
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build()
        )
    }

    class AppLifecycleObserver : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event === Lifecycle.Event.ON_RESUME) {
                CastReceiverContext.getInstance().start()
            } else if (event === Lifecycle.Event.ON_PAUSE) {
                CastReceiverContext.getInstance().stop()
            }
        }

    }
}