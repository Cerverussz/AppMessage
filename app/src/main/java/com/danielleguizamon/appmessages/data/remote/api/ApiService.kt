package com.danielleguizamon.appmessages.data.remote.api

import com.danielleguizamon.appmessages.data.db.entities.InfoUser
import com.danielleguizamon.appmessages.data.db.entities.Post
import com.danielleguizamon.appmessages.rest.Endpoints.GET_POST_USER
import com.danielleguizamon.appmessages.rest.Endpoints.GET_USERS
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET(GET_POST_USER)
    fun getPosts(): Flowable<List<Post>>

    @GET(GET_USERS)
    fun getUsers(): Flowable<List<InfoUser>>

}