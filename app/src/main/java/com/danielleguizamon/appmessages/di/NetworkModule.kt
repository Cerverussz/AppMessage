package com.danielleguizamon.appmessages.di

import com.danielleguizamon.appmessages.core.Network
import com.danielleguizamon.appmessages.data.remote.api.ApiService
import org.koin.dsl.module

val networkModule = module {
    single { Network().createWebService().create(ApiService::class.java) }
}