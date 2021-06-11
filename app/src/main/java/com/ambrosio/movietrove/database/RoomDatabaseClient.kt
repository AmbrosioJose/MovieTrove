package com.ambrosio.movietrove.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import android.content.Context
import com.ambrosio.movietrove.models.Movie
import com.ambrosio.movietrove.models.Genre

const val DATABASE_SCHEMA_VERSION = 1
const val DB_NAME = "local-db"

@Database(version = DATABASE_SCHEMA_VERSION, entities = [Movie::class, Genre::class], exportSchema = false)
abstract class RoomDatabaseClient : RoomDatabase(){

    // Insert DAOs below
    abstract fun movieDAO(): MovieDAO
    abstract fun genreDAO(): GenreDAO

    companion object {
        private var instance: RoomDatabaseClient? = null

        fun getInstance(context: Context): RoomDatabaseClient {
            if(instance == null){
                instance = createDatabase(context)
            }
            return instance!!
        }

        private fun createDatabase(context: Context): RoomDatabaseClient {
            return Room.databaseBuilder(context, RoomDatabaseClient::class.java, DB_NAME)
                .build()
        }
    }
}