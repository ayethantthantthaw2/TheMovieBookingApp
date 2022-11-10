package com.attt.moviebookingapp

import android.app.Application
import com.attt.themovieapp.data.models.MovieModelImpl
import data.models.MovieBookingModelImpl

class MovieBookingApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        MovieBookingModelImpl.initDatabase(applicationContext)
        MovieModelImpl.initDatabase(applicationContext)
    }
}