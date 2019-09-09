package com.danielleguizamon.appmessages.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "infoUser")
data class InfoUser (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @field:Json(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    @field:Json(name = "name")
    val name: String = "",

    @ColumnInfo(name = "email")
    @field:Json(name = "email")
    val email: String = "",

    @ColumnInfo(name = "phone")
    @field:Json(name = "phone")
    val phone: String = ""
) : Serializable