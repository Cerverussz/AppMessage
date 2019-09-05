package com.danielleguizamon.appmessages.data.remote.Api

import com.danielleguizamon.appmessages.data.db.Post
import com.danielleguizamon.appmessages.rest.Endpoints.GET_POST_USER
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {
    @GET(GET_POST_USER)
    fun getPosts(): Flowable<List<Post>>
}