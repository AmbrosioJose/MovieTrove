package com.ambrosio.movietrove.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import androidx.room.Query
import com.ambrosio.movietrove.models.GENRE_TABLE
import com.ambrosio.movietrove.models.Genre

@Dao
interface GenreDAO {

    @Insert
    fun addGenre(genreEntity: Genre)

    @Insert
    fun insertAll(genreEntities: List<Genre>)

    @Update
    fun updateGenre(genre: Genre)

    @Delete
    fun deleteGenre(genre: Genre)

    @Query("SELECT * FROM $GENRE_TABLE")
    fun retrieveGenres(): List<Genre>?

}