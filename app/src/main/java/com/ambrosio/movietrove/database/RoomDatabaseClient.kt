package com.ambrosio.movietrove.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import android.content.Context
import com.ambrosio.movietrove.models.Movie

const val DATABASE_SCHEMA_VERSION = 1
const val DB_NAME = "local-db"

@Database(version = DATABASE_SCHEMA_VERSION, entities = [Movie::class], exportSchema = false)
abstract class RoomDatabaseClient : RoomDatabase(){

    // Insert DAOs below
    abstract fun movieDAO(): MovieDAO

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