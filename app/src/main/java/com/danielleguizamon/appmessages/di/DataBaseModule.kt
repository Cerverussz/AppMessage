package com.danielleguizamon.appmessages.di

import androidx.room.Room
import com.danielleguizamon.appmessages.data.db.DataBase
import org.koin.dsl.module

val roomModule = module {
    single { Room.databaseBuilder(get(), DataBase::class.java, "database_posts").build() }
    single { get<DataBase>().postsDao() }
}