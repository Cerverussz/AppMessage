package com.danielleguizamon.appmessages.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "post")
data class Post(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @field:Json(name = "id")
    var id: Int = 0
)