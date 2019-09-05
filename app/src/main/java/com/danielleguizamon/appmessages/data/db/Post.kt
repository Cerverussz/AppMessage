package com.danielleguizamon.appmessages.data.db

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity(tableName = "post")
data class Post(
    @Json(name = "id")
    @field:Json(name = "id")
    val id: Int = 0
)