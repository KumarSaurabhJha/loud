package com.kumar.loudly.test.application

import android.app.Application
import com.kumar.loudly.test.di.domainModule
import com.kumar.loudly.test.di.restApiModule
import com.kumar.loudly.test.di.restRepositoryModule
import com.kumar.loudly.test.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                restApiModule,
                restRepositoryModule,
                retrofitModule,
                domainModule
            )
        }
    }
}