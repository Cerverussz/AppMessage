package com.danielleguizamon.appmessages.di

import org.koin.dsl.module

val roomModule = module {
  //  single { Room.databaseBuilder(get(), DataBase::class.java, "database_movie").build() }
  //  single { get<DataBase>().movieDao() }
}