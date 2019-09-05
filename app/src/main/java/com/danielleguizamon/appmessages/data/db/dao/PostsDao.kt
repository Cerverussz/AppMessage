package com.danielleguizamon.appmessages.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.danielleguizamon.appmessages.data.db.Post
import io.reactivex.Completable

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<Post>): Completable
}