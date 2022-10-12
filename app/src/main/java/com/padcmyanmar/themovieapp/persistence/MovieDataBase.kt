package com.padcmyanmar.themovieapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padcmyanmar.themovieapp.data.vos.MovieVO
import com.padcmyanmar.themovieapp.persistence.daos.MovieDao

@Database(entities = [MovieVO::class], version = 3, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "THE_MOVIE_DB"

        var dbInstance: MovieDataBase? = null

        fun getDBInstance(context: Context): MovieDataBase? {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context, MovieDataBase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance
        }
    }

    abstract fun movieDao(): MovieDao
}