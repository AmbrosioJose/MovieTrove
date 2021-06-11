package com.ambrosio.movietrove.database

import com.ambrosio.movietrove.TroveApplication
import com.ambrosio.movietrove.models.Genre
import com.ambrosio.movietrove.models.IGenreModel
import com.ambrosio.movietrove.models.SuccessCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject

class GenreLocalModel @Inject constructor() : IGenreModel  {

    private var databaseclient: RoomDatabaseClient = RoomDatabaseClient.getInstance(TroveApplication.instance.applicationContext)

    private suspend fun performOperationWithTimeout(function: ()-> Unit, callback: SuccessCallback){
        GlobalScope.launch {
            val job = async {
                try {
                    withTimeout(TIMEOUT_DURATION_MILLIS){
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

    override suspend fun addGenre(genre: Genre, callback: SuccessCallback){
        performOperationWithTimeout({databaseclient.genreDAO().addGenre(genre)}, callback)
    }

    override suspend fun addGenres(genres: List<Genre>, callback: SuccessCallback){
        performOperationWithTimeout({databaseclient.genreDAO().insertAll(genres)}, callback)
    }

    override suspend fun updateGenre(genre: Genre, callback: SuccessCallback){
        performOperationWithTimeout({databaseclient.genreDAO().addGenre(genre)}, callback)
    }

    override suspend fun deleteGenre(genre: Genre, callback: SuccessCallback){
        performOperationWithTimeout({databaseclient.genreDAO().deleteGenre(genre)}, callback)
    }

    override suspend fun retrieveGenres(callback: (List<Genre>?) -> Unit) {
        val job = GlobalScope.async {
            withTimeoutOrNull(TIMEOUT_DURATION_MILLIS){
                databaseclient.genreDAO().retrieveGenres()
            }
        }
        callback.invoke(job.await())
    }
}