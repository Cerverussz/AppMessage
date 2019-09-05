package com.danielleguizamon.appmessages.models.repository

import com.danielleguizamon.appmessages.data.db.Post
import com.danielleguizamon.appmessages.data.db.dao.PostsDao
import com.danielleguizamon.appmessages.data.remote.Api.ApiService

class PostsRepositoryImp(private val serviceApi: ApiService, private val postsDao: PostsDao) :
    PostsRepository {
    override fun insertUsers(users: List<Post>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}