package com.danielleguizamon.appmessages.models.repository

import com.danielleguizamon.appmessages.data.db.Post

interface PostsRepository {
    fun insertUsers(users: List<Post>)
}