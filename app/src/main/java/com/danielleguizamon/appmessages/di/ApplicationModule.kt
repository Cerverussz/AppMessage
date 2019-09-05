package com.danielleguizamon.appmessages.di

import com.danielleguizamon.appmessages.models.repository.PostsRepository
import com.danielleguizamon.appmessages.models.repository.PostsRepositoryImp
import org.koin.dsl.module

val applicationModule = module {
    factory<PostsRepository> { PostsRepositoryImp(get(), get()) }
}