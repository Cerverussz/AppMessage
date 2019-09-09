package com.danielleguizamon.appmessages.models.repository

import android.annotation.SuppressLint
import android.util.Log
import com.danielleguizamon.appmessages.data.db.entities.Post
import com.danielleguizamon.appmessages.data.db.dao.PostsDao
import com.danielleguizamon.appmessages.data.db.entities.InfoUser
import com.danielleguizamon.appmessages.data.remote.api.ApiService
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class PostsRepositoryImp(private val serviceApi: ApiService, private val postsDao: PostsDao) :
    PostsRepository {

    override fun getPosts(): Flowable<List<Post>> {
        return serviceApi.getPosts().flatMap {
            insertPosts(it)
            Flowable.just(it)
        }
    }

    override fun insertPosts(posts: List<Post>) {
        postsDao.insertPosts(posts)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {
                    Log.i(TAG, "--- onComplete insert")
                },
                onError = {
                    Log.i(TAG, "--- ${it.message}")
                }
            )
    }

    override fun getPostsDB(): Flowable<List<Post>> = postsDao.getPosts()

    override fun getUsers(): Completable {
        return serviceApi.getUsers().concatMapCompletable {
            insertUsers(it)
            Completable.complete()
        }
    }

    override fun insertUsers(users: List<InfoUser>) {
        postsDao.insertUsers(users).subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {
                    Log.i(TAG, "--- onComplete insert")
                },
                onError = {
                    Log.i(TAG, "--- ${it.message}")
                }
            )
    }

    override fun getUserDB(id: Int): Single<InfoUser> = postsDao.getInfoUser(id)


    override fun readPost(post: Post): Completable = postsDao.postRead(post)

    override fun addFavoritePost(post: Post): Completable = postsDao.addFavorite(post)

    override fun deleteFavoritePost(post: Post): Completable = postsDao.deleteFavorite(post)

    override fun getAllFavoritePost(): Flowable<List<Post>> = postsDao.getAllFavoritePosts()

    override fun deleteAllPost(): Completable = postsDao.deleteAllPost()

    companion object {
        const val TAG = "PostsRepositoryImp"
    }
}