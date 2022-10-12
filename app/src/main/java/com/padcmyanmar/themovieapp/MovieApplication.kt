package com.padcmyanmar.themovieapp

import android.app.Application
import com.padcmyanmar.themovieapp.data.models.MovieModelImpl

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDataBase(applicationContext)
    }
}