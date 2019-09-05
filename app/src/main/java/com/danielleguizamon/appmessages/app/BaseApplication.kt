package com.danielleguizamon.appmessages.app

import android.app.Application
import com.danielleguizamon.appmessages.di.applicationModule
import com.danielleguizamon.appmessages.di.networkModule
import com.danielleguizamon.appmessages.di.roomModule
import com.danielleguizamon.appmessages.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger()
            modules(
                    listOf(
                            applicationModule,
                            networkModule,
                            roomModule,
                            viewModelModule
                    )
            )

        }
    }
}