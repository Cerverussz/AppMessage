package com.danielleguizamon.appmessages.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "post")
data class Post(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @field:Json(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "userId")
    @field:Json(name = "userId")
    val userId: Int = 0,

    @ColumnInfo(name = "title")
    @field:Json(name = "title")
    var title: String = "",

    @ColumnInfo(name = "body")
    @field:Json(name = "body")
    var body: String = "",

    @ColumnInfo(name = "isRead")
    var isRead: Int = 0,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Int = 0

): Serializable