package com.ambrosio.movietrove.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import com.ambrosio.movietrove.models.MOVIE_TABLE
import com.ambrosio.movietrove.models.Movie

@Dao
interface MovieDAO {

    @Insert
    fun addMovie(movieEntity: Movie)

    @Insert
    fun insertAll(movieEntities: List<Movie>)

    @Update
    fun updateMovie(movieEntity: Movie)

    @Delete
    fun deleteMovie(movieEntity: Movie)

    @Query("SELECT * FROM $MOVIE_TABLE")
    fun retrieveMovies(): MutableList<Movie>

}
