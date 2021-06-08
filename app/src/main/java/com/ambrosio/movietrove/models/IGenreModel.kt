package com.ambrosio.movietrove.models

interface IGenreModel {

    suspend fun addGenre(genre: Genre, callback: SuccessCallback)
    suspend fun addGenres(genres: List<Genre>, callback: SuccessCallback)
    suspend fun updateGenre(genre: Genre, callback: SuccessCallback)
    suspend fun deleteGenre(genre: Genre, callback: SuccessCallback)
    suspend fun retrieveGenres(callback: (List<Genre>?) -> Unit)
}