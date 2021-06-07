package com.ambrosio.movietrove.database

import com.ambrosio.movietrove.TroveApplication
import com.ambrosio.movietrove.models.IMovieModel
import com.ambrosio.movietrove.models.Movie
import com.ambrosio.movietrove.models.SuccessCallback
import com.ambrosio.movietrove.foundations.ApplicationScope
import kotlinx.coroutines.*
import toothpick.Toothpick
import javax.inject.Inject

const val TIMEOUT_DURATION_MILLIS = 3000L

class MovieLocalModel @Inject constructor() : IMovieModel {

    private var databaseClient: RoomDatabaseClient =
        RoomDatabaseClient.getInstance(TroveApplication.instance.applicationContext)

    private suspend fun performOperationWithTimeout(function: () -> Unit, callback: SuccessCallback){
        GlobalScope.launch {
            val job = async {
                try {
                    withTimeout(TIMEOUT_DURATION_MILLIS) {
                        function.invoke()
                    }
                    true
                } catch(e: java.lang.Exception) {
                    false
                }
            }
            callback.invoke(job.await())
        }
    }

    override suspend fun addMovie(movie: Movie, callback: SuccessCallback){
        performOperationWithTimeout({databaseClient.movieDAO().addMovie(movie)}, callback)
    }

    override suspend fun addMovies(movies: List<Movie>, callback: SuccessCallback){
        performOperationWithTimeout({databaseClient.movieDAO().insertAll(movies)}, callback)
    }

    override suspend fun updateMovie(movie: Movie, callback: SuccessCallback){
        performOperationWithTimeout({
            databaseClient.movieDAO().updateMovie(movie)
        }, callback)
    }

    override suspend fun deleteMovie(movie: Movie, callback: SuccessCallback){
        performOperationWithTimeout({databaseClient.movieDAO().deleteMovie(movie)}, callback)
    }

    override suspend fun retrieveMovies(callback: (List<Movie>?) -> Unit){
        val job = GlobalScope.async {
            withTimeoutOrNull(TIMEOUT_DURATION_MILLIS){
                databaseClient.movieDAO().retrieveMovies()
            }
        }
        callback.invoke(job.await())
    }
}
