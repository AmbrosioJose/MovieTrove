package com.ambrosio.movietrove.models

typealias SuccessCallback = (Boolean) -> Unit

interface IMovieModel {

    suspend fun addMovie(movie: Movie, callback: SuccessCallback)
    suspend fun addMovies(movies: List<Movie>, callback: SuccessCallback)
    suspend fun updateMovie(movie: Movie, callback: SuccessCallback)
    suspend fun deleteMovie(movie: Movie, callback: SuccessCallback)
    suspend fun retrieveMovies(callback: (List<Movie>?) -> Unit)
}