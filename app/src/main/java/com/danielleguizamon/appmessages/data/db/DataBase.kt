package com.danielleguizamon.appmessages.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danielleguizamon.appmessages.data.db.dao.PostsDao

@Database(entities = [Post::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}