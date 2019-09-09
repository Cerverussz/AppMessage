package com.danielleguizamon.appmessages.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danielleguizamon.appmessages.data.db.dao.PostsDao
import com.danielleguizamon.appmessages.data.db.entities.InfoUser
import com.danielleguizamon.appmessages.data.db.entities.Post

@Database(entities = [Post::class, InfoUser::class], version = 12)
abstract class DataBase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}