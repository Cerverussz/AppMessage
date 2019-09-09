package com.danielleguizamon.appmessages.data.db.dao

import androidx.room.*
import com.danielleguizamon.appmessages.data.db.entities.InfoUser
import com.danielleguizamon.appmessages.data.db.entities.Post
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(post: List<Post>): Completable

    @Query("SELECT * FROM post")
    fun getPosts(): Flowable<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<InfoUser>): Completable

    @Query("SELECT * FROM infoUser WHERE id = :id")
    fun getInfoUser(id: Int): Single<InfoUser>

    @Update
    fun postRead(post: Post): Completable

    @Update
    fun deleteFavorite(post: Post): Completable

    @Update
    fun addFavorite(post: Post): Completable

    @Query("SELECT * FROM post WHERE isFavorite = 1")
    fun getAllFavoritePosts(): Flowable<List<Post>>

    @Query("DELETE FROM post")
    fun deleteAllPost(): Completable


}