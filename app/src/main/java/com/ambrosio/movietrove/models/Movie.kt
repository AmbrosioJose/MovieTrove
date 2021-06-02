package com.ambrosio.movietrove.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movie")
data class Movie (

    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String = "",


    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String = "",


    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String = "",


    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String = "",

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String = "",


    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Float = 0.0f

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(releaseDate)
        parcel.writeString(overview)
        parcel.writeString(posterPath)
        parcel.writeString(backdropPath)
        parcel.writeFloat(voteAverage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}