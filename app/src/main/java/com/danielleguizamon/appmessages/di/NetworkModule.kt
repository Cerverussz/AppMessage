package com.danielleguizamon.appmessages.di

import co.com.ceiba.mobile.pruebadeingreso.core.Network
import com.danielleguizamon.appmessages.data.remote.Api.ApiService
import org.koin.dsl.module

val networkModule = module {
    single { Network().createWebService().create(ApiService::class.java) }
}