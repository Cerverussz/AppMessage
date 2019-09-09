package com.danielleguizamon.appmessages.models.repository

import com.danielleguizamon.appmessages.data.db.entities.InfoUser
import com.danielleguizamon.appmessages.data.db.entities.Post
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PostsRepository {

    fun getPosts(): Flowable<List<Post>>

    fun insertPosts(posts: List<Post>)

    fun getPostsDB(): Flowable<List<Post>>

    fun getUsers(): Completable

    fun insertUsers(users: List<InfoUser>)

    fun getUserDB(id: Int): Single<InfoUser>

    fun readPost(post: Post): Completable

    fun addFavoritePost(post: Post): Completable

    fun deleteFavoritePost(post: Post): Completable

    fun getAllFavoritePost(): Flowable<List<Post>>

    fun deleteAllPost(): Completable
}