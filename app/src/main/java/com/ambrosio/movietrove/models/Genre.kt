package com.ambrosio.movietrove.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

const val GENRE_TABLE: String = "genre"
@Entity(tableName = GENRE_TABLE)
class Genre (

    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String = ""

    )