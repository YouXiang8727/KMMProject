package org.youxiang8727.kmmproject

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinApplication)
            androidLogger()
            modules(
                viewModelModule
            )
        }
    }
}